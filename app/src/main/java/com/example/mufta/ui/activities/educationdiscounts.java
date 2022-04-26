package com.example.mufta.ui.activities;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mufta.R;
import com.example.mufta.ui.adapters.educationsAdapter;
import com.example.mufta.ui.models.educationClass;
import com.example.mufta.ui.models.homeClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class educationdiscounts extends AppCompatActivity {
    private ArrayList<educationClass> educationList;
    educationsAdapter eadapter;
    RecyclerView RvEducation;
    Query query;
    ProgressDialog dailog;
    ImageButton down;
    TextView textView;
    boolean[] selectedLanguage;
    ArrayList<Integer> langList = new ArrayList<>();
    String[] langArray = {"Java", "C++", "Kotlin", "C", "Python", "Javascript"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_educationdiscounts);
        Objects.requireNonNull(getSupportActionBar()).hide();

        RvEducation = findViewById(R.id.RVEducationDiscounts);
        down = findViewById(R.id.drop_down);
        textView = findViewById(R.id.textt);
        dailog = ProgressDialog.show(educationdiscounts.this, "", "Loading..", true);
        dailog.setCancelable(true);
        educationList = new ArrayList<>();
        eadapter = new educationsAdapter(this, educationList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RvEducation.setLayoutManager(linearLayoutManager);
        RvEducation.setAdapter(eadapter);
        RvEducation.setHasFixedSize(true);
        query = FirebaseDatabase.getInstance().getReference().child("Discounts").child("Education");
        query.addListenerForSingleValueEvent(valueEventListener);
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(educationdiscounts.this);

                // set title
                builder.setTitle("Select Category");

                // set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(langArray, selectedLanguage, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        // check condition
                        if (b) {
                            // when checkbox selected
                            // Add position  in lang list
                            langList.add(i);
                            // Sort array list
                            Collections.sort(langList);
                        } else {
                            // when checkbox unselected
                            // Remove position from langList
                            langList.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        // use for loop
                        for (int j = 0; j < langList.size(); j++) {
                            // concat array value
                            stringBuilder.append(langArray[langList.get(j)]);
                            // check condition
                            if (j != langList.size() - 1) {
                                // When j value  not equal
                                // to lang list size - 1
                                // add comma
                                stringBuilder.append(", ");
                            }
                        }
                        // set text on textView
                        textView.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // use for loop
                        for (int j = 0; j < selectedLanguage.length; j++) {
                            // remove all selection
                            selectedLanguage[j] = false;
                            // clear language list
                            langList.clear();
                            // clear text view value
                            textView.setText("");
                        }
                    }
                });
                // show dialog
                builder.show();
            }
        });
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @SuppressLint("NotifyDataSetChanged")
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            educationList.clear();
            if (snapshot.exists()) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    educationClass educlass = snapshot1.getValue(educationClass.class);
                    educationList.add(educlass);
                    dailog.dismiss();
                }
                eadapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(educationdiscounts.this, "" + error, Toast.LENGTH_SHORT).show();
        }
    };


}