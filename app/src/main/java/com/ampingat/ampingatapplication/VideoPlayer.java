package com.ampingat.ampingatapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

/**
 * Created by Joy Rivera on 9/18/2015.
 */
public class VideoPlayer extends Activity {

    VideoView videoView;
    int position = 0;
    ProgressDialog progressDialog;
    MediaController mediaController;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_video_player);

        if (mediaController == null) {
            mediaController = new MediaController(VideoPlayer.this);
        }
        videoView = (VideoView) findViewById(R.id.videoView);

        progressDialog = new ProgressDialog(VideoPlayer.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        try {
            videoView.setMediaController(mediaController);
            Uri uri = Uri.parse(getIntent().getExtras().getString("VIDEO_PATH"));
            Toast.makeText(VideoPlayer.this, "" + getIntent().getExtras().getString("VIDEO_PATH"), Toast.LENGTH_SHORT).show();
            videoView.setVideoURI(uri);
            videoView.start();

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        videoView.requestFocus();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {
                progressDialog.dismiss();
                videoView.seekTo(position);
                if (position == 0) {
                    videoView.start();
                } else {
                    videoView.pause();
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("Position", videoView.getCurrentPosition());
        videoView.pause();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        position = savedInstanceState.getInt("Position");
        videoView.seekTo(position);
    }
}