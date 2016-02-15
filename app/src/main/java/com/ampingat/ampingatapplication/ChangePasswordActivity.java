package com.ampingat.ampingatapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ampingat.ampingatapplication.models.ChangePassResponse;
import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class ChangePasswordActivity extends Activity {

    JSONParser jsonParser = new JSONParser();
    private static String url  = "http://172.20.10.2/ampingat/c_json/changepass";

    @InjectView(R.id.txtOldPass) EditText etOldpass;
    @InjectView(R.id.txtNewPass) EditText etNewpass;
    @InjectView(R.id.bChangepass) Button bChangePass;
    @InjectView(R.id.bCancel) Button bCancel;

    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.inject(this);

        session = new UserSessionManager(getApplicationContext());


        bChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String old = etOldpass.getText().toString();
                String newpass = etNewpass.getText().toString();
                if (old.length() == 0) {
                    etOldpass.setError("This field is empty!");
                    return;
                }
                if (newpass.length() == 0) {
                    etNewpass.setError("This field is empty!");
                    return;
                }
                new ChangePassword().execute();
            }
        });
        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ChangePasswordActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    @SuppressWarnings("deprecation")
    class ChangePassword extends AsyncTask<String, String, Boolean> {

        ChangePassResponse changePassRes = null;
        ProgressDialog progressDialog;

        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(ChangePasswordActivity.this,
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setMessage("Changing Password on progress...");
            progressDialog.setIndeterminate(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(true);
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... arg) {

            HashMap<String, String> user = session.getUserDetails();
            String old = etOldpass.getText().toString();
            String newpass= etNewpass.getText().toString();

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            String userid = user.get(UserSessionManager.KEY_TYPE);

            params.add(new BasicNameValuePair("id_no", userid));
            params.add(new BasicNameValuePair("oldpass", old));
            params.add(new BasicNameValuePair("newpass", newpass));
            Log.d("request!", "starting");

            JSONObject json = jsonParser.makeHttpRequest(url, "POST", params);
            changePassRes = new Gson().fromJson(json.toString(), ChangePassResponse.class);

            Log.d("Create Response", changePassRes.message);

            try {
                if (changePassRes.success == 1)
                {
                    Log.d("Successfully Login!", json.toString());
                    return (changePassRes.success == 1 ? true : false);
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
            etOldpass.setText("");
            etNewpass.setText("");
            Intent i = new Intent(ChangePasswordActivity.this, MainActivity.class);
            Toast.makeText(ChangePasswordActivity.this, changePassRes.message, Toast.LENGTH_LONG).show();
            startActivity(i);
            finish();
        }

    }

}