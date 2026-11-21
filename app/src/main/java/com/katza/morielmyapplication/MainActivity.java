package com.katza.morielmyapplication;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements
        SeekBar.OnSeekBarChangeListener,
        View.OnClickListener,
        CompoundButton.OnCheckedChangeListener {

     TextView tvUserName;
     Button btnSendMessage;
     Button btnFollow;
    CompoundButton mySwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvUserName = findViewById(R.id.tvUserName);
        btnSendMessage = findViewById(R.id.btnSendMessage);
        btnFollow = findViewById(R.id.btnFollow);

        btnSendMessage.setOnClickListener(this);
        btnFollow.setOnClickListener(this);


        // חיבור המתג
        mySwitch = findViewById(R.id.switch1);
        mySwitch.setOnCheckedChangeListener(this);
        mySwitch.setChecked(false);

        btnSendMessage.setVisibility(View.GONE);
        btnFollow.setVisibility(View.GONE);


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btnSendMessage) {
            Toast.makeText(this, R.string.opening_chat, Toast.LENGTH_SHORT).show();
        } else if (id == R.id.btnFollow) {
            Toast.makeText(this, R.string.started_following, Toast.LENGTH_SHORT).show();
            btnFollow.setText(R.string.following);
        }
    }
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onCheckedChanged(@NonNull CompoundButton compoundButton, boolean b) {

        if (b) {
            btnSendMessage.setVisibility(VISIBLE);
            btnFollow.setVisibility(VISIBLE);



            Toast.makeText(this, "true", Toast.LENGTH_SHORT).show();
        } else {
            btnSendMessage.setVisibility(INVISIBLE);
            btnFollow.setVisibility(INVISIBLE);



            Toast.makeText(this, "false", Toast.LENGTH_SHORT).show();
        }
    }
}

