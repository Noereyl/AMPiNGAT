package com.ampingat.ampingatapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ampingat.ampingatapplication.models.LoginResponse;
import com.ampingat.ampingatapplication.models.VideoFile;
import com.ampingat.ampingatapplication.services.DownloadIntentService;
import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;


@SuppressWarnings("ALL")
public class LoginActivity extends Activity {

    JSONParser jsonParser = new JSONParser();
    ProgressDialog progressDialog;
    private static String url  = "http://172.20.10.4/ampingat/c_json/login";
    //    private static String url  = "http://192.168.56.1/ampingat/c_json/login";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    @InjectView(R.id.etIdnumber)
    EditText etIdnumber;
    @InjectView(R.id.etPassword)
    EditText etPassword;
    @InjectView(R.id.bLogin)
    Button bLogin;
    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        session = new UserSessionManager(getApplicationContext());

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idnumber = etIdnumber.getText().toString();
                String password = etPassword.getText().toString();

                if (idnumber.length() == 0 || idnumber.length() > 11) {
                    etIdnumber.setError("Please Enter correct ID Number");
                    return;
                }
                if (password.isEmpty() || password.length() < 4 || password.length() > 15) {
                    etPassword.setError("Please Enter correct password!");
                    return;
                }

                ArrayList<VideoFile> videoFiles = new ArrayList<VideoFile>();
                videoFiles.add(new VideoFile("http://172.20.10.4/htdocs/ampingat/uploads/Adult_CPR_-_Lay_Rescuer.mp4"));
                videoFiles.add(new VideoFile("http://172.20.10.4/htdocs/ampingat/uploads/Amputation.mp4"));

                DownloadIntentService downloadIntentService = new DownloadIntentService();
                DownloadIntentService.setSources(videoFiles);
                downloadIntentService.setDownloadProgressCallback(new DownloadIntentService.DownloadProgressCallback() {
                    @Override
                    public void onDownloadProgress(int byteTransfer, int byteDownloaded) { //SECOND TO OCCUR
                        Log.e("DownloadProgress", "byteTransfer:" + byteTransfer + ", byteDownloaded:" + byteDownloaded);
                    }

                    @Override
                    public void onDownloadPrepare(int byteTotalSize, String filename) { //FIRST TO OCCUR
                        Log.e("DownloadProgress", "byteTotalSize:" + byteTotalSize + ", filename:" + filename);
                    }

                    @Override
                    public void onDownloadComplete() {
                        Log.e("DownloadProgress", "onDownloadComplete");
                    }

                    @Override
                    public void onDownloadError() {
                        Log.e("DownloadProgress", "onDownloadError");
                    }
                });
                Intent downloadIntent = new Intent(LoginActivity.this, downloadIntentService.getClass());
                startService(downloadIntent);

                new AttemptLogin().execute();
            }
        });
    }


    class AttemptLogin extends AsyncTask<String, String, Boolean> {

        LoginResponse loginResponse = null;
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(LoginActivity.this,
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setMessage("Authenticating...");
            progressDialog.setIndeterminate(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(true);
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... arg) {

            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            String userid = etIdnumber.getText().toString();
            String password = etPassword.getText().toString();
            params.add(new BasicNameValuePair("id_no", userid));
            params.add(new BasicNameValuePair("password", password));
            Log.d("request!", "starting");

            JSONObject json = jsonParser.makeHttpRequest(url, "POST", params);
            loginResponse = new Gson().fromJson(json.toString(), LoginResponse.class);

            Log.d("Create Response", loginResponse.message);

            try {
                if (loginResponse.success == 1) {
                    Log.d("Successfully Login!", json.toString());
                    return (loginResponse.success == 1 ? true : false);

                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        protected void onPostExecute(Boolean success) {
            progressDialog.dismiss();
            if (success == true) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                i.putExtra("id_no", loginResponse.user.idNo);
                i.putExtra("username", loginResponse.user.username);
                i.putExtra("username", loginResponse.user.password);
                i.putExtra("usertype", loginResponse.user.usertype);
                session.createUserLoginSession(loginResponse.user.username, loginResponse.user.usertype, loginResponse.user.idNo);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();

            }
            if (success != null) {
                Toast.makeText(LoginActivity.this, loginResponse.message, Toast.LENGTH_LONG).show();
            }
        }

    }
}