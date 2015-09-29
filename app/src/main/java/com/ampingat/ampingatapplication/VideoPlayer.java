package com.ampingat.ampingatapplication;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

/**
 * Created by Joy Rivera on 9/18/2015.
 */
public class VideoPlayer extends Activity {

    VideoView videoView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_video_player);
        File file = new File(Environment.getExternalStorageDirectory() +"/AmpingatVid/Amputation.mp4");
        Log.e("FILE", file.isFile() + "");
        videoView = (VideoView) findViewById(R.id.videoView);
        Uri uri = Uri.parse(getIntent().getExtras().getString("VIDEO_PATH"));
        Toast.makeText(VideoPlayer.this, "" + getIntent().getExtras().getString("VIDEO_PATH"), Toast.LENGTH_SHORT).show();
        videoView.setVideoURI(uri);
        videoView.start();

        Toast.makeText(VideoPlayer.this, "" + getIntent().getExtras().getString("VIDEO_PATH"), Toast.LENGTH_SHORT).show();
    }

}
