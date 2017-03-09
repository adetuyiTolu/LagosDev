package com.crevator.lagosdev.rest;

import com.crevator.lagosdev.Model.Developer;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Slimfit on 3/6/2017.
 */

public class DevelopersResponse {

    @SerializedName("total_count")
    private int totalCount;

    @SerializedName("incomplete_results")
    private boolean incomplete;

    @SerializedName("items")
    public List<Developer> developers = new ArrayList<>();

}
