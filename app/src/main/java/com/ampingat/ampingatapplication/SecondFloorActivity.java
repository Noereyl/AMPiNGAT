package com.ampingat.ampingatapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ampingat.ampingatapplication.helpers.Constants;
import com.ampingat.ampingatapplication.models.RequestRoutesResponse;
import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import at.lukle.clickableareasimage.ClickableArea;
import at.lukle.clickableareasimage.ClickableAreasImage;
import at.lukle.clickableareasimage.OnClickableAreaClickedListener;
import uk.co.senab.photoview.PhotoViewAttacher;


public class SecondFloorActivity extends Activity implements OnClickableAreaClickedListener {
    ImageView imageView;
    TextView room, direction;
    String area;
    JSONParser jsonParser = new JSONParser();
    private static String url  = "http://" + Constants.DOMAIN_IP + "ampingat/c_secondfloorroutes/requestroutes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_floor);

        room = (TextView) findViewById(R.id.roomId);
        direction = (TextView) findViewById(R.id.directions);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.ic_second_floor);
        AfterTask();

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

    @SuppressWarnings("unchecked")
    public void AfterTask() {

        ClickableAreasImage clickableAreasImage = new ClickableAreasImage(new PhotoViewAttacher(imageView), this);
        List<ClickableArea> clickableAreas = new ArrayList<ClickableArea>();
        clickableAreas.add(new ClickableArea(617, 521, 161, 71, "Grade School Faculty"));
        clickableAreas.add(new ClickableArea(617, 465, 68, 57, "Room 203"));
        clickableAreas.add(new ClickableArea(617, 408, 70, 57, "Room 205"));
        clickableAreas.add(new ClickableArea(617, 352, 69, 57, "Room 207"));
        clickableAreas.add(new ClickableArea(617, 299, 69, 54, "Room 209"));
        clickableAreas.add(new ClickableArea(617, 222, 69, 75, "Room 211"));
        clickableAreas.add(new ClickableArea(619, 72, 68, 125, "Audio Visual Room"));
        clickableAreas.add(new ClickableArea(704, 465, 65, 55, "Room 202"));
        clickableAreas.add(new ClickableArea(705, 419, 74, 46, "Room 204"));
        clickableAreas.add(new ClickableArea(705, 355, 73, 54, "Room 206"));
        clickableAreas.add(new ClickableArea(705, 241, 74, 112, "Room 208"));
        clickableAreas.add(new ClickableArea(703, 242, 76, 55, "Room 210"));
        clickableAreas.add(new ClickableArea(705, 184, 74, 57, "Room 212"));
        clickableAreas.add(new ClickableArea(560, 116, 45, 82, "Room 215"));
        clickableAreas.add(new ClickableArea(264, 116, 47, 82, "Research Center"));
        clickableAreas.add(new ClickableArea(218, 115, 48, 83, "CJF ComLab"));
        clickableAreas.add(new ClickableArea(173, 115, 47, 83, "CJF Office"));
        clickableAreas.add(new ClickableArea(265, 33, 45, 81, "Accreditation Center"));
        clickableAreas.add(new ClickableArea(312, 32, 156, 167, "Second Floor Extension"));
        clickableAreas.add(new ClickableArea(705, 74, 74, 100, "Computer Laboratory 2"));
        clickableAreas.add(new ClickableArea(218, 33, 47, 82, "Room 225"));
        clickableAreas.add(new ClickableArea(514, 116, 46, 80, "Room 216"));
        clickableAreas.add(new ClickableArea(468, 116, 46, 83, "Room 217"));
        clickableAreas.add(new ClickableArea(560, 32, 46, 86, "Room 218"));
        clickableAreas.add(new ClickableArea(514, 33, 46, 81, "Room 219"));
        clickableAreas.add(new ClickableArea(466, 33, 44, 82, "Room 220"));
        clickableAreas.add(new ClickableArea(173, 31, 46, 83, "Social Work Office"));

        clickableAreasImage.setClickableAreas(clickableAreas);
    }

    @Override
    public void onClickableAreaTouched(Object o) {

        area = "" + o;
        room.setText(area);

        if(isNetworkOnline()) {
            new RequestRoutes().execute();
        } else {
           defaultroutes();
        }
    }

    public void defaultroutes() {

        Toast.makeText(SecondFloorActivity.this, "Successfully Received the routes...", Toast.LENGTH_SHORT).show();
        if(room.getText().equals("Grade School Faculty")) {
            imageView.setImageResource(R.drawable.ic_gradefaculty);
        } else if(room.getText().equals("Room 202")) {
            imageView.setImageResource(R.drawable.ic_room202);
        } else if(room.getText().equals("Room 203")) {
            imageView.setImageResource(R.drawable.ic_room203);
        } else if(room.getText().equals("Room 204")) {
            imageView.setImageResource(R.drawable.ic_room204);
        } else if(room.getText().equals("Room 205")) {
            imageView.setImageResource(R.drawable.ic_room205);
        } else if(room.getText().equals("Room 206")) {
            imageView.setImageResource(R.drawable.ic_room206);
        } else if(room.getText().equals("Room 207")) {
            imageView.setImageResource(R.drawable.ic_room207);
        } else if(room.getText().equals("Room 208")) {
            imageView.setImageResource(R.drawable.ic_room208);
        } else if(room.getText().equals("Room 209")) {
            imageView.setImageResource(R.drawable.ic_room209);
        } else if(room.getText().equals("Room 211")) {
            imageView.setImageResource(R.drawable.ic_room211);
        } else if(room.getText().equals("Room 212")) {
            imageView.setImageResource(R.drawable.ic_room212);
        } else if(room.getText().equals("Room 215")) {
            imageView.setImageResource(R.drawable.ic_room215);
        } else if(room.getText().equals("Accreditation Center")) {
            imageView.setImageResource(R.drawable.ic_accredi);
        } else if(room.getText().equals("CJF ComLab")) {
            imageView.setImageResource(R.drawable.ic_comlabcjf);
        } else if(room.getText().equals("CJF Office")) {
            imageView.setImageResource(R.drawable.ic_cjfoffice);
        } else if(room.getText().equals("Audio Visual Room")) {
            imageView.setImageResource(R.drawable.ic_avr);
        } else if(room.getText().equals("Computer Laboratory 2")) {
            imageView.setImageResource(R.drawable.ic_comlab2);
        } else if(room.getText().equals("Second Floor Extension")) {
            imageView.setImageResource(R.drawable.ic_extension2);
        } else if(room.getText().equals("Research Center")) {
            imageView.setImageResource(R.drawable.ic_research);
        } else if(room.getText().equals("Room 210")) {
            imageView.setImageResource(R.drawable.ic_210);
        } else if(room.getText().equals("Room 216")) {
            imageView.setImageResource(R.drawable.ic_room216);
        } else if(room.getText().equals("Room 217")) {
            imageView.setImageResource(R.drawable.ic_room217);
        } else if(room.getText().equals("Room 218")) {
            imageView.setImageResource(R.drawable .ic_room218);
        } else if(room.getText().equals("Room 219")) {
            imageView.setImageResource(R.drawable.ic_room219);
        } else if(room.getText().equals("Room 220")) {
            imageView.setImageResource(R.drawable.ic_room220);
        } else if(room.getText().equals("Social Work Office")) {
            imageView.setImageResource(R.drawable.ic_socialwork);
        }
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setClickable(true);
        AfterTask();
    }

    class RequestRoutes extends AsyncTask<String, String, Boolean> {

        RequestRoutesResponse requestRoutes = null;
        ProgressDialog progressDialog;


        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(SecondFloorActivity.this,
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setMessage("Requesting for shortest routes...");
            progressDialog.setIndeterminate(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(true);
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... arg) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            String roomparams = room.getText().toString();
            params.add(new BasicNameValuePair("room", roomparams));
            Log.d("request!", "starting");

            JSONObject json = jsonParser.makeHttpRequest(url, "POST", params);
            requestRoutes = new Gson().fromJson(json.toString(), RequestRoutesResponse.class);
            Log.d("Create Response", requestRoutes.message);

            try {
                if (requestRoutes.success == 1) {
                    Log.d("Successful!", json.toString());
                    return (requestRoutes.success == 1);

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

            if(success)
            direction.setText(requestRoutes.shortestRoute.toString() + " -> " + requestRoutes.secondRoute + " -> " + requestRoutes.thirdRoute);
            if(room.getText().equals("Grade School Faculty")) {
                imageView.setImageResource(R.drawable.ic_gradefaculty);
            } else if(room.getText().equals("Room 202")) {
                imageView.setImageResource(R.drawable.ic_room202);
            } else if(room.getText().equals("Room 203")) {
                imageView.setImageResource(R.drawable.ic_room203);
            } else if(room.getText().equals("Room 204")) {
                imageView.setImageResource(R.drawable.ic_room204);
            } else if(room.getText().equals("Room 205")) {
                imageView.setImageResource(R.drawable.ic_room205);
            } else if(room.getText().equals("Room 206")) {
                imageView.setImageResource(R.drawable.ic_room206);
            } else if(room.getText().equals("Room 207")) {
                imageView.setImageResource(R.drawable.ic_room207);
            } else if(room.getText().equals("Room 208")) {
                imageView.setImageResource(R.drawable.ic_room208);
            } else if(room.getText().equals("Room 209")) {
                imageView.setImageResource(R.drawable.ic_room209);
            } else if(room.getText().equals("Room 211")) {
                imageView.setImageResource(R.drawable.ic_room211);
            } else if(room.getText().equals("Room 212")) {
                imageView.setImageResource(R.drawable.ic_room212);
            } else if(room.getText().equals("Room 215")) {
                imageView.setImageResource(R.drawable.ic_room215);
            } else if(room.getText().equals("Accreditation Center")) {
                imageView.setImageResource(R.drawable.ic_accredi);
            } else if(room.getText().equals("CJF ComLab")) {
                imageView.setImageResource(R.drawable.ic_comlabcjf);
            } else if(room.getText().equals("CJF Office")) {
                imageView.setImageResource(R.drawable.ic_cjfoffice);
            } else if(room.getText().equals("Audio Visual Room")) {
                imageView.setImageResource(R.drawable.ic_avr);
            } else if(room.getText().equals("Computer Laboratory 2")) {
                imageView.setImageResource(R.drawable.ic_comlab2);
            } else if(room.getText().equals("Second Floor Extension")) {
                imageView.setImageResource(R.drawable.ic_extension2);
            } else if(room.getText().equals("Research Center")) {
                imageView.setImageResource(R.drawable.ic_research);
            } else if(room.getText().equals("Room 210")) {
                imageView.setImageResource(R.drawable.ic_210);
            } else if(room.getText().equals("Room 216")) {
                imageView.setImageResource(R.drawable.ic_room216);
            } else if(room.getText().equals("Room 217")) {
                imageView.setImageResource(R.drawable.ic_room217);
            } else if(room.getText().equals("Room 218")) {
                imageView.setImageResource(R.drawable .ic_room218);
            } else if(room.getText().equals("Room 219")) {
                imageView.setImageResource(R.drawable.ic_room219);
            } else if(room.getText().equals("Room 220")) {
                imageView.setImageResource(R.drawable.ic_room220);
            } else if(room.getText().equals("Social Work Office")) {
                imageView.setImageResource(R.drawable.ic_socialwork);
            }
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setClickable(true);
            AfterTask();

        }
    }
}
