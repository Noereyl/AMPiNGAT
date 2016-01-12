package com.ampingat.ampingatapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ampingat.ampingatapplication.models.LoginResponse;
import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


@SuppressWarnings("ALL")
public class LoginActivity extends AppCompatActivity {

    JSONParser jsonParser = new JSONParser();
    private static String url  = "http://192.168.1.201/ampingat/c_json/";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    @InjectView(R.id.etIdnumber) EditText etIdnumber;
    @InjectView(R.id.etPassword) EditText etPassword;
    @InjectView(R.id.bLogin) Button bLogin;
    /*@InjectView(R.id.fPassword) TextView fPassword;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idnumber = etIdnumber.getText().toString();
                String password = etPassword.getText().toString();

                if (idnumber.length() == 0 || idnumber.length() > 11) {
                    Toast.makeText(LoginActivity.this, "Please Enter correct ID Number", Toast.LENGTH_LONG).show();
                    return;
                }
                if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
                    Toast.makeText(LoginActivity.this, "Please Enter correct password", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!isOnline(LoginActivity.this)) {
                    Toast.makeText(LoginActivity.this, "No network connection", Toast.LENGTH_LONG).show();
                    return;
                }
                new attemptLogin().execute();
                /*Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                finish();*/
            }

            private boolean isOnline(Context mContext) {
                ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netInfo = cm.getActiveNetworkInfo();
                if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                    return true;
                }
                return false;
            }
        });
    }


    class attemptLogin extends AsyncTask<String, String, Boolean> {

        LoginResponse loginResponse = null;
        ProgressDialog progressDialog;

        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(LoginActivity.this,
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setMessage("Attempting for login...");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(true);
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... arg) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            String userid = etIdnumber.getText().toString();
            String password = etPassword.getText().toString();
            params.add(new BasicNameValuePair("id_no", userid));

            //http://192.168.1.1/ampingat/request_route
            //params:
            //      int:id_no
            //      string:room_num

            params.add(new BasicNameValuePair("password", password));
            Log.d("request!", "starting");

            JSONObject json = jsonParser.makeHttpRequest(url, "POST", params);
            loginResponse = new Gson().fromJson(json.toString(), LoginResponse.class);

            Log.d("Create Response", loginResponse.message + " - " + loginResponse.student.firstname + " - " + loginResponse.videofiles.get(5).videopath);

            try {
                if (loginResponse.success == 1)
                {
                    Log.d("Successfully Login!", json.toString());
                    return (loginResponse.success == 1 ? true : false);
                }
                else
                {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        protected void onPostExecute(Boolean success) {
            progressDialog.dismiss();
            if (success) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                i.putExtra("id_no",loginResponse.student.idNo);
                //TODO use password or not...
                //i.putExtra("password",password);
                startActivity(i);
                finish();
            }
            if (success != null) {
                Toast.makeText(LoginActivity.this, loginResponse.message, Toast.LENGTH_LONG).show();
            }
        }

    }
}
