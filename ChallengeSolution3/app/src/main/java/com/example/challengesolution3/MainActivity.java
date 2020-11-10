package com.example.challengesolution3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int scoreTeamA = 0;
    private int scoreTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tvScoreTeamA = findViewById(R.id.tvScoreTeamA);
        final TextView tvScoreTeamB = findViewById(R.id.tvScoreTeamB);
        Button btnPlusTeamA = findViewById(R.id.btnPlusTeamA);
        Button btnPlusTeamB = findViewById(R.id.btnPlusTeamB);
        Button btnMinusTeamA = findViewById(R.id.btnMinusTeamA);
        Button btnMinusTeamB = findViewById(R.id.btnMinusTeamB);
        Button btnReset = findViewById(R.id.btnReset);

        btnPlusTeamA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreTeamA += 1;
                tvScoreTeamA.setText(String.valueOf(scoreTeamA));
            }
        });

        btnMinusTeamA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreTeamA -= 1;
                if (scoreTeamA < 0){
                    scoreTeamA = 0;
                }
                tvScoreTeamA.setText(String.valueOf(scoreTeamA));
            }
        });

        btnPlusTeamB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreTeamB += 1;
                tvScoreTeamB.setText(String.valueOf(scoreTeamB));
            }
        });

        btnMinusTeamB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreTeamB -= 1;
                if (scoreTeamB < 0){
                    scoreTeamB = 0;
                }
                tvScoreTeamB.setText(String.valueOf(scoreTeamB));
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreTeamA = 0;
                scoreTeamB = 0;
                tvScoreTeamA.setText(String.valueOf(scoreTeamA));
                tvScoreTeamB.setText(String.valueOf(scoreTeamB));
            }
        });
    }
}