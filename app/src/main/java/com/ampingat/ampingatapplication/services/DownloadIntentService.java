package com.ampingat.ampingatapplication.services;

import android.app.IntentService;
import android.content.Intent;

import com.ampingat.ampingatapplication.models.VideoFile;

import java.util.List;

/**
 * Created by Joy Rivera on 1/12/2016.
 */
public class DownloadIntentService extends IntentService {

    public static final String TAG = "DownloadIntentService";

    List<VideoFile> objects;

    public DownloadIntentService(List<VideoFile> objects){
        super("DownloadIntentService");
        this.objects = objects;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
//        performQueueDownload();
    }

    /*private void performQueueDownload(){
//        Intent broadcastIntent = new Intent();
//        broadcastIntent.setAction(DownloadProgressBroadcastReceiver._DOWNLOAD_PROGRESS_BROADCAST_RECEIVER);

        //iterate over list of VideoMetadataModel
        for(VideoFile videoFile : this.objects){
            Log.e(TAG, videoFile.vidname);
            try {
                //core download process
                URL sourceURL = new URL(videoFile.videopath); //request url
                URLConnection urlConnection = sourceURL.openConnection();
                urlConnection.connect();

                InputStream inputStream = new BufferedInputStream(sourceURL.openStream(), 8192);
                OutputStream outputStream = new FileOutputStream(*//*output folder*//*);

                byte data[] = new byte[4096];
                int count;
                int currentTotal = 0;
                while((count = inputStream.read(data)) != -1){
                    currentTotal += count;
                    outputStream.write(data, 0, count);
                }

                outputStream.flush();
                outputStream.close();
                inputStream.close();

                *//**
                 * decide where to put your files
                 *//*

            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }*/
}
