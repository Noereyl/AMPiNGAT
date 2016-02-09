package com.ampingat.ampingatapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements ActionBar.TabListener {


    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session = new UserSessionManager(getApplicationContext());
        Toast.makeText(getApplicationContext(),
                "User Login Status: " + session.isUserLoggedIn(),
                Toast.LENGTH_LONG).show();
        if(session.checkLogin())
            finish();

        HashMap<String, String> user = session.getUserDetails();

        String userid = user.get(UserSessionManager.KEY_ID_NUMBER);
        String name = user.get(UserSessionManager.KEY_NAME);
        String type = user.get(UserSessionManager.KEY_TYPE);

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_user_profile) {
            return true;
        }
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_about) {
            return true;
        }
        if (id == R.id.action_signout) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showProfile(MenuItem item){
        Toast.makeText(this, "Welcome to your profile!", Toast.LENGTH_LONG).show();
        Intent i = new Intent(MainActivity.this, WelcomeActivity.class);
        startActivity(i);
        finish();
    }

    public void reporting(MenuItem item){
        Intent i = new Intent(MainActivity.this, EmergencyReportActivity.class);
        startActivity(i);
        finish();
    }



    public void logout(MenuItem item) {
        Intent i = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        String [] tabTitles;
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
            tabTitles = getResources().getStringArray(R.array.tabPages);

        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            switch (position) {
                case 0:
                    return EmergencyFragment.newInstance();
                case 1:
                    return EapFragment.newInstance();
                case 2:
                    return FirstAidFragment.newInstance();
            }
            return null;
        }
        @Override
        public int getCount() {
            // Show 3 total pages.
            return tabTitles.length;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return tabTitles[position].toUpperCase(l);
                case 1:
                    return tabTitles[position].toUpperCase(l);
                case 2:
                    return tabTitles[position].toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;

        }
        public void firstFloorMap(View view)
        {
            Intent intent = new Intent(view.getContext(), FirstFloorActivity.class);
            startActivity(intent);
        }

        public void secondFloorMap(View view)
        {
            Intent intent = new Intent(view.getContext(), SecondFloorActivity.class);
            startActivity(intent);
        }

        public void thirdFloorMap(View view)
        {
            Intent intent = new Intent(view.getContext(), ThirdFloorActivity.class);
            startActivity(intent);
        }
        public void fourthFloorMap(View view)
        {
            Intent intent = new Intent(view.getContext(), FourthFloorActivity.class);
            startActivity(intent);
        }

    }
}

