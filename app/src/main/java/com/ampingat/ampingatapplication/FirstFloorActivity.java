package com.ampingat.ampingatapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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


public class FirstFloorActivity extends Activity implements OnClickableAreaClickedListener {
    ImageView imageView;
    TextView room, direction;
    String area;
    JSONParser jsonParser = new JSONParser();
    private static String url  = "http://172.20.10.4/ampingat/c_firstfloorroutes/requestroutes";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_floor);

        room = (TextView) findViewById(R.id.roomId);
        direction = (TextView) findViewById(R.id.directions);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.ic_first_floor);
        AfterTask();

    }

    @SuppressWarnings("unchecked")
    public void AfterTask() {

        ClickableAreasImage clickableAreasImage = new ClickableAreasImage(new PhotoViewAttacher(imageView), this);
        List<ClickableArea> clickableAreas = new ArrayList<ClickableArea>();
        clickableAreas.add(new ClickableArea(602, 42, 80, 162, "Engineering Office"));
        clickableAreas.add(new ClickableArea(697, 93, 84, 49, "IT Office"));
        clickableAreas.add(new ClickableArea(697, 141, 83, 50, "Architect Office"));
        clickableAreas.add(new ClickableArea(697, 193, 84, 33, "COMPACQ"));
        clickableAreas.add(new ClickableArea(697, 229, 84, 45, "Clinic"));
        clickableAreas.add(new ClickableArea(618, 227, 64, 28, "Coop"));
        clickableAreas.add(new ClickableArea(618, 254, 23, 40, "STO"));
        clickableAreas.add(new ClickableArea(638, 254, 45, 39, "Admin Office"));
        clickableAreas.add(new ClickableArea(639, 297, 42, 50, "Prep Library"));
        clickableAreas.add(new ClickableArea(616, 296, 22, 48, "Printing"));
        clickableAreas.add(new ClickableArea(618, 297, 75, 50, "Registrar"));
        clickableAreas.add(new ClickableArea(700, 344, 58, 53, "Nursery Classroom"));
        clickableAreas.add(new ClickableArea(700, 394, 57, 54, "Kinder 1 Classroom"));
        clickableAreas.add(new ClickableArea(700, 445, 58, 53, "Kinder 2 Classroom"));
        clickableAreasImage.setClickableAreas(clickableAreas);
    }

    @Override
    public void onClickableAreaTouched(Object o) {

        Toast.makeText(FirstFloorActivity.this, "" + o, Toast.LENGTH_SHORT).show();
        area = "" + o;
        room.setText(area);
        new RequestRoutes().execute();
    }

    class RequestRoutes extends AsyncTask<String, String, Boolean> {

        RequestRoutesResponse requestRoutes = null;
        ProgressDialog progressDialog;


        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(FirstFloorActivity.this,
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

            if(success == true) {
                Toast.makeText(FirstFloorActivity.this, requestRoutes.message, Toast.LENGTH_SHORT).show();
            }
            direction.setText(requestRoutes.shortestRoute.toString() + "," + requestRoutes.secondRoute + "," + requestRoutes.thirdRoute);
            defaultRoutes();
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setClickable(true);
            AfterTask();

        }
    }



    public void defaultRoutes() {
        if(room.getText().equals("Engineering Office")) {
            imageView.setImageResource(R.drawable.ic_engineering);

        } else if(room.getText().equals("IT Office")) {
            imageView.setImageResource(R.drawable.ic_it);

        } else if(room.getText().equals("Architect Office")) {
            imageView.setImageResource(R.drawable.ic_architect);

        } else if(room.getText().equals("COMPACQ")) {
            imageView.setImageResource(R.drawable.ic_compacq);

        } else if(room.getText().equals("Clinic")) {
            imageView.setImageResource(R.drawable.ic_clinic);

        } else if(room.getText().equals("Coop")) {
            imageView.setImageResource(R.drawable.ic_coop);

        } else if(room.getText().equals("STO")) {
            imageView.setImageResource(R.drawable.ic_sto);

        } else if(room.getText().equals("Admin")) {
            imageView.setImageResource(R.drawable.ic_admin);

        } else if(room.getText().equals("Prep Library")) {
            imageView.setImageResource(R.drawable.ic_preplib);

        } else if(room.getText().equals("Printing")) {
            imageView.setImageResource(R.drawable.ic_printing);

        } else if(room.getText().equals("Registrar")) {
            imageView.setImageResource(R.drawable.ic_register);

        } else if(room.getText().equals("Nursery Classroom")) {
            imageView.setImageResource(R.drawable.ic_nursery);

        } else if(room.getText().equals("Kinder 1 Classroom")) {
            imageView.setImageResource(R.drawable.ic_kinder1);

        } else if(room.getText().equals("Kinder 2 Classroom")) {
            imageView.setImageResource(R.drawable.ic_kinder2);
        }
    }


    public void customRoutes() {
        if(room.getText().equals("Engineering Office")) {
            imageView.setImageResource(R.drawable.ic_engineering);

        } else if(room.getText().equals("IT Office")) {
            imageView.setImageResource(R.drawable.ic_it);

        } else if(room.getText().equals("Architect Office")) {
            imageView.setImageResource(R.drawable.ic_architect);

        } else if(room.getText().equals("COMPACQ")) {
            imageView.setImageResource(R.drawable.ic_compacq);

        } else if(room.getText().equals("Clinic")) {
            imageView.setImageResource(R.drawable.ic_clinic);

        } else if(room.getText().equals("Coop")) {
            imageView.setImageResource(R.drawable.ic_coop);

        } else if(room.getText().equals("STO")) {
            imageView.setImageResource(R.drawable.ic_sto);

        } else if(room.getText().equals("Admin")) {
            imageView.setImageResource(R.drawable.ic_admin);

        } else if(room.getText().equals("Prep Library")) {
            imageView.setImageResource(R.drawable.ic_preplib);

        } else if(room.getText().equals("Printing")) {
            imageView.setImageResource(R.drawable.ic_printing);

        } else if(room.getText().equals("Registrar")) {
            imageView.setImageResource(R.drawable.ic_register);

        } else if(room.getText().equals("Nursery Classroom")) {
            imageView.setImageResource(R.drawable.ic_nursery);

        } else if(room.getText().equals("Kinder 1 Classroom")) {
            imageView.setImageResource(R.drawable.ic_kinder1);

        } else if(room.getText().equals("Kinder 2 Classroom")) {
            imageView.setImageResource(R.drawable.ic_kinder2);
        }
    }
}
