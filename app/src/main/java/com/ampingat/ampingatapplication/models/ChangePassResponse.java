package com.ampingat.ampingatapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Joy Rivera on 2/15/2016.
 */
public class ChangePassResponse {
    @SerializedName("success")
    @Expose
    public Integer success;
    @SerializedName("message")
    @Expose
    public String message;
}
