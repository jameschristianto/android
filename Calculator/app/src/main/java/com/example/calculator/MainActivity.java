package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNumber1;
    private EditText etNumber2;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Cara koneksi antara xml dengan java
        etNumber1 = findViewById(R.id.etNumber1);
        etNumber2 = findViewById(R.id.etNumber2);

        tvResult = findViewById(R.id.tvResult);
    }

    public void plus(View view) {
        String number1 = etNumber1.getText().toString();
        String number2 = etNumber2.getText().toString();

        double result = Double.parseDouble(number1) + Double.parseDouble(number2);
        tvResult.setText(String.valueOf(result));
    }

    public void minus(View view){
        String number1 = etNumber1.getText().toString();
        String number2 = etNumber2.getText().toString();

        double result = Double.parseDouble(number1) - Double.parseDouble(number2);
        tvResult.setText(String.valueOf(result));
    }

    public void multiple(View view) {
        String number1 = etNumber1.getText().toString();
        String number2 = etNumber2.getText().toString();

        double result = Double.parseDouble(number1) * Double.parseDouble(number2);
        tvResult.setText(String.valueOf(result));
    }

    public void divide(View view) {
        String number1 = etNumber1.getText().toString();
        String number2 = etNumber2.getText().toString();

        double result = Double.parseDouble(number1) / Double.parseDouble(number2);
        tvResult.setText(String.valueOf(result));
    }
}