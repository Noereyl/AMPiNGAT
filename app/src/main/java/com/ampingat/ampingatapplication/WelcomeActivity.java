package com.ampingat.ampingatapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;


public class WelcomeActivity extends Activity {

    UserSessionManager session;
    Button btnLogout, btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        session = new UserSessionManager(getApplicationContext());
        TextView lbName = (TextView) findViewById(R.id.lblName);
        TextView lbId = (TextView) findViewById(R.id.lblId);
        TextView lbType = (TextView) findViewById(R.id.lblType);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnHome = (Button) findViewById(R.id.btnHome);

        Toast.makeText(getApplicationContext(),
                "User Login Status:" + session.isUserLoggedIn(), Toast.LENGTH_LONG).show();

        session.checkLogin();

        HashMap<String, String> user = session.getUserDetails();

        String userid = user.get(UserSessionManager.KEY_ID_NUMBER);
        String name = user.get(UserSessionManager.KEY_NAME);
        String type = user.get(UserSessionManager.KEY_TYPE);

        lbName.setText(Html.fromHtml(" <i>" + name + "</i>"));
        lbId.setText(Html.fromHtml(" <b>" + userid + "</b>"));
        lbType.setText(Html.fromHtml(" <b>" + type + "</b>"));


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                session.logoutUser();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
