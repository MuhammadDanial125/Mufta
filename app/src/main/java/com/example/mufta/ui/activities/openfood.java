package com.example.mufta.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mufta.R;
import com.example.mufta.ui.models.foodmodelclass;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class openfood extends AppCompatActivity {
    TextView title, Description, Category, StartingDate, EndingDate, Discount, Location, Contact, Upvote;
    Button avail;
    int upvotecounter;
    String id;
    LinearLayout layout;
    ImageView imgv, plusupvote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openfood);
        Objects.requireNonNull(getSupportActionBar()).hide();
        Intent intent = getIntent();
        title = findViewById(R.id.ctitle);
        Description = findViewById(R.id.cdescription);
        Discount = findViewById(R.id.cdiscount);
        StartingDate = findViewById(R.id.cstartdate);
        EndingDate = findViewById(R.id.cenddate);
        Contact = findViewById(R.id.contact);
        imgv = findViewById(R.id.img);
        Upvote = findViewById(R.id.uvote);
        avail = findViewById(R.id.button);
        plusupvote = findViewById(R.id.plusupvote);
        layout = findViewById(R.id.l);



        title.setText(intent.getStringExtra("Title"));
        Description.setText(intent.getStringExtra("Description"));
        Discount.setText(intent.getStringExtra("Discount"));
        StartingDate.setText(intent.getStringExtra("StartingDate"));
        EndingDate.setText(intent.getStringExtra("EndingDate"));
        Contact.setText(intent.getStringExtra("Contact"));
        Upvote.setText(intent.getStringExtra("Upvote"));
        upvotecounter = Integer.parseInt(Upvote.getText().toString());
        id = intent.getStringExtra("Id");


        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upvotecounter = upvotecounter + 1;
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Discounts").child("Other Discounts").child(id);
                mDatabase.child("upvote").setValue("" + upvotecounter);
                Upvote.setText("" + upvotecounter);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    Upvote.setTextColor(getColor(R.color.colorAccent));
                    plusupvote.setColorFilter(getColor(R.color.colorAccent));
                    layout.setClickable(false);
                }
            }
        });
        Glide.with(this).load(intent.getStringExtra("Image"))
                .placeholder(R.drawable.avatar)
                .into(imgv);
        avail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(intent.getStringExtra("Link")));
                startActivity(browserIntent);
            }
        });
    }
}