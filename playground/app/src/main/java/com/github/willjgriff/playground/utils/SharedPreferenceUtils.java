package com.github.willjgriff.playground.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

/**
 * Created by Will on 03/04/2016.
 */
public class SharedPreferenceUtils {

    public static final String SHARED_PREFS_NAME = "com.github.willjgriff.playground.utils;SHARED_PREFS_NAME";

    public static final String SHARED_MOVIES_CONFIG = "com.github.willjgriff.playground.utils;SHARED_MOVIES_CONFIG";

    public static void writeObjectToPreferences(Context context, String key, Object value) {
        Gson gson = new Gson();
        SharedPreferences prefs = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        Editor prefsEditor = prefs.edit();
        String json = gson.toJson(value);
        prefsEditor.putString(key, json).apply();
    }

    public static <T> T readObjectFromPreferences(Context context, String key, Class<T> returnType) {
        PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        SharedPreferences prefs = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        return gson.fromJson(prefs.getString(SHARED_MOVIES_CONFIG, ""), returnType);
    }

}
