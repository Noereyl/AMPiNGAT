package com.ampingat.ampingatapplication;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Reyle T. Revira on 8/23/2015.
 */
    public class FirstAidFragment extends Fragment  {

    private VideoView mVideoView;
    private MediaController mMediaController;
    private int contentView;


    public static FirstAidFragment newInstance() {
        FirstAidFragment fragment = new FirstAidFragment();
        return fragment;
    }
    public FirstAidFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_firstaid, container, false);

        try {
            mVideoView = (VideoView)rootView.findViewById(R.id.videoEntry);
            mMediaController  = new MediaController(getActivity().getBaseContext());
            mMediaController.setAnchorView(mVideoView);
            mVideoView.setVideoURI(Uri.parse(Environment.getExternalStorageDirectory() + "/Download/CPR.mp4"));
            mVideoView.setMediaController(mMediaController);
            mVideoView.seekTo(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rootView;

    }

    public void setContentView(int contentView) {
        this.contentView = contentView;
    }
}
