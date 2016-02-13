package com.ampingat.ampingatapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by Joy Rivera on 1/26/2016.
 */
public class UserSessionManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    private static final String PREFER_NAME = "SessionLogin";
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";
    public static final String KEY_NAME = "name";
    public static final String KEY_ID_NUMBER = "userid";
    public static final String KEY_TYPE = "usertype";

    public UserSessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREFER_NAME, Context.MODE_PRIVATE);
    }
    public void createUserLoginSession (String name, String userid, String usertype) {
        editor = pref.edit();
        editor.putBoolean(IS_USER_LOGIN, true);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_ID_NUMBER, userid);
        editor.putString(KEY_TYPE, usertype);
        editor.apply();
    }

    public boolean checkLogin(){
        // Check login status
        return this.isUserLoggedIn();
    }

    public HashMap<String, String> getUserDetails () {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_ID_NUMBER, pref.getString(KEY_ID_NUMBER, null));
        user.put(KEY_TYPE, pref.getString(KEY_TYPE, null));
        return user;
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, LoginActivity.class);
        context.startActivity(i);
    }

    public boolean isUserLoggedIn() {
        return pref.getBoolean(IS_USER_LOGIN, false);
    }
}
