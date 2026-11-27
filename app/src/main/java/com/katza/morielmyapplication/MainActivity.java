
package com.katza.morielmyapplication;

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

import java.util.Random;

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
    EditText etMin, etMax;

    Button btnCheck, btnPlayAgain, btnStart;

    TextView tvResult, tvCount, tvHistory;

    int count = 0;
    int secretNumber;

    int min;
    int max;

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

        mySwitch = findViewById(R.id.switch1);

        etGuess = findViewById(R.id.etGuess);
        etMin = findViewById(R.id.etMin);
        etMax = findViewById(R.id.etMax);

        btnCheck = findViewById(R.id.btnCheck);
        btnPlayAgain = findViewById(R.id.btnPlayAgain);
        btnStart = findViewById(R.id.btnStart);

        tvResult = findViewById(R.id.tvResult);
        tvCount = findViewById(R.id.tvCount);
        tvHistory = findViewById(R.id.tvHistory);

        mySeekBar.setOnSeekBarChangeListener(this);
        btnSendMessage.setOnClickListener(this);
        btnFollow.setOnClickListener(this);
        mySwitch.setOnCheckedChangeListener(this);

        btnStart.setOnClickListener(this);
        btnCheck.setOnClickListener(this);
        btnPlayAgain.setOnClickListener(this);

        mySwitch.setChecked(false);

        btnSendMessage.setVisibility(View.GONE);
        btnFollow.setVisibility(View.GONE);

        btn_img1.setAlpha(0f);
        btn_img2.setAlpha(1f);

        tvResult.setText("בחר טווח מספרים ולחץ על התחל משחק");
        btnCheck.setEnabled(false);
    }

    public void startGameFromRange() {

        String minText = etMin.getText().toString().trim();
        String maxText = etMax.getText().toString().trim();

        if (minText.isEmpty() || maxText.isEmpty()) {
            tvResult.setText("אנא הכנס את שני המספרים");
            return;
        }

        try {
            min = Integer.parseInt(minText);
            max = Integer.parseInt(maxText);

        } catch (NumberFormatException e) {
            tvResult.setText("אנא הכנס מספרים חוקיים");
            return;
        }

        if (min < 1 || max > 100 || min > max) {
            tvResult.setText("בחר טווח תקין בין 1 ל-100");
            return;
        }

        startNewGame();
    }

    public void startNewGame() {

        Random random = new Random();

        secretNumber = random.nextInt(max - min + 1) + min;

        count = 0;

        tvCount.setText("מספר ניסיונות: 0");

        tvResult.setText("נחש מספר בין " + min + " ל-" + max);

        tvHistory.setText("הניחושים הקודמים:\n");

        etGuess.setText("");

        etGuess.setEnabled(true);
        btnCheck.setEnabled(true);

        btnPlayAgain.setVisibility(View.GONE);
    }

    public void checkGuess() {

        String guessText = etGuess.getText().toString().trim();

        if (guessText.isEmpty()) {
            tvResult.setText("אנא הכנס מספר חוקי");
            return;
        }

        int guess;

        try {
            guess = Integer.parseInt(guessText);

        } catch (NumberFormatException e) {
            tvResult.setText("אנא הכנס מספר חוקי");
            return;
        }

        if (guess < min || guess > max) {
            tvResult.setText("אנא הכנס מספר בין " + min + " ל-" + max);
            return;
        }

        count++;

        tvCount.setText("מספר ניסיונות: " + count);

        if (count == 1) {
            tvHistory.setText("הניחושים הקודמים: " + guess);
        } else {
            tvHistory.append(", " + guess);
        }

        if (guess > secretNumber) {

            tvResult.setText("המספר גדול מדי, נסה מספר קטן יותר ⬇️");

        } else if (guess < secretNumber) {

            tvResult.setText("המספר קטן מדי, נסה מספר גדול יותר ⬆️");

        } else {

            tvResult.setText("כל הכבוד! גילית את המספר הנכון ב-"
                    + count + " צעדים!");

            etGuess.setEnabled(false);
            btnCheck.setEnabled(false);

            btnPlayAgain.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        if (id == R.id.btnSendMessage) {

            Toast.makeText(this, R.string.opening_chat,
                    Toast.LENGTH_SHORT).show();

        } else if (id == R.id.btnFollow) {

            Toast.makeText(this, R.string.started_following,
                    Toast.LENGTH_SHORT).show();

            btnFollow.setText(R.string.following);

        } else if (id == R.id.btnStart) {

            startGameFromRange();

        } else if (id == R.id.btnCheck) {

            checkGuess();

        } else if (id == R.id.btnPlayAgain) {

            startNewGame();
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
    public void onCheckedChanged(@NonNull CompoundButton compoundButton,
                                 boolean b) {

        if (b) {

            btnSendMessage.setVisibility(View.VISIBLE);
            btnFollow.setVisibility(View.VISIBLE);

            Toast.makeText(this, "true",
                    Toast.LENGTH_SHORT).show();

        } else {

            btnSendMessage.setVisibility(View.INVISIBLE);
            btnFollow.setVisibility(View.INVISIBLE);

            Toast.makeText(this, "false",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
