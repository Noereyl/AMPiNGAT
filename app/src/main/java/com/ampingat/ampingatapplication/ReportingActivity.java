package com.ampingat.ampingatapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ampingat.ampingatapplication.models.SendReportResponse;
import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


@SuppressWarnings("ALL")
public class ReportingActivity extends Activity {

    JSONParser jsonParser = new JSONParser();
    private static String url  = "http://192.168.56.1/ampingat/c_report";
    @InjectView(R.id.EmergencyType) EditText etEtype;
    @InjectView(R.id.Location) EditText etLocation;
    @InjectView(R.id.Remarks) EditText etRemarks;
    @InjectView(R.id.bSend) Button bSend;
    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporting);
        ButterKnife.inject(this);

        session = new UserSessionManager(getApplicationContext());

        bSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = etEtype.getText().toString();
                String location = etLocation.getText().toString();
                String remarks = etRemarks.getText().toString();
                if (type.length() == 0) {
                    etEtype.setError("This field is empty!");
                    return;
                }
                if (location.length() == 0) {
                    etLocation.setError("This field is empty!");
                    return;
                }

                new attemptSend().execute();
            }
        });
    }

    class attemptSend extends AsyncTask<String, String, Boolean> {

        SendReportResponse sendReportResponse = null;
        ProgressDialog progressDialog;

        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(ReportingActivity.this,
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setMessage("Sending...");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(true);
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... arg) {

            HashMap<String, String> user = session.getUserDetails();
            String type = etEtype.getText().toString();
            String location = etLocation.getText().toString();
            String remarks = etRemarks.getText().toString();
            String message = type + " at " + location + " .. " + remarks;


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            String userid = user.get(UserSessionManager.KEY_ID_NUMBER);
            params.add(new BasicNameValuePair("id_no", userid));
            params.add(new BasicNameValuePair("message", message));
            Log.d("request!", "starting");

            JSONObject json = jsonParser.makeHttpRequest(url, "POST", params);
            sendReportResponse = new Gson().fromJson(json.toString(), SendReportResponse.class);

            Log.d("Create Response", sendReportResponse.message);

            try {
                if (sendReportResponse.success == 1)
                {
                    Log.d("Successfully Login!", json.toString());
                    return (sendReportResponse.success == 1 ? true : false);
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
            etEtype.setText("");
            etRemarks.setText("");
            etLocation.setText("");

            if (success) {
                Toast.makeText(ReportingActivity.this, sendReportResponse.message, Toast.LENGTH_LONG).show();
            }
        }

    }

}


