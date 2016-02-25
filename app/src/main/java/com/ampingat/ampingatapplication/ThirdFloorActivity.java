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


public class ThirdFloorActivity extends Activity implements OnClickableAreaClickedListener {


    ImageView imageView;
    TextView room, direction;
    String area;
    JSONParser jsonParser = new JSONParser();
    private static String url  = "http://" + Constants.DOMAIN_IP + "ampingat/c_thirdfloorroutes/requestroutes";
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_floor);
        room = (TextView) findViewById(R.id.roomId);
        direction = (TextView) findViewById(R.id.directions);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.ic_third_floor);
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
        clickableAreas.add(new ClickableArea(617, 521, 161, 71, "HRMD Office"));
        clickableAreas.add(new ClickableArea(617, 465, 68, 57, "POD Office"));
        clickableAreas.add(new ClickableArea(617, 408, 70, 57, "Room 305"));
        clickableAreas.add(new ClickableArea(617, 352, 69, 57, "Room 307"));
        clickableAreas.add(new ClickableArea(617, 299, 69, 54, "Room 309"));
        clickableAreas.add(new ClickableArea(617, 222, 69, 75, "Computer Laboratory 3"));
        clickableAreas.add(new ClickableArea(619, 72, 68, 125, "High School Library"));
        clickableAreas.add(new ClickableArea(704, 465, 65, 55, "Room 302"));
        clickableAreas.add(new ClickableArea(705, 419, 74, 46, "Room 304"));
        clickableAreas.add(new ClickableArea(705, 355, 73, 54, "Room 306"));
        clickableAreas.add(new ClickableArea(705, 241, 74, 112, "Room 308"));
        clickableAreas.add(new ClickableArea(596, 157, 52, 47, "Room 310"));
        clickableAreas.add(new ClickableArea(705, 184, 74, 57, "Room 312"));
        clickableAreas.add(new ClickableArea(475, 97, 37, 69, "Room 315"));
        clickableAreas.add(new ClickableArea(266, 116, 45, 80, "Room 321"));
        clickableAreas.add(new ClickableArea(218, 115, 48, 83, "Room 322"));
        clickableAreas.add(new ClickableArea(173, 115, 47, 83, "Room 323"));
        clickableAreas.add(new ClickableArea(264, 33, 47, 83, "Room 324"));
        clickableAreas.add(new ClickableArea(220, 33, 47, 81, "Room 325"));
        clickableAreas.add(new ClickableArea(312, 28, 155, 167, "Third Floor Extension"));
        clickableAreas.add(new ClickableArea(705, 74, 74, 100, "Speech Laboratory"));
        clickableAreas.add(new ClickableArea(514, 116, 46, 80, "High School Faculty"));
        clickableAreas.add(new ClickableArea(468, 116, 46, 83, "Room 317"));
        clickableAreas.add(new ClickableArea(560, 32, 46, 86, "Room 318"));
        clickableAreas.add(new ClickableArea(514, 33, 46, 81, "Room 319"));
        clickableAreas.add(new ClickableArea(466, 33, 44, 82, "Room 320"));
        clickableAreasImage.setClickableAreas(clickableAreas);
    }

    @Override
    public void onClickableAreaTouched(Object o) {

        area = "" + o;
        room.setText(area);
        if(isNetworkOnline()) {
            new RequestRoutes().execute();
        } else {
            defaultRoutes();
        }
    }

    public void defaultRoutes () {

        if(room.getText().equals("HRMD Office")) {
            imageView.setImageResource(R.drawable.ic_hrmd);
        } else if(room.getText().equals("POD Office")) {
            imageView.setImageResource(R.drawable.ic_pod);
        } else if(room.getText().equals("Room 305")) {
            imageView.setImageResource(R.drawable.ic_room305);
        } else if(room.getText().equals("Room 307")) {
            imageView.setImageResource(R.drawable.ic_room307);
        } else if(room.getText().equals("Room 308")) {
            imageView.setImageResource(R.drawable.ic_room308);
        } else if(room.getText().equals("Room 309")) {
            imageView.setImageResource(R.drawable.ic_room309);
        } else if(room.getText().equals("Room 312")) {
            imageView.setImageResource(R.drawable.ic_room312);
        } else if(room.getText().equals("Room 315")) {
            imageView.setImageResource(R.drawable.ic_room315);
        } else if(room.getText().equals("Speech Laboratory")) {
            imageView.setImageResource(R.drawable.ic_speechlab);
        } else if(room.getText().equals("Room 321")) {
            imageView.setImageResource(R.drawable.ic_room321);
        } else if(room.getText().equals("Room 322")) {
            imageView.setImageResource(R.drawable.ic_room322);
        } else if(room.getText().equals("Room 324")) {
            imageView.setImageResource(R.drawable.ic_room324);
        } else if(room.getText().equals("Room 302")) {
            imageView.setImageResource(R.drawable.ic_room302);
        } else if(room.getText().equals("Room 304")) {
            imageView.setImageResource(R.drawable.ic_room304);
        } else if(room.getText().equals("Computer Laboratory 3")) {
            imageView.setImageResource(R.drawable.ic_comlab3);
        } else if(room.getText().equals("High School Library")) {
            imageView.setImageResource(R.drawable.ic_library3);
        } else if(room.getText().equals("Room 315")) {
            imageView.setImageResource(R.drawable.ic_room315);
        } else if(room.getText().equals("Third Floor Extension")) {
            imageView.setImageResource(R.drawable.ic_extension3);
        } else if(room.getText().equals("Room 310")) {
            imageView.setImageResource(R.drawable.ic_310);
        } else if(room.getText().equals("High School Faculty")) {
            imageView.setImageResource(R.drawable.ic_highfac);
        } else if(room.getText().equals("Room 317")) {
            imageView.setImageResource(R.drawable.ic_mrc);
        } else if(room.getText().equals("Room 318")) {
            imageView.setImageResource(R.drawable .ic_room318);
        } else if(room.getText().equals("Room 319")) {
            imageView.setImageResource(R.drawable.ic_room319);
        } else if(room.getText().equals("Room 320")) {
            imageView.setImageResource(R.drawable.ic_room320);
        } else if(room.getText().equals("Room 325")) {
            imageView.setImageResource(R.drawable.ic_room325);
        } else if(room.getText().equals("Room 323")) {
            imageView.setImageResource(R.drawable.ic_room323);
        } else if(room.getText().equals("Room 305")) {
            imageView.setImageResource(R.drawable.ic_room306);
        }
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setClickable(true);
        AfterTask();
    }


        class RequestRoutes extends AsyncTask<String, String, Boolean> {

        RequestRoutesResponse requestRoutes = null;

        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(ThirdFloorActivity.this,
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
            if(room.getText().equals("HRMD Office")) {
                imageView.setImageResource(R.drawable.ic_hrmd);
            } else if(room.getText().equals("POD Office")) {
                imageView.setImageResource(R.drawable.ic_pod);
            } else if(room.getText().equals("Room 305")) {
                imageView.setImageResource(R.drawable.ic_room305);
            } else if(room.getText().equals("Room 307")) {
                imageView.setImageResource(R.drawable.ic_room307);
            } else if(room.getText().equals("Room 308")) {
                imageView.setImageResource(R.drawable.ic_room308);
            } else if(room.getText().equals("Room 309")) {
                imageView.setImageResource(R.drawable.ic_room309);
            } else if(room.getText().equals("Room 312")) {
                imageView.setImageResource(R.drawable.ic_room312);
            } else if(room.getText().equals("Room 315")) {
                imageView.setImageResource(R.drawable.ic_room315);
            } else if(room.getText().equals("Speech Laboratory")) {
                imageView.setImageResource(R.drawable.ic_speechlab);
            } else if(room.getText().equals("Room 321")) {
                imageView.setImageResource(R.drawable.ic_room321);
            } else if(room.getText().equals("Room 322")) {
                imageView.setImageResource(R.drawable.ic_room322);
            } else if(room.getText().equals("Room 324")) {
                imageView.setImageResource(R.drawable.ic_room324);
            } else if(room.getText().equals("Room 302")) {
                imageView.setImageResource(R.drawable.ic_room302);
            } else if(room.getText().equals("Room 304")) {
                imageView.setImageResource(R.drawable.ic_room304);
            } else if(room.getText().equals("Computer Laboratory 3")) {
                imageView.setImageResource(R.drawable.ic_comlab3);
            } else if(room.getText().equals("High School Library")) {
                imageView.setImageResource(R.drawable.ic_library3);
            } else if(room.getText().equals("Room 315")) {
                imageView.setImageResource(R.drawable.ic_room315);
            } else if(room.getText().equals("Third Floor Extension")) {
                imageView.setImageResource(R.drawable.ic_extension3);
            } else if(room.getText().equals("Room 310")) {
                imageView.setImageResource(R.drawable.ic_310);
            } else if(room.getText().equals("High School Faculty")) {
                imageView.setImageResource(R.drawable.ic_highfac);
            } else if(room.getText().equals("Room 317")) {
                imageView.setImageResource(R.drawable.ic_mrc);
            } else if(room.getText().equals("Room 318")) {
                imageView.setImageResource(R.drawable .ic_room318);
            } else if(room.getText().equals("Room 319")) {
                imageView.setImageResource(R.drawable.ic_room319);
            } else if(room.getText().equals("Room 320")) {
                imageView.setImageResource(R.drawable.ic_room320);
            } else if(room.getText().equals("Room 325")) {
                imageView.setImageResource(R.drawable.ic_room325);
            } else if(room.getText().equals("Room 323")) {
                imageView.setImageResource(R.drawable.ic_room323);
            } else if(room.getText().equals("Room 305")) {
                imageView.setImageResource(R.drawable.ic_room306);
            }
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setClickable(true);
            AfterTask();
        }
    }
}
