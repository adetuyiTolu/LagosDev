package com.crevator.lagosdev.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.crevator.lagosdev.LagosDevApp;
import com.crevator.lagosdev.rest.DevelopersResponse;
import com.google.gson.Gson;

/**
 * Created by Adetuyi Tolu on 3/4/2017.
 */

public class SharedPreferenceHandler {
    private final static String PREF_FILE = "FACTORY_PREF";
    private final static String DEV_KEY = "devKey";

    /**
     * Save a string to shared preference
     *
     * @param key   - Key for value to be saved
     * @param value - Value to save for the key
     */
    public static void saveSharedPreferenceString(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(PREF_FILE, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * Save an integer to shared preference
     *
     * @param key   - Key for value to be saved
     * @param value - Value to save for the key
     */
    public static void saveSharedPreferenceInt(Context context, String key, int value) {
        SharedPreferences settings = context.getSharedPreferences(PREF_FILE, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * Save a Boolean to shared preference
     *
     * @param key   - Key for value to be saved
     * @param value - Value to save for the key
     */
    public static void saveSharedPreferenceBoolean(Context context, String key, boolean value) {
        SharedPreferences settings = context.getSharedPreferences(PREF_FILE, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * Get a string saved from shared preference
     *
     * @param key      - Key to look up in shared preferences.
     * @param defValue - Default value to be returned if shared preference isn't found.
     * @return value - String containing value of the shared preference if found.
     */
    public static String getSharedPreferenceString(Context context, String key, String defValue) {
        SharedPreferences settings = context.getSharedPreferences(PREF_FILE, 0);
        return settings.getString(key, defValue);
    }

    /**
     * Get an integer saved from shared preference
     *
     * @param key      - Key to look up in shared preferences.
     * @param defValue - Default value to be returned if shared preference isn't found.
     * @return value - String containing value of the shared preference if found.
     */
    static int getSharedPreferenceInt(Context context, String key, int defValue) {
        SharedPreferences settings = context.getSharedPreferences(PREF_FILE, 0);
        return settings.getInt(key, defValue);
    }

    /**
     * Get a boolean saved from shared preference
     *
     * @param key      - Key to look up in shared preferences.
     * @param defValue - Default value to be returned if shared preference isn't found.
     * @return value - String containing value of the shared preference if found.
     */
    static boolean getSharedPreferenceBoolean(Context context, String key, boolean defValue) {
        SharedPreferences settings = context.getSharedPreferences(PREF_FILE, 0);
        return settings.getBoolean(key, defValue);
    }

    public static void saveDeveloperRecords(Context context,DevelopersResponse developersResponse){
        Gson gson = new Gson();
        String devRecords = gson.toJson(developersResponse);
        saveSharedPreferenceString(context,DEV_KEY,devRecords);
    }

    public static DevelopersResponse fetchDeveloperRecords(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREF_FILE, 0);
        String records= settings.getString(DEV_KEY, "{}");
        return new Gson().fromJson(records,DevelopersResponse.class);
    }

}
