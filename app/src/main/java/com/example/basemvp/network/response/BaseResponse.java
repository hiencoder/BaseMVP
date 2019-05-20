package com.example.basemvp.network.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaseResponse implements Serializable {
    @SerializedName("statusCode")
    private int statusCode;
    @SerializedName("statusMessage")
    private String statusMessage;
    @SerializedName("dialogMessage")
    private String dialogMessage;

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public String getDialogMessage() {
        return dialogMessage;
    }
}
