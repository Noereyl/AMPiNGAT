
package com.ampingat.ampingatapplication;
    import android.net.Uri;
    import android.os.Bundle;
    import android.os.Environment;
    import android.support.v4.app.Fragment;
    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.AdapterView;
    import android.widget.ListView;
    import android.widget.TextView;

    import com.ampingat.ampingatapplication.adapter.VideoListAdapter;
    import java.util.ArrayList;
    import java.util.List;

/**
 * Created by Reyle T. Revira on 8/23/2015.
 */
public class FirstAidFragment extends Fragment  {

    ListView videoListView;

    private static final String TAG = "FirstAidFragment";

    public static FirstAidFragment newInstance() {
        FirstAidFragment fragment = new FirstAidFragment();
        return fragment;
    }
    public FirstAidFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_firstaid, container, false);

        //bind widget to listview object
        videoListView = (ListView)rootView.findViewById(R.id.videoList);


        //create dummy values for list
        final List<String> videoList = new ArrayList<>();
        videoList.add("Adult CPR (Lay Rescuer)");
        videoList.add("Amputation");
        videoList.add("Bleeding Control (Capillary Bleeding)");
        videoList.add("Bleeding Control (Venous Bleeding)");
        videoList.add("Burns");
        videoList.add("Conscious Adult Choking ");
        videoList.add("CPR");
        videoList.add("Eye Injuries");
        videoList.add("Fainting");
        videoList.add("Hand Washing");
        videoList.add("Head, Neck and Back Injuries ");
        videoList.add("Heat Related Emergencies");
        videoList.add("How to use an Epipen");
        videoList.add("Musculoskeletal Injuries");
        videoList.add("Poison Control");
        videoList.add("Secondary Survey");
        videoList.add("Seizure");
        videoList.add("Shock");


        //instantiate video list adapter with the required parameters
        final VideoListAdapter videoListAdapter = new VideoListAdapter(getActivity().getApplicationContext(), videoList);

        //set adapter to video view
        videoListView.setAdapter(videoListAdapter);

        //set on item click listener to specify what actions are
        // to be done when clicking on a single item of the list
        videoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG, videoListAdapter.getItem(position));

                }
        }

        );

        return rootView;

    }

}


