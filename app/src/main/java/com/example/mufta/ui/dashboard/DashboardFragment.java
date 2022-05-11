package com.example.mufta.ui.dashboard;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mufta.R;
import com.example.mufta.databinding.FragmentDashboardBinding;
import com.example.mufta.ui.adapters.educationsAdapter;
import com.example.mufta.ui.adapters.foodadapter;
import com.example.mufta.ui.models.educationClass;
import com.example.mufta.ui.models.foodmodelclass;
import com.example.mufta.ui.models.homeClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class DashboardFragment extends Fragment {
    private ArrayList<foodmodelclass> foodmodelclassArrayList;
    foodadapter foodadapter;
    private ProgressBar spinner;
    Query query;
    ProgressDialog dailog;
    FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        DashboardViewModel dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).hide();
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        foodmodelclassArrayList = new ArrayList<>();
        foodadapter = new foodadapter(requireActivity().getApplicationContext(), foodmodelclassArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        binding.rvCardview.setLayoutManager(linearLayoutManager);
        binding.rvCardview.setAdapter(foodadapter);
        query = FirebaseDatabase.getInstance().getReference().child("Discounts").child("Other Discounts").orderByChild("category");
        query.addListenerForSingleValueEvent(valueEventListener);
        dailog = ProgressDialog.show(requireActivity(), "", "Loading..", true);
        return root;
    }


    ValueEventListener valueEventListener = new ValueEventListener() {
        @SuppressLint("NotifyDataSetChanged")
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            foodmodelclassArrayList.clear();
            if (snapshot.exists()) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    foodmodelclass foodclass = snapshot1.getValue(foodmodelclass.class);
                    foodmodelclassArrayList.add(foodclass);
                    dailog.dismiss();
                }
                foodadapter.notifyDataSetChanged();
            }
//            binding.pg.setVisibility(View.GONE);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(requireActivity().getApplicationContext(), "" + error, Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}