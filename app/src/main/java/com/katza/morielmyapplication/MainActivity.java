package com.katza.morielmyapplication;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements
        SeekBar.OnSeekBarChangeListener,
        View.OnClickListener,
        CompoundButton.OnCheckedChangeListener {

    Button button2;
    CompoundButton mySwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this);


        // חיבור המתג
        mySwitch = findViewById(R.id.switch1);
        mySwitch.setOnCheckedChangeListener(this);
        mySwitch.setChecked(false);

        button2.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        if (id == R.id.button2) {
            Log.d("Noam", "clicked");
            Toast.makeText(this, "Noam", Toast.LENGTH_SHORT).show();
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
            button2.setVisibility(VISIBLE);

            Toast.makeText(this, "true", Toast.LENGTH_SHORT).show();
        } else {
            button2.setVisibility(INVISIBLE);

            Toast.makeText(this, "false", Toast.LENGTH_SHORT).show();
        }
    }
}

