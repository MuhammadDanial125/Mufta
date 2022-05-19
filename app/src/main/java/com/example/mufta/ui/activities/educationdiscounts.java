package com.example.mufta.ui.activities;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;
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
    private ArrayList<educationClass> temeducationList;
    educationsAdapter eadapter;
    RecyclerView RvEducation;
    Query query;
    Spinner spinner;
    String string;
    ProgressDialog dailog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_educationdiscounts);
        Objects.requireNonNull(getSupportActionBar()).hide();
        RvEducation = findViewById(R.id.RVEducationDiscounts);
        spinner = findViewById(R.id.drop_down);
        dailog = ProgressDialog.show(educationdiscounts.this, "", "Loading..", true);
        dailog.setCancelable(true);
        educationList = new ArrayList<>();
        temeducationList = new ArrayList<>();
        eadapter = new educationsAdapter(this, educationList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RvEducation.setLayoutManager(linearLayoutManager);
        RvEducation.setAdapter(eadapter);
        RvEducation.setHasFixedSize(true);
        query = FirebaseDatabase.getInstance().getReference().child("Discounts").child("Education");
        query.addListenerForSingleValueEvent(valueEventListener);
        string = spinner.getSelectedItem().toString();
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