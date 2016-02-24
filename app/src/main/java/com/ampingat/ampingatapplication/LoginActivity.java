package com.ampingat.ampingatapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Html;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ampingat.ampingatapplication.helpers.Constants;
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
    private static String url  = "http://" + Constants.DOMAIN_IP + "ampingat/c_json/login";


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
                if (password.length() == 0 || password.length() > 15) {
                    etPassword.setError("Please Enter correct password!");
                    return;
                }

                if(isNetworkOnline())
                    new AttemptLogin().execute();
            }
        });
    }

    public void downloadIntent(LoginResponse loginResponse, final Intent intent) {
        ArrayList<VideoFile> videoFiles = new ArrayList<VideoFile>();
        /*videoFiles.add(new VideoFile("http://" + Constants.DOMAIN_IP + Constants.DOMAIN_UPLOADS + "Adult_CPR_-_Lay_Rescuer.mp4"));
        videoFiles.add(new VideoFile("http://" + Constants.DOMAIN_IP + Constants.DOMAIN_UPLOADS + "Amputation.mp4"));
        videoFiles.add(new VideoFile("http://" + Constants.DOMAIN_IP + Constants.DOMAIN_UPLOADS + "Bleeding_Control_Capillary_Bleeding.mp4"));
        videoFiles.add(new VideoFile("http://" + Constants.DOMAIN_IP + Constants.DOMAIN_UPLOADS + "Bleeding_Control_Venous_Bleeding.mp4"));
        videoFiles.add(new VideoFile("http://" + Constants.DOMAIN_IP + Constants.DOMAIN_UPLOADS + "Burns.mp4"));
        videoFiles.add(new VideoFile("http://" + Constants.DOMAIN_IP + Constants.DOMAIN_UPLOADS + "Conscious_Adult_Choking.mp4"));
        videoFiles.add(new VideoFile("http://" + Constants.DOMAIN_IP + Constants.DOMAIN_UPLOADS + "CPR.mp4"));
        videoFiles.add(new VideoFile("http://" + Constants.DOMAIN_IP + Constants.DOMAIN_UPLOADS + "Eye_Injuries.mp4"));
        videoFiles.add(new VideoFile("http://" + Constants.DOMAIN_IP + Constants.DOMAIN_UPLOADS + "Fainting.mp4"));
        videoFiles.add(new VideoFile("http://" + Constants.DOMAIN_IP + Constants.DOMAIN_UPLOADS + "Hand_Washing.mp4"));
        videoFiles.add(new VideoFile("http://" + Constants.DOMAIN_IP + Constants.DOMAIN_UPLOADS + "Head,_Neck_and_Back_Injuries.mp4"));
        videoFiles.add(new VideoFile("http://" + Constants.DOMAIN_IP + Constants.DOMAIN_UPLOADS + "Heat_Related_Emergencies.mp4"));
        videoFiles.add(new VideoFile("http://" + Constants.DOMAIN_IP + Constants.DOMAIN_UPLOADS + "How_To_Use_An_Epipen.mp4"));
        videoFiles.add(new VideoFile("http://" + Constants.DOMAIN_IP + Constants.DOMAIN_UPLOADS + "Musculoskeletal_Injuries.mp4"));
        videoFiles.add(new VideoFile("http://" + Constants.DOMAIN_IP + Constants.DOMAIN_UPLOADS + "Poison_Control.mp4"));
        videoFiles.add(new VideoFile("http://" + Constants.DOMAIN_IP + Constants.DOMAIN_UPLOADS + "Secondary_Survey.mp4"));
        videoFiles.add(new VideoFile("http://" + Constants.DOMAIN_IP + Constants.DOMAIN_UPLOADS + "Seizure.mp4"));
        videoFiles.add(new VideoFile("http://" + Constants.DOMAIN_IP + Constants.DOMAIN_UPLOADS + "Shock.mp4"));
        videoFiles.add(new VideoFile("http://" + Constants.DOMAIN_IP + Constants.DOMAIN_UPLOADS + "Fire_Eap.mp4"));
        videoFiles.add(new VideoFile("http://" + Constants.DOMAIN_IP + Constants.DOMAIN_UPLOADS + "Earthquake.mp4"));*/
        for (VideoFile videoFile : loginResponse.videofiles) {
            String videoPath = videoFile.videopath;
            String filename = videoPath.substring(videoPath.lastIndexOf("/") + 1, videoPath.length());
            videoFiles.add(new VideoFile("http://" + Constants.DOMAIN_IP + Constants.DOMAIN_UPLOADS + filename));
        }

        progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle(Html.fromHtml("<b><i>Downloading assets...</b></i>"));
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(true);
        progressDialog.show();

        DownloadIntentService downloadIntentService = DownloadIntentService.getInstance();
        downloadIntentService.prepare();
        DownloadIntentService.setSources(videoFiles);
        downloadIntentService.setDownloadProgressCallback(new DownloadIntentService.DownloadProgressCallback() {

            String currentFile = "";

            @Override
            public void onDownloadProgress(int byteTransfer, final int byteDownloaded) { //SECOND TO OCCUR
                Log.e("DownloadProgress", "byteTransfer:" + byteTransfer + ", byteDownloaded:" + byteDownloaded);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.setMessage(currentFile + "[ " + Formatter.formatFileSize(LoginActivity.this, byteDownloaded) + " ]");
                    }
                });
            }

            @Override
            public void onDownloadPrepare(int byteTotalSize, final String filename) { //FIRST TO OCCUR
                Log.e("DownloadProgress", "byteTotalSize:" + byteTotalSize + ", filename:" + filename);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.setMessage("File: " + filename);
                        currentFile = filename;
                    }
                });
            }

            @Override
            public void onDownloadComplete() {
                Log.e("DownloadProgress", "onDownloadComplete");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.setMessage("Download complete!");
                        startActivity(intent);
                        finish();
                    }
                });
            }

            @Override
            public void onDownloadError() {
                Log.e("DownloadProgress", "onDownloadError");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.setMessage("Download Error!");
                    }
                });
            }
        });
        Intent downloadIntent = new Intent(LoginActivity.this, downloadIntentService.getClass());
        startService(downloadIntent);
    }

    public boolean isNetworkOnline() {
        boolean status=false;
        try{
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);
            if (netInfo != null && netInfo.getState()==NetworkInfo.State.CONNECTED) {
                status= true;
            }else {
                netInfo = cm.getNetworkInfo(1);
                if(netInfo!=null && netInfo.getState()== NetworkInfo.State.CONNECTED)
                    status= true;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return status;
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
            Log.e("RESPONSE", json.toString());
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
                session.createUserLoginSession(loginResponse.user.username, loginResponse.user.usertype, loginResponse.user.idNo);
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                i.putExtra("id_no", loginResponse.user.idNo);
                i.putExtra("username", loginResponse.user.username);
                i.putExtra("username", loginResponse.user.password);
                i.putExtra("usertype", loginResponse.user.usertype);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                downloadIntent(loginResponse, i);

            }
            if (success != null) {
                Toast.makeText(LoginActivity.this, loginResponse.message, Toast.LENGTH_LONG).show();
            }
        }
    }
}