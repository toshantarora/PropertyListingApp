package com.example.propertylisting.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.propertylisting.R;
import com.example.propertylisting.app.SessionManager;
import com.example.propertylisting.utility.Const;

public class SplashScreenActivity extends Activity
{
    private static final String TAG = "SplashScreenActivity";

    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate: started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SessionManager sessionManager = new SessionManager(this);

        thread = new Thread()
        {
            public void run()
            {
                try
                {
                    synchronized(this){
                        wait(3000);
                    }
                }
                catch (Exception e) {
                    // TODO: handle exception
                }

                if(sessionManager.ifSessionExists()) {
                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        thread.start();
    }
}
