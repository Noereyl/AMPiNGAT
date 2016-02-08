package com.ampingat.ampingatapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;


public class SecondFloorActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_floor);
        ImageView iv = (ImageView) findViewById(R.id.customImageView);
        iv.setOnTouchListener(new Touch());
    }
}
