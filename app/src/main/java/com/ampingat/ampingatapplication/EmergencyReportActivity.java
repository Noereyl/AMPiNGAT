package com.ampingat.ampingatapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ampingat.ampingatapplication.helpers.Constants;
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


public class EmergencyReportActivity extends Activity {

    JSONParser jsonParser = new JSONParser();
    private static String url  = "http://" + Constants.DOMAIN_IP + "ampingat/c_report";
    @InjectView(R.id.Location)
    Spinner etLocation;
    @InjectView(R.id.Room)
    Spinner etRoom;
    @InjectView(R.id.Remarks)
    EditText etRemarks;
    @InjectView(R.id.bSend)
    Button bSend;
    @InjectView(R.id.etype)
    Spinner etype;
    UserSessionManager session;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_report);
        ButterKnife.inject(this);

        etype = (Spinner) findViewById(R.id.etype);
        String[] values = getResources().getStringArray(R.array.Emergencytype);
        ArrayAdapter<String> simpleSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
        etype.setAdapter(simpleSpinnerAdapter);

        etLocation = (Spinner) findViewById(R.id.Location);
        String[] locval = getResources().getStringArray(R.array.FloorNumber);
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, locval);
        etLocation.setAdapter(locationAdapter);

        etRoom =  (Spinner) findViewById(R.id.Room);

        etLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String floorval = String.valueOf(etLocation.getSelectedItem());
                String[] fourthfloor = getResources().getStringArray(R.array.FourthFloorRooms);
                String[] thirdfloor = getResources().getStringArray(R.array.ThirdFloorRooms);
                String[] secondfloor = getResources().getStringArray(R.array.SecondFloorRooms);
                String[] firstfloor = getResources().getStringArray(R.array.FirstFloorRooms);

                if(floorval.contentEquals("FOURTH FLOOR")) {
                    etRoom.setAdapter(new ArrayAdapter<String>(EmergencyReportActivity.this, android.R.layout.simple_list_item_1, fourthfloor));
                } else if (floorval.contentEquals("THIRD FLOOR")) {
                    etRoom.setAdapter(new ArrayAdapter<String>(EmergencyReportActivity.this, android.R.layout.simple_list_item_1, thirdfloor));
                } else if (floorval.contentEquals("SECOND FLOOR")) {
                    etRoom.setAdapter(new ArrayAdapter<String>(EmergencyReportActivity.this, android.R.layout.simple_list_item_1, secondfloor));
                }  else if (floorval.contentEquals("FIRST FLOOR")) {
                    etRoom.setAdapter(new ArrayAdapter<String>(EmergencyReportActivity.this, android.R.layout.simple_list_item_1, firstfloor));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        session = new UserSessionManager(getApplicationContext());

        bSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(EmergencyReportActivity.this);
                AlertDialog dialog = builder.create();
                dialog.setMessage("Are you sure you want to send this report?");
                dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        new AttemptSend().execute();
                    }
                });
                dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(EmergencyReportActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    class AttemptSend extends AsyncTask<String, String, Boolean> {

        SendReportResponse sendReportResponse = null;
        ProgressDialog progressDialog;

        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(EmergencyReportActivity.this,
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setMessage("Sending...");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(true);
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... arg) {

            HashMap<String, String> user = session.getUserDetails();
            String type = etype.getSelectedItem().toString();
            String room = etRoom.getSelectedItem().toString();
            String location = etLocation.getSelectedItem().toString();
            String remarks = etRemarks.getText().toString();
            String message = type + " at " + location + " - " + room + "..." + remarks;


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            String userid = user.get(UserSessionManager.KEY_TYPE);
            String username = user.get(UserSessionManager.KEY_NAME);
            String usertype = user.get(UserSessionManager.KEY_ID_NUMBER);
            params.add(new BasicNameValuePair("id_no", userid));
            params.add(new BasicNameValuePair("username", username));
            params.add(new BasicNameValuePair("message", message));
            params.add(new BasicNameValuePair("usertype", usertype));
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
            etRemarks.setText("");


            if (success) {
                Toast.makeText(EmergencyReportActivity.this, sendReportResponse.message, Toast.LENGTH_LONG).show();
            }
            Intent i = new Intent(EmergencyReportActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }

    }

}
