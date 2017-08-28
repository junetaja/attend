package id.co.uti.utiattendance.module.attendance.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DELL on 8/25/2017.
 */

public class CheckinModel {

    @SerializedName("userid")
    public String userid;

    @SerializedName("location_in")
    public String location_in;

    @SerializedName("note")
    public String note;

    @SerializedName("latitude")
    public double latitude;

    @SerializedName("longitude")
    public double longitude;

    @SerializedName("device")
    public String device;
}
