package com.ampingat.ampingatapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class AboutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(AboutActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
