package com.ampingat.ampingatapplication;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.ampingat.ampingatapplication.adapter.VideoListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
    public FirstAidFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_firstaid, container, false);

        //bind widget to listview object
        videoListView = (ListView)rootView.findViewById(R.id.videoList);

        //=============create dummy values for list
        List<String> videoList = new ArrayList<>();
        videoList.add(Environment.getExternalStorageDirectory() + "/Download/sample.mp4");
        videoList.add(Environment.getExternalStorageDirectory() + "/Download/sample2.mp4");

        //instantiate video list adapter with the required parameters
        final VideoListAdapter videoListAdapter = new VideoListAdapter(getActivity().getApplicationContext(), videoList);

        //set adapter to video view
        videoListView.setAdapter(videoListAdapter);

        //set on item click listener to specify what actions are
        // to be done when clicking on a single item of the list
        videoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG, videoListAdapter.getItem(position));
            }
        });

        return rootView;

    }

}
