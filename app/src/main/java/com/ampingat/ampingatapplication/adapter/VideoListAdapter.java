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
        if(convertView == null){
            holder = new Holder();
            convertView = View.inflate(mContext, R.layout.listview_item_firstaidvideolist, null);
            holder.videoFilename = (TextView)convertView.findViewById(R.id.videoTitle);
            convertView.setTag(holder);
        } else {
            holder = (Holder)convertView.getTag();
        }

        holder.videoFilename.setText(mVideoList.get(position));
        holder.directoryPath = mVideoList.get(position);

        return convertView;
    }

    static class Holder{
        TextView videoFilename;
        String directoryPath;
    }
}
