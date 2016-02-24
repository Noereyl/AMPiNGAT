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
import java.util.HashMap;
import java.util.List;

import at.lukle.clickableareasimage.ClickableArea;
import at.lukle.clickableareasimage.ClickableAreasImage;
import at.lukle.clickableareasimage.OnClickableAreaClickedListener;
import uk.co.senab.photoview.PhotoViewAttacher;


public class FourthFloorActivity extends Activity implements OnClickableAreaClickedListener {

    ImageView imageView;
    TextView room, direction;
    String area;
    JSONParser jsonParser = new JSONParser();
    private static String url  = "http://" + Constants.DOMAIN_IP + "ampingat/c_fourthfloorroutes/customizedRoutes";

    UserSessionManager session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_floor);
        session = new UserSessionManager(getApplicationContext());
        room = (TextView) findViewById(R.id.roomId);
        direction = (TextView) findViewById(R.id.directions);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.ic_fourthfloor);

        AfterTask();
    }

    @SuppressWarnings("unchecked")
    public void AfterTask() {

        ClickableAreasImage clickableAreasImage = new ClickableAreasImage(new PhotoViewAttacher(imageView), this);
        List<ClickableArea> clickableAreas = new ArrayList<ClickableArea>();
        clickableAreas.add(new ClickableArea(617, 521, 161, 71, "Deans Office"));
        clickableAreas.add(new ClickableArea(617, 465, 68, 57, "College Faculty"));
        clickableAreas.add(new ClickableArea(617, 408, 70, 57, "Room 405"));
        clickableAreas.add(new ClickableArea(617, 352, 69, 57, "CHS Room"));
        clickableAreas.add(new ClickableArea(617, 299, 69, 54, "Electrical Room"));
        clickableAreas.add(new ClickableArea(617, 222, 69, 75, "Computer Laboratory 4"));
        clickableAreas.add(new ClickableArea(619, 72, 68, 125, "College Library"));
        clickableAreas.add(new ClickableArea(704, 465, 65, 55, "MassCom Room"));
        clickableAreas.add(new ClickableArea(705, 419, 74, 46, "Accounting Lab"));
        clickableAreas.add(new ClickableArea(705, 355, 73, 54, "Room 406"));
        clickableAreas.add(new ClickableArea(705, 241, 74, 112, "Physics Lab"));
        clickableAreas.add(new ClickableArea(705, 184, 74, 57, "OSAD/SSG Office"));
        clickableAreas.add(new ClickableArea(705, 74, 74, 100, "ChemLab"));
        clickableAreas.add(new ClickableArea(469, 39, 138, 168, "Caregiver Room"));
        clickableAreas.add(new ClickableArea(264, 115, 47, 84, "ITE Center"));
        clickableAreas.add(new ClickableArea(218, 115, 48, 83, "Room 417"));
        clickableAreas.add(new ClickableArea(173, 115, 47, 83, "Room 418"));
        clickableAreas.add(new ClickableArea(220, 31, 94, 83, "BSBA"));
        clickableAreas.add(new ClickableArea(92, 221, 68, 75, "CrimLab"));
        clickableAreas.add(new ClickableArea(338, 53, 105, 140, "Fourth Floor Extension"));
        clickableAreas.add(new ClickableArea(604, 19, 101, 55, "Canteen"));
        clickableAreasImage.setClickableAreas(clickableAreas);
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


    @Override
    public void onClickableAreaTouched(Object o) {

        Toast.makeText(FourthFloorActivity.this, "" + o, Toast.LENGTH_SHORT).show();
        area = "" + o;
        room.setText(area);

        if(isNetworkOnline()) {
            new RequestRoutes().execute();
        } else {
            defaultRoutes();
        }
    }

    public void defaultRoutes () {

        if(room.getText().equals("Deans Office")) {
            imageView.setImageResource(R.drawable.ic_deansoffice);
        } else if(room.getText().equals("College Faculty")) {
            imageView.setImageResource(R.drawable.ic_faculty);
        } else if(room.getText().equals("Room 405")) {
            imageView.setImageResource(R.drawable.ic_room405);
        } else if(room.getText().equals("CHS Room")) {
            imageView.setImageResource(R.drawable.ic_chs);
        } else if(room.getText().equals("Electrical Room")) {
            imageView.setImageResource(R.drawable.ic_electrical);
        } else if(room.getText().equals("College Library")) {
            imageView.setImageResource(R.drawable.ic_library4);
        } else if(room.getText().equals("MassCom Room")) {
            imageView.setImageResource(R.drawable.ic_masscom);
        } else if(room.getText().equals("Accounting Lab")) {
            imageView.setImageResource(R.drawable.ic_accouting);
        } else if(room.getText().equals("Room 406")) {
            imageView.setImageResource(R.drawable.ic_room406);
        } else if(room.getText().equals("Physics Lab")) {
            imageView.setImageResource(R.drawable.ic_physics);
        } else if(room.getText().equals("OSAD/SSG Office")) {
            imageView.setImageResource(R.drawable.ic_ssg);
        } else if(room.getText().equals("ITE Center")) {
            imageView.setImageResource(R.drawable.ic_ite);
        } else if(room.getText().equals("Room 417")) {
            imageView.setImageResource(R.drawable.ic_room417);
        } else if(room.getText().equals("Room 418")) {
            imageView.setImageResource(R.drawable.ic_room418);
        } else if(room.getText().equals("BSBA")) {
            imageView.setImageResource(R.drawable.ic_bsba);
        } else if(room.getText().equals("ChemLab")) {
            imageView.setImageResource(R.drawable.ic_chemlab);
        } else if(room.getText().equals("Caregiver Room")) {
            imageView.setImageResource(R.drawable.ic_caregiver);
        } else if(room.getText().equals("CrimLab")) {
            imageView.setImageResource(R.drawable.ic_crim);
        } else if(room.getText().equals("Fourth Floor Extension")) {
            imageView.setImageResource(R.drawable.ic_extension);
        } else if(room.getText().equals("Computer Laboratory 4")) {
            imageView.setImageResource(R.drawable.ic_comlab);
        } else if(room.getText().equals("Canteen")) {
            imageView.setImageResource(R.drawable.ic_canteen4);
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
            progressDialog = new ProgressDialog(FourthFloorActivity.this,
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setMessage("Requesting for shortest routes...");
            progressDialog.setIndeterminate(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(true);
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... arg) {

            HashMap<String, String> user = session.getUserDetails();
            String userid = user.get(UserSessionManager.KEY_TYPE);

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            String roomparams = room.getText().toString();
            params.add(new BasicNameValuePair("room", roomparams));
            params.add(new BasicNameValuePair("userid", userid));
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

            if(success == true) {
                Toast.makeText(FourthFloorActivity.this, requestRoutes.message, Toast.LENGTH_SHORT).show();
            }
            direction.setText("Route: " + requestRoutes.shortestRoute + requestRoutes.secondRoute);
            customRoutes();
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setClickable(true);
            AfterTask();
        }

        public void customRoutes() {
            String er1 = "Route: [Electrical Room, Fire Exit 1][]";
            String er2 = "Route: [Electrical Room, Fire Exit 2][]";
            String care1 = "Route: [caregiver room, Fire Exit 2][]";
            String care2 = "Route: [caregiver room, Fire Exit 2][caregiver room, Stair 2]";
            String care3 = "Route: [caregiver room, Stair 2][caregiver room, 4th Floor Extension]";
            String physics1 = "Route: [physics lab, Fire Exit 1][]";
            String physics2 = "Route: [physics lab, Fire Exit 2][]";
            String ite1 = "Route: [ite, Fire Exit 3][]";
            String ite2 = "Route: [ite, 4th Floor Extension][]";
            String crim1 = "Route: [crimlab, 4th Floor Extension][]";
            String crim2 = "Route: [crimlab, Fire Exit 3][]";
            String rum418s = "Route: [room 418, 4th Floor Extension][]";
            String rum418 = "Route: [room 418, Fire Exit 3][]";
            String colfac1 = "Route: [College faculty, Stair 1][]";
            String colfac2 = "Route: [College faculty, Fire Exit 1][]";
            String masscom1 = "Route: [MassCom, Stair 1][]";
            String masscom2 = "Route: [MassCom, Fire Exit 1][]";
            String exten1 = "Route: [extension][]";

            if(room.getText().equals("Deans Office")) {
                imageView.setImageResource(R.drawable.ic_customdean);
            } else if(room.getText().equals("College Faculty") & direction.getText().equals(colfac1)) {
                imageView.setImageResource(R.drawable.ic_customfaculty);
            } else if(room.getText().equals("College Faculty") & direction.getText().equals(colfac2)) {
                imageView.setImageResource(R.drawable.ic_customcollegefac);
            } else if(room.getText().equals("Room 405")) {
                imageView.setImageResource(R.drawable.ic_customroom405);
            } else if(room.getText().equals("CHS Room")) {
                imageView.setImageResource(R.drawable.ic_customchs);
            } else if(room.getText().equals("Electrical Room") & direction.getText().equals(er1)) {
                imageView.setImageResource(R.drawable.ic_customeroom2);
            } else if(room.getText().equals("Electrical Room") & direction.getText().equals(er2)) {
                imageView.setImageResource(R.drawable.ic_customeroom);
            } else if(room.getText().equals("College Library")) {
                imageView.setImageResource(R.drawable.ic_customcollegelib);
            } else if(room.getText().equals("MassCom Room")  & direction.getText().equals(masscom1)) {
                imageView.setImageResource(R.drawable.ic_custommasscom);
            } else if(room.getText().equals("MassCom Room")  & direction.getText().equals(masscom2)) {
                imageView.setImageResource(R.drawable.ic_custommasscom1);
            } else if(room.getText().equals("Accounting Lab")) {
                imageView.setImageResource(R.drawable.ic_customcustomaccroom);
            } else if(room.getText().equals("Room 406")) {
                imageView.setImageResource(R.drawable.ic_customroom406);
            } else if(room.getText().equals("Physics Lab") & direction.getText().equals(physics2)) {
                imageView.setImageResource(R.drawable.ic_customphysic2);
            } else if(room.getText().equals("Physics Lab") & direction.getText().equals(physics1)) {
                imageView.setImageResource(R.drawable.ic_customphysics);
            } else if(room.getText().equals("OSAD/SSG Office")) {
                imageView.setImageResource(R.drawable.ic_customssg);
            } else if(room.getText().equals("ITE Center") & direction.getText().equals(ite2)) {
                imageView.setImageResource(R.drawable.ic_customite);
            } else if(room.getText().equals("ITE Center") & direction.getText().equals(ite1)) {
                imageView.setImageResource(R.drawable.ic_customite2);
            } else if(room.getText().equals("Room 417")) {
                imageView.setImageResource(R.drawable.ic_customroom417);
            } else if(room.getText().equals("Room 418")  & direction.getText().equals(rum418)) {
                imageView.setImageResource(R.drawable.ic_customroom418);
            } else if(room.getText().equals("Room 418") & direction.getText().equals(rum418s)) {
                imageView.setImageResource(R.drawable.ic_customroom418s);
            } else if(room.getText().equals("BSBA")) {
                imageView.setImageResource(R.drawable.ic_custombsba);
            } else if(room.getText().equals("ChemLab")) {
                imageView.setImageResource(R.drawable.ic_customchemlab1);
            } else if(room.getText().equals("Caregiver Room") & direction.getText().equals(care1)) {
                imageView.setImageResource(R.drawable.ic_customcaregiver);
            } else if(room.getText().equals("Caregiver Room") & direction.getText().equals(care2)) {
                imageView.setImageResource(R.drawable.ic_customcaregiver3);
            } else if(room.getText().equals("Caregiver Room") & direction.getText().equals(care3)) {
                imageView.setImageResource(R.drawable.ic_customcaregiver2);
            } else if(room.getText().equals("CrimLab") & direction.getText().equals(crim1)) {
                imageView.setImageResource(R.drawable.ic_customcrim1);
            } else if(room.getText().equals("CrimLab") & direction.getText().equals(crim2)) {
                imageView.setImageResource(R.drawable.ic_customcrim);
            } else if(room.getText().equals("Fourth Floor Extension") & direction.getText().equals(exten1)) {
                imageView.setImageResource(R.drawable.ic_customextension4);
            } else if(room.getText().equals("Computer Laboratory 4")) {
                imageView.setImageResource(R.drawable.ic_customcomlab4);
            } else if(room.getText().equals("Canteen")) {
                imageView.setImageResource(R.drawable.ic_customcanteen4);
            } else if(direction.getText().equals("[][]")) {
                imageView.setImageResource(R.drawable.ic_fourthfloor);
            }
        }

    }
}