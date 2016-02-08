package com.ampingat.ampingatapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;


public class ThirdFloorActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_floor);
        ImageView iv = (ImageView) findViewById(R.id.customImageView);
        iv.setOnTouchListener(new Touch());
    }
}
