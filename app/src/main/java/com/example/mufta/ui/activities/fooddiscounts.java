package com.example.mufta.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mufta.R;
import com.example.mufta.ui.adapters.foodadapter;
import com.example.mufta.ui.models.foodmodelclass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.Objects;

public class fooddiscounts extends AppCompatActivity {
    private ArrayList<foodmodelclass> foodmodelclassArrayList;
    foodadapter foodadapter;
    RecyclerView Rvfood;
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;
    Query query;
    ImageView back;
    ProgressDialog dailog;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fooddiscounts2);
        Objects.requireNonNull(getSupportActionBar()).hide();
        back = findViewById(R.id.back);
        SharedPreferences prefs = getSharedPreferences("city", MODE_PRIVATE);
        String resID = prefs.getString("resId", String.valueOf(0));
        Intent intent = getIntent();
        category = intent.getStringExtra("category");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(fooddiscounts.this, MainActivity.class);
                startActivity(in);
                finishAffinity();
            }
        });
        Rvfood = findViewById(R.id.RVfooddiscounts);
        dailog = ProgressDialog.show(fooddiscounts.this, "", "Loading..", true);

        foodmodelclassArrayList = new ArrayList<>();
        foodadapter = new foodadapter(this, foodmodelclassArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        Rvfood.setLayoutManager(linearLayoutManager);
        Rvfood.setAdapter(foodadapter);
        Rvfood.setHasFixedSize(true);


        if (category.equals("food")) {
            query = FirebaseDatabase.getInstance().getReference().child("Discounts").child("Other Discounts").orderByChild("category").equalTo("food");
            query.addListenerForSingleValueEvent(valueEventListener);
        } else if (category.equals("lifestyle")) {
            query = FirebaseDatabase.getInstance().getReference().child("Discounts").child("Other Discounts").orderByChild("category").equalTo("lifestyle");
            query.addListenerForSingleValueEvent(valueEventListener);
        } else if (category.equals("clothing")) {
            query = FirebaseDatabase.getInstance().getReference().child("Discounts").child("Other Discounts").orderByChild("category").equalTo("clothing");
            query.addListenerForSingleValueEvent(valueEventListener);
        } else if (category.equals("event")) {
            query = FirebaseDatabase.getInstance().getReference().child("Discounts").child("Other Discounts").orderByChild("category").equalTo("event");
            query.addListenerForSingleValueEvent(valueEventListener);
        } else if (category.equals("travelling")) {
            query = FirebaseDatabase.getInstance().getReference().child("Discounts").child("Other Discounts").orderByChild("category").equalTo("travelling");
            query.addListenerForSingleValueEvent(valueEventListener);
        }
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
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

}