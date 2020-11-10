package com.example.challenge3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvScoreA;
    private TextView tvScoreB;
    private Button btnPlusA;
    private Button btnPlusB;
    private Button btnMinusA;
    private Button btnMinusB;
    private Button btnReset;
    private String srScore;
    private int inScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvScoreA = findViewById(R.id.tvScoreA);
        tvScoreB = findViewById(R.id.tvScoreB);
        btnPlusA = findViewById(R.id.btnPlusA);
        btnPlusB = findViewById(R.id.btnPlusB);
        btnMinusA = findViewById(R.id.btnMinusA);
        btnMinusB = findViewById(R.id.btnMinusB);
        btnReset = findViewById(R.id.btnReset);

        btnPlusA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                srScore = tvScoreA.getText().toString();
                inScore = Integer.parseInt(srScore);
                inScore += 1;
                tvScoreA.setText(Integer.toString(inScore));
            }
        });

        btnPlusB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                srScore = tvScoreB.getText().toString();
                inScore = Integer.parseInt(srScore);
                inScore += 1;
                tvScoreB.setText(Integer.toString(inScore));
            }
        });

        btnMinusA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                srScore = tvScoreA.getText().toString();
                inScore = Integer.parseInt(srScore);

                if(inScore > 0) {
                    inScore -= 1;
                    tvScoreA.setText(Integer.toString(inScore));
                }
            }
        });

        btnMinusB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                srScore = tvScoreB.getText().toString();
                inScore = Integer.parseInt(srScore);

                if(inScore > 0) {
                    inScore -= 1;
                    tvScoreB.setText(Integer.toString(inScore));
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvScoreA.setText(Integer.toString(0));
                tvScoreB.setText(Integer.toString(0));
            }
        });
    }
}