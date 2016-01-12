package com.ampingat.ampingatapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Joy Rivera on 1/12/2016.
 */
public class Student {

    @SerializedName("id_no")
    @Expose
    public String idNo;
    @SerializedName("lastname")
    @Expose
    public String lastname;
    @SerializedName("firstname")
    @Expose
    public String firstname;
    @SerializedName("created_at")
    @Expose
    public String createdAt;

}
