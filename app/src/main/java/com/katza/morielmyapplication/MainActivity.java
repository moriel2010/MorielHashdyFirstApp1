package com.katza.morielmyapplication;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
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

    ImageView btn_img1, btn_img2;
    SeekBar mySeekBar;
    EditText etGuess;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvUserName = findViewById(R.id.tvUserName);
        btnSendMessage = findViewById(R.id.btnSendMessage);
        btnFollow = findViewById(R.id.btnFollow);
        mySeekBar = findViewById(R.id.mySeekBar);
        btn_img1 = findViewById(R.id.btn_img1);
        btn_img2 = findViewById(R.id.btn_img2);
        etGuess = findViewById(R.id.etGuess);


        mySeekBar.setOnSeekBarChangeListener(this);
        btnSendMessage.setOnClickListener(this);
        btnFollow.setOnClickListener(this);
        etGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
            }
        });

        // חיבור המתג
        mySwitch = findViewById(R.id.switch1);
        mySwitch.setOnCheckedChangeListener(this);
        mySwitch.setChecked(false);

        btnSendMessage.setVisibility(View.GONE);
        btnFollow.setVisibility(View.GONE);
        btn_img1.setAlpha(0f);
        btn_img2.setAlpha(1f);

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
            float alpha = i / 100.0f;
            float alpha2 = 1.0f - alpha;

            btn_img1.setAlpha(alpha);
            btn_img2.setAlpha(alpha2);
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
