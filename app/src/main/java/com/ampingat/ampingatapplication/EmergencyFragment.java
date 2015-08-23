package com.ampingat.ampingatapplication;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Reyle T. Revira on 8/23/2015.
 */
    public class EmergencyFragment extends Fragment {
        public static EmergencyFragment newInstance() {
            EmergencyFragment fragment = new EmergencyFragment();
            return fragment;
    }

    public EmergencyFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_emergency, container, false);
        return rootView;
    }
}
