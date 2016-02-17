package com.ampingat.ampingatapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joy Rivera on 2/15/2016.
 */

public class RequestRoutesResponse {

    @SerializedName("success")
    @Expose
    public Integer success;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("shortestRoute")
    @Expose
    public List<String> shortestRoute = new ArrayList<String>();
    @SerializedName("secondRoute")
    @Expose
    public List<String> secondRoute = new ArrayList<String>();
    @SerializedName("thirdRoute")
    @Expose
    public List<String> thirdRoute = new ArrayList<String>();

}
