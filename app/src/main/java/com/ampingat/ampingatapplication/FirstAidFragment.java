package com.ampingat.ampingatapplication;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * Created by Reyle T. Revira on 8/23/2015.
 */
    public class FirstAidFragment extends Fragment {
        public static FirstAidFragment newInstance() {
            FirstAidFragment fragment = new FirstAidFragment();
            return fragment;
        }
    public FirstAidFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_firstaid, container, false);
        return rootView;
    }

}
