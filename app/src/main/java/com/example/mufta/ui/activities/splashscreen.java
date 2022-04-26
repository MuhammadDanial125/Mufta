package com.example.mufta.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.mufta.R;

import java.util.Objects;

public class splashscreen extends AppCompatActivity {
    Animation topanimation, bottomanimation;
    ImageView img, img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        Objects.requireNonNull(getSupportActionBar()).hide();

        topanimation = AnimationUtils.loadAnimation(this, R.anim.animation);
        bottomanimation = AnimationUtils.loadAnimation(this, R.anim.bottomanim);
        img = findViewById(R.id.splash);
        img2 = findViewById(R.id.splash1);
        img.setAnimation(topanimation);
        img2.setAnimation(bottomanimation);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent opensignup = new Intent(splashscreen.this, signin.class);
                startActivity(opensignup);
                finish();
            }
        }, 2000);
    }
}