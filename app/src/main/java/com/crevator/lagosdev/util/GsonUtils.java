package com.crevator.lagosdev.util;

import com.google.gson.Gson;

/**
 * Created by Adetuyi Tolu on 3/4/2017.
 */

public class GsonUtils {
    private static GsonUtils instance;
    private Gson gson;

    public static GsonUtils init() {
        if (instance == null) {
            instance = new GsonUtils();
        }
        return instance;
    }

    private GsonUtils() {
        gson = new Gson();
    }

    public Gson getGson() {
        return gson;
    }
}
