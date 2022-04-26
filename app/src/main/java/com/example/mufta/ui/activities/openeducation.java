package com.example.mufta.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mufta.R;

import java.util.Objects;

public class openeducation extends AppCompatActivity {
    TextView title,detail, website, time, discountde, originalprice, discountedprice, expirydate;
    Button getdiscount;
    ImageView iview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openeducation);
        Objects.requireNonNull(getSupportActionBar()).hide();
        Intent intent = getIntent();
        title = findViewById(R.id.etitle);
        detail = findViewById(R.id.edetails);
        website = findViewById(R.id.ewebsite);
        time = findViewById(R.id.etimeduration);
        discountde = findViewById(R.id.ediscount);
        originalprice = findViewById(R.id.eoriginal);
        discountedprice = findViewById(R.id.ediscounted);
        expirydate = findViewById(R.id.eexpiry);
        getdiscount = findViewById(R.id.btn);
        iview = findViewById(R.id.i);
        switch (intent.getStringExtra("website")) {
            case "Udemy":
                Glide.with(this).load(R.drawable.udemy)
                        .into(iview);
                break;
            case "Udacity":
                Glide.with(this).load(R.drawable.udacity)
                        .into(iview);
                break;
            case "Coursera":
                Glide.with(this).load(R.drawable.courseera)
                        .into(iview);
                break;
            case "edX":
                Glide.with(this).load(R.drawable.edx)
                        .into(iview);
                break;
            case "Datacamp":
                Glide.with(this).load(R.drawable.datacamp)
                        .into(iview);
                break;
            default:
        }
        title.setText(intent.getStringExtra("discount"));
        detail.setText(intent.getStringExtra("details"));
        website.setText(intent.getStringExtra("website"));
        time.setText(intent.getStringExtra("time"));
        discountde.setText(intent.getStringExtra("title"));
        originalprice.setText(intent.getStringExtra("oprice"));
        discountedprice.setText(intent.getStringExtra("dprice"));
        originalprice.setPaintFlags(originalprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        expirydate.setText(intent.getStringExtra("expiry"));
        getdiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(intent.getStringExtra("link")));
                startActivity(browserIntent);
            }
        });
    }
}