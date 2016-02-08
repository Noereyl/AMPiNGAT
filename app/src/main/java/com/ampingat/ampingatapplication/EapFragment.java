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
public class EapFragment extends Fragment {

    ListView videoListView;

    private static final String TAG = "EapFragment";

    public static EapFragment newInstance() {
        EapFragment fragment = new EapFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_eap, container, false);

        videoListView = (ListView) rootView.findViewById(R.id.listVideo);

        //create dummy values for list
        List<VideoMetaData> videoList = new ArrayList<VideoMetaData>();

        for (int i = 0; i < 2; i++) {
            VideoMetaData data;
            data = new VideoMetaData();
            if (i == 0) {
                String fileName = "Emergency Action Plan in Fire";
                String size = "Size: 6:51 MB";
                String length = "Length: 00:03:36";
                data.setFileName(fileName);
                data.setFileSize(size);
                data.setFileLength(length);
                data.setFilePath(Environment.getExternalStorageDirectory() + "/Download/AmpingatVid/Adult CPR - Lay Rescuer.mp4");

            } else {
                String fileName = "Emergency Action Plan in Earthquake";
                data.setFileName(fileName);
                String size = "Size: 9.86 MB";
                String length = "Length: 00:05:41";
                data.setFileLength(length);
                data.setFileSize(size);
                data.setFilePath(Environment.getExternalStorageDirectory() + "/Download/AmpingatVid/Shock.mp4");
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
