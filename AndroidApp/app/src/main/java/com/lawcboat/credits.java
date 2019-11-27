package com.lawcboat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class credits extends AppCompatActivity {
    ImageView backButton;
    TextView teamTitle,specialThanksTitle;
    CardView sahil,prateek,manthan,hassan,laukik;
    Animation animationForSahil, animationForPrateek, animationForManthan, animationForhassan, animationForLaukik,animationForteamTitle,animationForspecialThanksTitle;
    Animation animationForClickOnSahil, animationForClickOnPrateek,animationForClickOnManthan,animationForClickOnhassan,animationForClickOnLaukik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        backButton = findViewById(R.id.backButton);
        sahil = findViewById(R.id.sahil);
        prateek = findViewById(R.id.prateek);
        manthan = findViewById(R.id.manthan);
        hassan = findViewById(R.id.hassan);
        laukik = findViewById(R.id.laukik);
        teamTitle = findViewById(R.id.teamTitle);
        specialThanksTitle = findViewById(R.id.special_thanks_title);

        animationForSahil = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.on_create_animation);
        animationForPrateek = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.on_create_animation);
        animationForManthan = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.on_create_animation);
        animationForhassan = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.on_create_animation);
        animationForLaukik = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.on_create_animation);
        animationForteamTitle = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.on_create_animation);
        animationForspecialThanksTitle = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.on_create_animation);

        animationForClickOnSahil = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.on_click_animation);
        animationForClickOnPrateek = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.on_click_animation);
        animationForClickOnManthan = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.on_click_animation);
        animationForClickOnhassan = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.on_click_animation);
        animationForClickOnLaukik = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.on_click_animation);

        sahil.startAnimation(animationForSahil);
        prateek.startAnimation(animationForPrateek);
        manthan.startAnimation(animationForManthan);
        hassan.startAnimation(animationForhassan);
        laukik.startAnimation(animationForLaukik);
        teamTitle.startAnimation(animationForteamTitle);
        specialThanksTitle.startAnimation(animationForspecialThanksTitle);

        sahil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sahil.startAnimation(animationForClickOnSahil);
            }
        });

        prateek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prateek.startAnimation(animationForClickOnPrateek);
            }
        });

        manthan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manthan.startAnimation(animationForClickOnManthan);
            }
        });

        hassan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hassan.startAnimation(animationForClickOnhassan);
            }
        });

        laukik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                laukik.startAnimation(animationForClickOnLaukik);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                credits.super.onBackPressed();
            }
        });
    }
}
