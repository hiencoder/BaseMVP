package com.example.basemvp.common;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharePref {
    private static final String SHARED_NAME = "BASE_PROJECT";
    private static final String WALK_THOUGH = "SHOW_WALK_THOUGH";
    private static final String FLAG_LOGIN = "FLAG_LOGIN";
    private static final String USER_TOKEN = "USER_TOKEN";


    private static MySharePref mInstance;
    private SharedPreferences sharedPref;

    private MySharePref(Context context) {
        sharedPref = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized MySharePref getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MySharePref(context);
        }
        return mInstance;
    }

    public void saveFlagWalkThough() {
        putBoolean(WALK_THOUGH, true);
        //sharedPref.edit().putBoolean(WALK_THOUGH, true).apply();
    }

    public boolean checkFlagWalkThough() {
        return getBoolean(WALK_THOUGH, false);
        //return sharedPref.getBoolean(WALK_THOUGH, false);
    }

    public void saveFlagLogin(boolean value) {
        putBoolean(FLAG_LOGIN, value);
        // sharedPref.edit().putBoolean(FLAG_LOGIN, value).apply();
    }

    public boolean checkFlagLogin() {
        //return sharedPref.getBoolean(FLAG_LOGIN, false);
        return getBoolean(FLAG_LOGIN, false);
    }

    public void saveUserToken(String token) {
        //sharedPref.edit().putString(USER_TOKEN, token).apply();
        putString(USER_TOKEN, token);
    }

    public String getUserToken() {
        //return sharedPref.getString(USER_TOKEN, "");
        return getString(USER_TOKEN, "");
    }

    public void putInt(String key, int value) {
        sharedPref.edit().putInt(key, value).apply();
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    public int getInt(String key, int defaultValue) {
        return sharedPref.getInt(key, defaultValue);
    }

    /**
     * @param key
     * @param value
     */
    public void putString(String key, String value) {
        sharedPref.edit().putString(key, value).apply();
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    public String getString(String key, String defaultValue) {
        return sharedPref.getString(key, defaultValue);
    }

    /**
     * @param key
     * @param value
     */
    public void putBoolean(String key, boolean value) {
        sharedPref.edit().putBoolean(key, value).apply();
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedPref.getBoolean(key, defaultValue);
    }

    /**
     * @param key
     * @param value
     */
    public void putLong(String key, long value) {
        sharedPref.edit().putLong(key, value).apply();
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    public long getLong(String key, long defaultValue) {
        return sharedPref.getLong(key, defaultValue);
    }

    /**
     * @param key
     * @param value
     */
    public void putFloat(String key, float value) {
        sharedPref.edit().putFloat(key, value).apply();
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    public float getFloat(String key, float defaultValue) {
        return sharedPref.getFloat(key, defaultValue);
    }
}
