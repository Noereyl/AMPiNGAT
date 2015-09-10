package com.ampingat.ampingatapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ampingat.ampingatapplication.R;

import java.util.List;

/**
 * Created by OMIPLEKEVIN on 10/09/2015.
 *
 * this class implements ViewHolder pattern for ListView.
 *
 * All passed data values are for temporary purposes only.
 */
public class VideoListAdapter extends BaseAdapter {

    List<String> mVideoList;
    Context mContext;
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
        Holder holder;
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

            //set view holder as tag to convertview
            //we do this when we have to keep track on the data binded to that particular
            //view item of the list
            convertView.setTag(holder);
        } else {
            /**
             * convertView is alreaddy instantiated which we don't need to re-instantiate
             */

            //assign to view holder object the current view tag
            holder = (Holder)convertView.getTag();
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
