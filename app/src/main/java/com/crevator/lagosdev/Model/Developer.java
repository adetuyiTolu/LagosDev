package com.crevator.lagosdev.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Slimfit on 3/6/2017.
 */

public class Developer implements Serializable {

    @SerializedName("login")
    private String userName;

    @SerializedName("avatar_url")
    private String imgURL;

    @SerializedName("html_url")
    private String profileURL;

    public Developer(String userName, String imgURL) {
        this.userName = userName;
        this.imgURL = imgURL;
    }

    public String getImgURL() {
        return imgURL;
    }

    public String getUserName() {
        return userName;
    }

    public void setProfileURL(String profileURL) {
        this.profileURL = profileURL;
    }

    public String getProfileURL() {
        return profileURL;
    }
}
