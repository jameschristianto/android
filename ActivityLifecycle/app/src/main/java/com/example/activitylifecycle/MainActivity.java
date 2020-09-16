/****************************************************************************************************
Activity Lifecycle
 1. When the app is running for the first time
    onCreate -> onStart -> onResume

 2. When the app is out (the app is still running)
    onPause -> onStop

 3. when the app is closed (the app is not running)
    onPause -> onStop -> onDestroy

 4. when the app is opening again (not start for the first time)
    onRestart -> onStart -> onResume

 5. when the app is moving from activity 1 to activity 2 (activity 2 is running for the first time)
    activity 1 : onPause
    activity 2 : onCreate -> onStart -> onResume

 6. when the app is moving back from activity 2 to activity 1 (activity 1 is running)
    activity 1 : onResume
    activity 2 : onPause
 ****************************************************************************************************/

package com.example.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "Activity Lifecycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "This is OnCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "This is OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "This is OnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "This is OnPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "This is OnStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "This is OnRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "This is OnDestroy");
    }
}