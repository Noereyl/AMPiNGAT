package com.ampingat.ampingatapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Reyle T. Revira on 8/23/2015.
 */
    public class EapFragment extends Fragment {
        public static EapFragment newInstance() {
            EapFragment fragment = new EapFragment();
            return fragment;
        }

    public EapFragment() {

    }
    Button btn1, btn2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_eap, container, false);
        btn1 = (Button) rootView.findViewById(R.id.button_fire);
        btn2 = (Button) rootView.findViewById(R.id.button_earthquake);
        View.OnClickListener btnFireListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EapFireActivity.class);
                v.getContext().startActivity(intent);
            }
        };
        View.OnClickListener btnEarthquakeListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EapEarthquakeActivity.class);
                v.getContext().startActivity(intent);
            }
        };
        btn1.setOnClickListener(btnFireListener);
        btn2.setOnClickListener(btnEarthquakeListener);
        return rootView;
    }

}
