
package com.ampingat.ampingatapplication;

    import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ampingat.ampingatapplication.adapter.VideoListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Reyle T. Revira on 8/23/2015.
 */
public class FirstAidFragment extends Fragment  {

    ListView videoListView;

    private static final String TAG = "FirstAidFragment";

    public static FirstAidFragment newInstance() {
        FirstAidFragment fragment = new FirstAidFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_firstaid, container, false);

        //bind widget to listview object
        videoListView = (ListView)rootView.findViewById(R.id.videoList);

        //create dummy values for list
        List<VideoMetaData> videoList = new ArrayList<VideoMetaData>();

        for (int i=0;i<17;i++){
            VideoMetaData data;
            data = new VideoMetaData();
            if (i == 0){
                String fileName = "Adult CPR (Lay Rescuer)";
                data.setFileName(fileName);
                data.setFilePath(Environment.getExternalStorageDirectory() + "/amp/Adult CPR - Lay Rescuer.mp4");
            } else if (i == 1) {
                String fileName = "Amputation";
                data.setFileName(fileName);
                data.setFilePath(Environment.getExternalStorageDirectory() + "/amp/Amputation.mp4");
            } else if (i == 2) {
                String fileName = "Bleeding Control (Capillary Bleeding)";
                data.setFileName(fileName);
                data.setFilePath(Environment.getExternalStorageDirectory() +"/amp/Bleeding Control Capillary Bleeding.mp4");
            } else if (i == 3) {
                String fileName = "Bleeding Control (Venous Bleeding)";
                data.setFileName(fileName);
                data.setFilePath(Environment.getExternalStorageDirectory() +"/amp/Bleeding Control Venous Bleeding.mp4");
            } else if (i == 4) {
                String fileName = "Burns";
                data.setFileName(fileName);
                data.setFilePath(Environment.getExternalStorageDirectory() +"/amp/Burns.mp4");
            } else if (i == 5) {
                String fileName = "Conscious Adult Choking";
                data.setFileName(fileName);
                data.setFilePath(Environment.getExternalStorageDirectory() +"/amp/Conscious Adult Choking.mp4");
            } else if (i == 6) {
                String fileName = "CPR";
                data.setFileName(fileName);
                data.setFilePath(Environment.getExternalStorageDirectory() +"/amp/CPR.mp4");
            } else if (i == 7) {
                String fileName = "Eye Injuries";
                data.setFileName(fileName);
                data.setFilePath(Environment.getExternalStorageDirectory() +"/amp/Eye Injuries.mp4");
            } else if (i == 8) {
                String fileName = "Fainting";
                data.setFileName(fileName);
                data.setFilePath(Environment.getExternalStorageDirectory() +"/amp/Fainting.mp4");
            } else if (i == 9) {
                String fileName = "Hand Washing";
                data.setFileName(fileName);
                data.setFilePath(Environment.getExternalStorageDirectory() +"/amp/Hand Washing.mp4");
            } else if (i == 10) {
                String fileName = "Head, Neck and Back Injuries";
                data.setFileName(fileName);
                data.setFilePath(Environment.getExternalStorageDirectory() +"/amp/Head, Neck and Back Injuries.mp4");
            } else if (i == 11) {
                String fileName = "Heat Related Emergencies";
                data.setFileName(fileName);
                data.setFilePath(Environment.getExternalStorageDirectory() +"/amp/Heat Related Emergencies.mp4");
            } else if (i == 12) {
                String fileName = "How to use an Epipen";
                data.setFileName(fileName);
                data.setFilePath(Environment.getExternalStorageDirectory() +"/amp/How To Use An Epipen.mp4");
            } else if (i == 13) {
                String fileName = "Musculoskeletal Injuries";
                data.setFileName(fileName);
                data.setFilePath(Environment.getExternalStorageDirectory() +"/amp/Musculoskeletal Injuries.mp4");
            } else if (i == 14) {
                String fileName = "Poison Control";
                data.setFileName(fileName);
                data.setFilePath(Environment.getExternalStorageDirectory() +"/amp/Poison Control.mp4");
            } else if (i == 15) {
                String fileName = "Secondary Survey";
                data.setFileName(fileName);
                data.setFilePath(Environment.getExternalStorageDirectory() +"/amp/Secondary Survey.mp4");
            } else if (i == 16) {
                String fileName = "Seizure";
                data.setFileName(fileName);
                data.setFilePath(Environment.getExternalStorageDirectory() +"/amp/Seizure.mp4");
            } else {
                String fileName = "Shock";
                data.setFileName(fileName);
                data.setFilePath(Environment.getExternalStorageDirectory() +"/amp/Shock.mp4");
            }
            videoList.add(data);
        }


        //instantiate video list adapter with the required parameters
        final VideoListAdapter videoListAdapter = new VideoListAdapter(getActivity().getApplicationContext(), videoList);

        //set adapter to video view
        videoListView.setAdapter(videoListAdapter);

        //set on item click listener to specify what actions are
        // to be done when clicking on a single item of the list
        videoListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        VideoPlayer videoPlayer = new VideoPlayer();
                        Log.e("TAG", videoListAdapter.getItem(position).getFilePath());
                        Intent intent = new Intent(getActivity().getApplicationContext(), videoPlayer.getClass());
                        intent.putExtra("VIDEO_PATH", videoListAdapter.getItem(position).getFilePath());
                        startActivity(intent);
                    }
                }
        );

        return rootView;

    }

}


