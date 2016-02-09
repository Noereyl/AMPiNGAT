package com.ampingat.ampingatapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Joy Rivera on 2/9/2016.
 */
public class PointModel {
    @SerializedName("coorX")
    @Expose
    public int coorX;

    @SerializedName("coorY")
    @Expose
    public int coorY;

    @SerializedName("weight")
    @Expose
    public int weight;

    @SerializedName("pointlabel")
    @Expose
    public String pointlabel;
}
