package com.lawcboat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    CardView controls, credits;
    Animation animationForOnCreateEngineControls, animationForOnCreateControls, animationForOnCreateCredits;
    Animation animationForClickOnEngineControls, animationForClickOnControls, animationForClickOnCredits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        credits = findViewById(R.id.credits);
        controls = findViewById(R.id.controls);
        animationForOnCreateEngineControls = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.on_create_animation);
        animationForOnCreateControls = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.on_create_animation);
        animationForOnCreateCredits = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.on_create_animation);

        animationForClickOnEngineControls = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.on_click_animation);
        animationForClickOnControls = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.on_click_animation);
        animationForClickOnCredits = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.on_click_animation);

        controls.startAnimation(animationForOnCreateControls);
        credits.startAnimation(animationForOnCreateCredits);

        controls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controls.startAnimation(animationForClickOnControls);
                animationForClickOnControls.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        Log.d("Animation ControlsClick", "Started");
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        Log.d("Animation ControlsClick", "Ended");
                        Intent intent = new Intent(getApplicationContext(), Controls.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });

        credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                credits.startAnimation(animationForClickOnCredits);
                animationForClickOnCredits.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                        Log.d("Animation:Credits Click", "Started");
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        Log.d("Animation:Credits Click", "Ended");
                        Intent intent = new Intent(getApplicationContext(), credits.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });

    }

}
