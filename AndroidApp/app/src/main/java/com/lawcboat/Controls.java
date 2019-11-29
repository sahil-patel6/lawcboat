package com.lawcboat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.suke.widget.SwitchButton;

import java.io.IOException;
import java.io.InputStream;

import android.bluetooth.BluetoothSocket;

public class Controls extends AppCompatActivity {

    ImageView backButton, forwardLeft, forwardRight, forward, backward, backwardLeft, backwardRight;
    RelativeLayout stop;
    CardView conveyorBeltControls, vacuumControls,engineControls,gatesControls;
    Animation animationForOnCreate, animationForOnClick;
    SwitchButton conveyorBeltSwitch, vacuumSwitch,engineSwitch,gatesSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controls);
        backButton = findViewById(R.id.backButton);

        animationForOnCreate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.on_create_animation);
        animationForOnClick = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.on_click_animation);

        conveyorBeltSwitch = findViewById(R.id.conveyor_belt_switch_button);
        vacuumSwitch = findViewById(R.id.vacuum_switch_button);
        engineSwitch = findViewById(R.id.engine_switch_button);
        gatesSwitch = findViewById(R.id.gates_switch_button);

        forward = findViewById(R.id.forward);
        forwardLeft = findViewById(R.id.forward_left);
        forwardRight = findViewById(R.id.forward_right);


        backward = findViewById(R.id.backward);
        backwardLeft = findViewById(R.id.backward_left);
        backwardRight = findViewById(R.id.backward_right);
        stop = findViewById(R.id.stop);

        conveyorBeltControls = findViewById(R.id.conveyorBeltControls);
        vacuumControls = findViewById(R.id.vacuumControls);
        engineControls = findViewById(R.id.engineControls);
        gatesControls = findViewById(R.id.gatesControls);

        forward.startAnimation(animationForOnCreate);
        forwardRight.startAnimation(animationForOnCreate);
        forwardLeft.startAnimation(animationForOnCreate);

        backward.startAnimation(animationForOnCreate);
        backwardLeft.startAnimation(animationForOnCreate);
        backwardRight.startAnimation(animationForOnCreate);

        stop.startAnimation(animationForOnCreate);

        conveyorBeltControls.startAnimation(animationForOnCreate);
        vacuumControls.startAnimation(animationForOnCreate);
        engineControls.startAnimation(animationForOnCreate);
        gatesControls.startAnimation(animationForOnCreate);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Controls.super.onBackPressed();
            }
        });

        forwardLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forwardLeft.startAnimation(animationForOnClick);
                checkIfDataSend(BTHandler.sendDataViaBluetooth("a"));

            }
        });
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forward.startAnimation(animationForOnClick);
                checkIfDataSend(BTHandler.sendDataViaBluetooth("w"));
            }
        });
        forwardRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forwardRight.startAnimation(animationForOnClick);
                checkIfDataSend(BTHandler.sendDataViaBluetooth("d"));
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stop.startAnimation(animationForOnClick);
                checkIfDataSend(BTHandler.sendDataViaBluetooth("s"));
            }
        });
        backwardLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backwardLeft.startAnimation(animationForOnClick);
                checkIfDataSend(BTHandler.sendDataViaBluetooth("j"));
            }
        });
        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backward.startAnimation(animationForOnClick);
                checkIfDataSend(BTHandler.sendDataViaBluetooth("k"));
            }
        });
        backwardRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backwardRight.startAnimation(animationForOnClick);

                checkIfDataSend(BTHandler.sendDataViaBluetooth("l"));
            }
        });

        conveyorBeltControls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conveyorBeltControls.startAnimation(animationForOnClick);
                conveyorBeltSwitch.setChecked(!conveyorBeltSwitch.isChecked());
            }
        });
        vacuumControls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vacuumControls.startAnimation(animationForOnClick);
                vacuumSwitch.setChecked(!vacuumSwitch.isChecked());
            }
        });

        conveyorBeltSwitch.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) {
                    Log.d("conveyor belt switch", "turned on");
                } else {
                    Log.d("conveyor belt switch", "turned off");
                }
                checkIfDataSend(BTHandler.sendDataViaBluetooth("c"));
            }
        });

        vacuumSwitch.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) {
                    Log.d("vacuum switch", "turned on");
                } else {
                    Log.d("vacuum switch", "turned off");
                }

                checkIfDataSend(BTHandler.sendDataViaBluetooth("v"));
            }
        });

        engineControls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                engineControls.startAnimation(animationForOnClick);
                engineSwitch.setChecked(!engineSwitch.isChecked());
            }
        });

        engineSwitch.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) {
                    Log.d("Engine switch", "turned on");
                } else {
                    Log.d("Engine switch", "turned off");
                }

                checkIfDataSend(BTHandler.sendDataViaBluetooth("e"));
            }
        });

        gatesControls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gatesControls.startAnimation(animationForOnClick);
                gatesSwitch.setChecked(!gatesSwitch.isChecked());
            }
        });

        gatesSwitch.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) {
                    Log.d("Gates switch", "turned on");
                } else {
                    Log.d("Gates switch", "turned off");
                }

                checkIfDataSend(BTHandler.sendDataViaBluetooth("g"));
            }
        });

        connect();
    }

    static class connectToDevice extends AsyncTask<Void, Void, Boolean> {
        @SuppressLint("StaticFieldLeak")
        Context context;

        connectToDevice(Context c) {
            this.context = c;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                return BTHandler.bluetooth_connect_device();
            } catch (Exception e) {
                Log.d("bluetooth connect", "Error");
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean isConnected) {
            super.onPostExecute(isConnected);
            if (isConnected) {
                Toast.makeText(context, "Device Connected Successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Device Connection Failure", Toast.LENGTH_SHORT).show();
            }
        }

    }


    void connect() {
        new connectToDevice(this).execute();
    }


    void checkIfDataSend(boolean isSent) {
        if (isSent) {
            Toast.makeText(getApplicationContext(), "Sent Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Error while sending data", Toast.LENGTH_SHORT).show();
        }
    }

}