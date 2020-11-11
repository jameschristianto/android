package com.example.viewmodel;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    private TextView tvScoreA;
    private TextView tvScoreB;
    private Button btnPlusA;
    private Button btnPlusB;
    private Button btnMinusA;
    private Button btnMinusB;
    private Button btnReset;

    private ScoreViewModel scoreViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreViewModel = new ViewModelProvider(this).get(ScoreViewModel.class);

//        Observer<Integer> scoreObserverA = new Observer<Integer>() {
//            @Override
//            public void onChanged(@Nullable Integer a) {
//                tvScoreA.setText(String.valueOf(a));
//            }
//        };
//        scoreViewModel.getScoreA().observe(this, scoreObserverA);
//
//        Observer<Integer> scoreObserverB = new Observer<Integer>() {
//            @Override
//            public void onChanged(@Nullable Integer b) {
//                tvScoreB.setText(String.valueOf(b));
//            }
//        };
//        scoreViewModel.getScoreB().observe(this, scoreObserverB);

        scoreViewModel.getScoreA().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tvScoreA.setText(String.valueOf(integer));
            }
        });

        scoreViewModel.getScoreB().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                    tvScoreB.setText(String.valueOf(integer));
            }
        });

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
                scoreViewModel.addScoreA();
            }
        });

        btnPlusB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreViewModel.addScoreB();
            }
        });

        btnMinusA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreViewModel.minusScoreA();
            }
        });

        btnMinusB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreViewModel.minusScoreB();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreViewModel.reset();
            }
        });
    }
}