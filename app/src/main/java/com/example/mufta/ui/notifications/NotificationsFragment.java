package com.example.mufta.ui.notifications;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.mufta.R;
import com.example.mufta.databinding.FragmentNotificationsBinding;
import com.example.mufta.ui.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class NotificationsFragment extends Fragment {
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;
    Uri selectedImage;
    EditText e1;
    private FragmentNotificationsBinding binding;
    ProgressDialog dailog;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);


//        Bundle bundle = getIntent().getExtras();
//        String key = bundle.getString("key");
        getdata();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();

        dailog = ProgressDialog.show(requireActivity(), "", "Loading..", true);

        binding.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 45);
            }
        });

        binding.BtnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dailog = ProgressDialog.show(requireActivity(), "", "Loading..", true);
                String phonenumber = binding.numberBox.getText().toString();
                String city = binding.cityBox.getText().toString();
                String name = binding.nameBox.getText().toString();
                if (name.isEmpty()) {
                    binding.nameBox.setError("Please type a name");
                    return;
                }

                if (city.isEmpty()) {
                    binding.nameBox.setError("Please enter your city");
                    return;
                }
                if (selectedImage != null) {
                    StorageReference reference = storage.getReference().child("Profiles");
                    reference.putFile(selectedImage).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful()) {
                                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        String imageUrl = uri.toString();
                                        String uid = auth.getUid();
                                        String email = auth.getCurrentUser().getEmail();
                                        String name = binding.nameBox.getText().toString();
                                        String phonenumber = binding.numberBox.getText().toString();
                                        String city = binding.cityBox.getText().toString();
                                        User user = new User( name, email, imageUrl, phonenumber, city);
                                        database.getReference()
                                                .child("users")
                                                .child(uid)
                                                .setValue(user)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        dailog.dismiss();
                                                        Toast.makeText(requireActivity().getApplicationContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                    }
                                });
                            }
                        }
                    });
                } else {
                    String uid = auth.getUid();
                    String email = auth.getCurrentUser().getEmail();

                    User user = new User( name, email, "No Image", phonenumber, city);

                    database.getReference()
                            .child("users")
                            .child(uid)
                            .setValue(user)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(requireActivity().getApplicationContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(UpdateProfile.this, MainActivity.class);
//                                    startActivity(intent);
                                    dailog.dismiss();
//                                    finish();
                                }
                            });
                }

            }
        });
        return binding.getRoot();
    }

    public void getdata() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child("users").child((FirebaseAuth.getInstance().getUid()));
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    User user = snapshot.getValue(User.class);
                    binding.nameBox.setHint(user.name);
                    binding.emailBox.setHint(user.getEmailid());
                    binding.numberBox.setHint(user.getPhonenumber());
                    binding.cityBox.setHint(user.getCity());
                    Glide.with(NotificationsFragment.this).load(user.profileImage)
                            .placeholder(R.drawable.avatar)
                            .into(binding.imageView);
                    dailog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            if (data.getData() != null) {
                Uri uri = data.getData(); // filepath
                FirebaseStorage storage = FirebaseStorage.getInstance();
                long time = new Date().getTime();
                StorageReference reference = storage.getReference().child("Profiles").child(time + "");
                reference.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (task.isSuccessful()) {
                            reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String filePath = uri.toString();
                                    HashMap<String, Object> obj = new HashMap<>();
                                    obj.put("image", filePath);
                                    database.getReference().child("users")
                                            .child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()))
                                            .updateChildren(obj).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {

                                        }
                                    });
                                }
                            });
                        }
                    }
                });
                binding.imageView.setImageURI(data.getData());
                selectedImage = data.getData();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}