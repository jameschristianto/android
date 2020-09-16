package com.example.inputusernameandpassword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = (Button) findViewById(R.id.button);
        username = (EditText) findViewById(R.id.textView2);
        password = (EditText) findViewById(R.id.textView4);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //((EditText) findViewById(R.id.textView2)).getText().clear();
                username.getText().clear(); //Clear username bar
                Log.i("Edit Text", "Username cleared");

                //((EditText) findViewById(R.id.textView4)).getText().clear();
                password.getText().clear(); //Clear password bar
                Log.i("Edit Text", "Password Cleared");

                Toast.makeText(getApplicationContext(), "Submitted", Toast.LENGTH_SHORT).show();
                Log.i("Toast", "Submitted");
            }
        });
    }
}