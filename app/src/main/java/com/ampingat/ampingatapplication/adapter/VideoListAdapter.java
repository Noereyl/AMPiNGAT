package com.ampingat.ampingatapplication.adapter;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.ampingat.ampingatapplication.R;
import java.util.List;


public class VideoListAdapter extends BaseAdapter {

    List<String> mVideoList;
    Context mContext;
    VideoView videoView;
    Button btn;

    public VideoListAdapter(Context context, List<String> videoList){
        mContext = context;
        mVideoList = videoList;
    }

    @Override
    public int getCount() {

        return mVideoList.size();
    }

    @Override
    public String getItem(int position) {

        return mVideoList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Holder holder;
        /**
         * if current view is null, the view will be instantiated with the specified custom view using View.inflate(...)
         */
        if(convertView == null){
            //instantiate view holder class
            holder = new Holder();

            //bind layout to convertView object
            convertView = View.inflate(mContext, R.layout.listview_item_firstaidvideolist, null);

            //bind view widgets from convertView to view holder class
            holder.videoFilename = (TextView)convertView.findViewById(R.id.videoTitle);

            //we do this when we have to keep track on the data binded to that particular
            //view item of the list
            convertView.setTag(holder);
        } else {
            /**
             * convertView is already instantiated which we don't need to re-instantiate
             */

            //assign to view holder object the current view tag
            holder = (Holder)convertView.getTag();

            if(holder.videoFilename.equals("Adult CPR (Lay Rescuer)")) {
                videoView = (VideoView) convertView.findViewById(R.id.videoEntry);
                MediaController mc = new MediaController(mContext);
                mc.setAnchorView(videoView);
                mc.setMediaPlayer(videoView);
                videoView.setMediaController(mc);
                holder.directoryPath = (Environment.getExternalStorageDirectory() + "/Download/Adult CPR - Lay Rescuer.mp4");
                videoView.setVideoURI(Uri.parse(holder.directoryPath));

                holder.videoFilename.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        videoView.start();

                    }
                });
            }

            if(holder.videoFilename.equals("Amputation")) {
                videoView = (VideoView) convertView.findViewById(R.id.videoEntry);
                MediaController mc = new MediaController(mContext);
                mc.setAnchorView(videoView);
                mc.setMediaPlayer(videoView);
                videoView.setMediaController(mc);
                holder.directoryPath = (Environment.getExternalStorageDirectory() + "/Download/Amputation.mp4");
                videoView.setVideoURI(Uri.parse(holder.directoryPath));

                holder.videoFilename.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.videoFilename.setVisibility(View.INVISIBLE);
                        videoView.start();

                    }
                });
            }
        }
        //assign variables to the view holder fields
        holder.videoFilename.setText(mVideoList.get(position));
        holder.directoryPath = mVideoList.get(position);

        //return convertView with the data being loaded
        return convertView;
    }

    /**
     * static class implementation for memory efficiency
     */
    static class Holder{
        TextView videoFilename;
        String directoryPath;
    }
}