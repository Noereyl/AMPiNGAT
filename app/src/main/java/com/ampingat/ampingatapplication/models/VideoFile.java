package com.ampingat.ampingatapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Joy Rivera on 1/12/2016.
 */
public class VideoFile {

    @SerializedName("vidname")
    @Expose
    public String vidname;
    @SerializedName("videourl")
    @Expose
    public String videourl;
    @SerializedName("videopath")
    @Expose
    public String videopath;
    @SerializedName("videosize")
    @Expose
    public int videosize;

    public VideoFile(String fileUrl) {
        this.videourl = fileUrl;
    }

}
