package com.ampingat.ampingatapplication;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Reyle T. Revira on 8/23/2015.
 */
    public class EmergencyFragment extends Fragment {
        public static EmergencyFragment newInstance() {
            EmergencyFragment fragment = new EmergencyFragment();
            return fragment;
    }
    Button btn1, btn2, btn3, btn4;

    public EmergencyFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_emergency, container, false);
        btn1 = (Button) rootView.findViewById(R.id.button_1st);
        btn2 = (Button) rootView.findViewById(R.id.button_2nd);
        btn3 = (Button) rootView.findViewById(R.id.button_3rd);
        btn4 = (Button) rootView.findViewById(R.id.button_4th);

        View.OnClickListener btnOneListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FirstFloorActivity.class);
                v.getContext().startActivity(intent);
            }
        };

        View.OnClickListener btnTwoListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SecondFloorActivity.class);
                v.getContext().startActivity(intent);
            }
        };

        View.OnClickListener btnThreeListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ThirdFloorActivity.class);
                v.getContext().startActivity(intent);
            }
        };

        View.OnClickListener btnFourListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FourthFloorActivity.class);
                v.getContext().startActivity(intent);
            }
        };
        btn1.setOnClickListener(btnOneListener);
        btn2.setOnClickListener(btnTwoListener);
        btn3.setOnClickListener(btnThreeListener);
        btn4.setOnClickListener(btnFourListener);
        return rootView;
    }
}
