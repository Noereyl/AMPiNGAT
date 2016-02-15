package com.ampingat.ampingatapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;


public class UserProfileActivity extends Activity {

    UserSessionManager session;
    Button btnChangePass, btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        session = new UserSessionManager(getApplicationContext());
        TextView lbName = (TextView) findViewById(R.id.lblName);
        TextView lbId = (TextView) findViewById(R.id.lblId);
        TextView lbType = (TextView) findViewById(R.id.lblType);
        btnChangePass = (Button) findViewById(R.id.btnChangePass);
        btnHome = (Button) findViewById(R.id.btnHome);

        HashMap<String, String> user = session.getUserDetails();

        String userid = user.get(UserSessionManager.KEY_ID_NUMBER);
        String name = user.get(UserSessionManager.KEY_NAME);
        String type = user.get(UserSessionManager.KEY_TYPE);

        lbName.setText(Html.fromHtml(" <i>" + name + "</i>"));
        lbId.setText(Html.fromHtml(" <b>" + userid + "</b>"));
        lbType.setText(Html.fromHtml(" <b>" + type + "</b>"));


        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserProfileActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserProfileActivity.this, ChangePasswordActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
