package com.ampingat.ampingatapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Joy Rivera on 2/9/2016.
 */
public class SendReportResponse {

    @SerializedName("success")
    @Expose
    public Integer success;

    @SerializedName("message")
    @Expose
    public String message;

}
