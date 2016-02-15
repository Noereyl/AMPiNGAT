
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
 * Created by Joy Rivera on 8/23/2015.
 */
public class FirstAidFragment extends Fragment  {

    ListView videoListView;

    private static final String TAG = "FirstAidFragment";

    public static FirstAidFragment newInstance() {
        FirstAidFragment fragment = new FirstAidFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_firstaid, container, false);

        //bind widget to listview object
        videoListView = (ListView)rootView.findViewById(R.id.videoList);

        //create dummy values for list
        List<VideoMetaData> videoList = new ArrayList<VideoMetaData>();

        for (int i=0;i<17;i++){
            VideoMetaData data;
            data = new VideoMetaData();
            if (i == 0){
                String fileName = "Adult CPR (Lay Rescuer)";
                String size = "Size: 6:51 MB";
                String length = "Length: 00:03:36";
                Integer image = R.drawable.ic_adult_cpr;
                data.setFileName(fileName);
                data.setFileSize(size);
                data.setFileLength(length);
                data.setFileImage(image);
                data.setFilePath(Environment.getExternalStorageDirectory() + "/AMPiNGAT/Adult_CPR_-_Lay_Rescuer.mp4");
            } else if (i == 1) {
                String fileName = "Amputation";
                String size = "Size: 16.9 MB";
                String length = "Length: 00:06:49";
                Integer image = R.drawable.ic_amputation;
                data.setFileLength(length);
                data.setFileSize(size);
                data.setFileName(fileName);
                data.setFileImage(image);
                data.setFilePath(Environment.getExternalStorageDirectory() + "/Download/AmpingatVid/Amputation.mp4");
            } else if (i == 2) {
                String fileName = "Bleeding Control (Capillary Bleeding)";
                data.setFileName(fileName);
                String size = "Size: 7.76 MB";
                String length = "Length: 00:03:26";
                Integer image = R.drawable.ic_bleeding;
                data.setFileLength(length);
                data.setFileSize(size);
                data.setFileImage(image);
                data.setFilePath(Environment.getExternalStorageDirectory() +"/Download/AmpingatVid/Bleeding Control Capillary Bleeding.mp4");
            } else if (i == 3) {
                String fileName = "Bleeding Control (Venous Bleeding)";
                data.setFileName(fileName);
                String size = "Size: 6.36 MB";
                String length = "Length: 00:02:48";
                Integer image = R.drawable.ic_venous;
                data.setFileLength(length);
                data.setFileSize(size);
                data.setFileImage(image);
                data.setFilePath(Environment.getExternalStorageDirectory() +"/Download/AmpingatVid/Bleeding Control Venous Bleeding.mp4");
            } else if (i == 4) {
                String fileName = "Burns";
                data.setFileName(fileName);
                String size = "Size: 12.4 MB";
                String length = "Length: 00:06:56";
                Integer image = R.drawable.ic_burns;
                data.setFileLength(length);
                data.setFileSize(size);
                data.setFileImage(image);
                data.setFilePath(Environment.getExternalStorageDirectory() +"/Download/AmpingatVid/Burns.mp4");
            } else if (i == 5) {
                String fileName = "Conscious Adult Choking";
                data.setFileName(fileName);
                String size = "Size: 3.37 MB";
                String length = "Length: 00:01:43";
                Integer image = R.drawable.ic_choking;
                data.setFileLength(length);
                data.setFileSize(size);
                data.setFileImage(image);
                data.setFilePath(Environment.getExternalStorageDirectory() +"/Download/AmpingatVid/Conscious Adult Choking.mp4");
            } else if (i == 6) {
                String fileName = "CPR";
                data.setFileName(fileName);
                String size = "Size: 10.4 MB";
                String length = "Length: 00:05:35";
                Integer image = R.drawable.ic_cpr;
                data.setFileLength(length);
                data.setFileSize(size);
                data.setFileImage(image);
                data.setFilePath(Environment.getExternalStorageDirectory() +"/Download/AmpingatVid/CPR.mp4");
            } else if (i == 7) {
                String fileName = "Eye Injuries";
                data.setFileName(fileName);
                String size = "Size: 7.83 MB";
                String length = "Length: 00:03:33";
                Integer image = R.drawable.ic_eye_injuries;
                data.setFileLength(length);
                data.setFileSize(size);
                data.setFileImage(image);
                data.setFilePath(Environment.getExternalStorageDirectory() +"/Download/AmpingatVid/Eye Injuries.mp4");
            } else if (i == 8) {
                String fileName = "Fainting";
                data.setFileName(fileName);
                String size = "Size: 3.24 MB";
                String length = "Length: 00:01:11";
                Integer image = R.drawable.ic_fainting;
                data.setFileLength(length);
                data.setFileSize(size);
                data.setFileImage(image);
                data.setFilePath(Environment.getExternalStorageDirectory() +"/Download/AmpingatVid/Fainting.mp4");
            } else if (i == 9) {
                String fileName = "Hand Washing";
                data.setFileName(fileName);
                String size = "Size: 10.2 MB";
                String length = "Length: 00:04:58";
                Integer image = R.drawable.ic_hand_washing;
                data.setFileLength(length);
                data.setFileSize(size);
                data.setFileImage(image);
                data.setFilePath(Environment.getExternalStorageDirectory() +"/Download/AmpingatVid/Hand Washing.mp4");
            } else if (i == 10) {
                String fileName = "Head, Neck and Back Injuries";
                String size = "Size: 5.60 MB";
                String length = "Length: 00:02:01";
                Integer image = R.drawable.ic_hbn_injuries;
                data.setFileName(fileName);
                data.setFileLength(length);
                data.setFileSize(size);
                data.setFileImage(image);
                data.setFilePath(Environment.getExternalStorageDirectory() +"/Download/AmpingatVid/Head, Neck and Back Injuries.mp4");
            } else if (i == 11) {
                String fileName = "Heat Related Emergencies";
                data.setFileName(fileName);
                String size = "Size: 20.2 MB";
                String length = "Length: 00:07:55";
                Integer image = R.drawable.ic_hrm;
                data.setFileLength(length);
                data.setFileSize(size);
                data.setFileImage(image);
                data.setFilePath(Environment.getExternalStorageDirectory() +"/Download/AmpingatVid/Heat Related Emergencies.mp4");
            } else if (i == 12) {
                String fileName = "How to use an Epipen";
                data.setFileName(fileName);
                String size = "Size: 6.24 MB";
                String length = "Length: 00:02:44";
                Integer image = R.drawable.ic_epipen;
                data.setFileLength(length);
                data.setFileSize(size);
                data.setFileImage(image);
                data.setFilePath(Environment.getExternalStorageDirectory() +"/Download/AmpingatVid/How To Use An Epipen.mp4");
            } else if (i == 13) {
                String fileName = "Musculoskeletal Injuries";
                data.setFileName(fileName);
                String size = "Size: 16.2 MB";
                String length = "Length: 00:05:00";
                Integer image = R.drawable.ic_musko;
                data.setFileLength(length);
                data.setFileSize(size);
                data.setFileImage(image);
                data.setFilePath(Environment.getExternalStorageDirectory() +"/Download/AmpingatVid/Musculoskeletal Injuries.mp4");
            } else if (i == 14) {
                String fileName = "Poison Control";
                data.setFileName(fileName);
                String size = "Size: 5.35 MB";
                String length = "Length: 00:02:29";
                Integer image = R.drawable.ic_poison;
                data.setFileLength(length);
                data.setFileSize(size);
                data.setFileImage(image);
                data.setFilePath(Environment.getExternalStorageDirectory() +"/Download/AmpingatVid/Poison Control.mp4");
            } else if (i == 15) {
                String fileName = "Secondary Survey";
                data.setFileName(fileName);
                String size = "Size: 5.69 MB";
                String length = "Length: 00:02:47";
                Integer image = R.drawable.ic_secondary;
                data.setFileLength(length);
                data.setFileSize(size);
                data.setFileImage(image);
                data.setFilePath(Environment.getExternalStorageDirectory() + "/Download/AmpingatVid/Secondary Survey.mp4");
            } else if (i == 16) {
                String fileName = "Seizure";
                data.setFileName(fileName);
                String size = "Size: 5.13 MB";
                String length = "Length: 00:02:09";
                Integer image = R.drawable.ic_seizure;
                data.setFileLength(length);
                data.setFileSize(size);
                data.setFileImage(image);
                data.setFilePath(Environment.getExternalStorageDirectory() +"/Download/AmpingatVid/Seizure.mp4");
            } else {
                String fileName = "Shock";
                data.setFileName(fileName);
                String size = "Size: 9.86 MB";
                String length = "Length: 00:05:41";
                Integer image = R.drawable.ic_shock;
                data.setFileLength(length);
                data.setFileSize(size);
                data.setFileImage(image);
                data.setFilePath(Environment.getExternalStorageDirectory() +"/Download/AmpingatVid/Shock.mp4");
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


