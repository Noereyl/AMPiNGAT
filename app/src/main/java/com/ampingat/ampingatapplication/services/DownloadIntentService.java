package com.ampingat.ampingatapplication.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;

import com.ampingat.ampingatapplication.models.VideoFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by Joy Rivera on 1/12/2016.
 *
 */
public class DownloadIntentService extends IntentService {

    public static final String TAG = "DownloadIntentService";
    public static DownloadProgressCallback downloadProgressCallback;
    private static final String rootDir = Environment.getExternalStorageDirectory() + "/AMPiNGAT";
    private static List<VideoFile> objects;

    public DownloadIntentService() {
        super("DownloadIntentService");
        File dirFile = new File(rootDir);
        if (dirFile.mkdir()) {
            Log.e(TAG, "created folder \"" + dirFile.getAbsolutePath() + "\"");
        } else {
            Log.e(TAG, "\"" + dirFile.getAbsolutePath() + "\" is present");
        }
    }

    public static void setSources(List<VideoFile> objects){
        DownloadIntentService.objects = objects;
    }

    private void finalizeVideoDetail() {
        for (int i = 0; i < objects.size(); i++) {
            String vidname = objects.get(i).videourl.substring(objects.get(i).videourl.lastIndexOf("/") + 1, objects.get(i).videourl.length());
            String vidpath = rootDir + "/" + vidname;
            objects.get(i).vidname = vidname;
            objects.get(i).videopath = vidpath;
        }
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        finalizeVideoDetail();
        performQueueDownload();
    }

    private void performQueueDownload() {
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(DownloadProgressBroadcastReceiver._DOWNLOAD_PROGRESS_BROADCAST_RECEIVER);

        //iterate over list of VideoMetadataModel
        for (VideoFile videoFile : this.objects) {
            Log.e(TAG, videoFile.vidname);
            try {
                //core download process
                URL sourceURL = new URL(videoFile.videourl); //request url
                URLConnection urlConnection = sourceURL.openConnection();
                urlConnection.connect();
                videoFile.videosize = urlConnection.getContentLength();
                if (downloadProgressCallback != null) {
                    downloadProgressCallback.onDownloadPrepare(videoFile.videosize, videoFile.vidname);
                }

                InputStream inputStream = new BufferedInputStream(sourceURL.openStream(), 8192);
                OutputStream outputStream = new FileOutputStream(videoFile.videopath);

                byte data[] = new byte[4096];
                int count;
                int currentTotal = 0;
                while ((count = inputStream.read(data)) != -1) {
                    currentTotal += count;
                    outputStream.write(data, 0, count);
                    if (downloadProgressCallback != null) {
                        downloadProgressCallback.onDownloadProgress(count, currentTotal);
                    }
                }

                outputStream.flush();
                outputStream.close();
                inputStream.close();

            } catch (IOException e) {
                if (downloadProgressCallback != null) {
                    downloadProgressCallback.onDownloadError();
                }
                e.printStackTrace();
            }
        }

        if (downloadProgressCallback != null) {
            downloadProgressCallback.onDownloadComplete();
        }
    }

    public static void setDownloadProgressCallback(DownloadProgressCallback callback) {
        downloadProgressCallback = callback;
    }

    public interface DownloadProgressCallback{

        void onDownloadProgress(int byteTransfer, int byteDownloaded);

        void onDownloadPrepare(int byteTotalSize, String filename);

        void onDownloadComplete();

        void onDownloadError();

    }
}
