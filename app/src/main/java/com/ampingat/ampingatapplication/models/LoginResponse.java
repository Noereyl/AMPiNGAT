package com.ampingat.ampingatapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joy Rivera on 1/12/2016.
 */
public class LoginResponse {

    @SerializedName("success")
    @Expose
    public Integer success;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("user")
    @Expose
    public Student user;
    @SerializedName("videofiles")
    @Expose
    public List<VideoFile> videofiles = new ArrayList<VideoFile>();

}
