package com.ampingat.ampingatapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;


public class SplashScreen extends Activity {
    ProgressBar progressBar;
    int progressStatus = 0;
    TextView textView;
    Handler handler = new Handler();
    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        progressBar = (ProgressBar) findViewById((R.id.progressBar));
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (progressStatus == 100) {
                    session = new UserSessionManager(getApplicationContext());
                    Intent intent;
                    if (session.checkLogin()) {
                        intent = new Intent(SplashScreen.this, MainActivity.class);
                    } else {
                        intent = new Intent(SplashScreen.this, LoginActivity.class);
                    }
                    startActivity(intent);
                    finish();
                }
            }
        }).start();
    }
}