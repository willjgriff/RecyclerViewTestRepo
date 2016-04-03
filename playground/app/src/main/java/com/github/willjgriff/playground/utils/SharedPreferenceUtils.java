package com.github.willjgriff.playground.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;

/**
 * Created by Will on 03/04/2016.
 */
public class SharedPreferenceUtils {

    public static final String SHARED_PREFS_NAME = "com.github.willjgriff.playground.utils;SHARED_PREFS_NAME";

    public static final String SHARED_MOVIES_CONFIG = "com.github.willjgriff.playground.utils;SHARED_MOVIES_CONFIG";

    public static void writeToPreferences(Context context, String key, Object value) {
        Gson gson = new Gson();
        SharedPreferences prefs = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        Editor prefsEditor = prefs.edit();
        String json = gson.toJson(value);
        prefsEditor.putString(key, json).apply();
    }

    /**
     * This requires casting the returned object after calling. This isn't great.
     * TODO: I think I need to understand Generics to return the correct type.
     */
    public static Object readFromPreferences(Context context, String key, Class<?> returnType) {
        Gson gson = new Gson();
        SharedPreferences prefs = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        Object object = gson.fromJson(prefs.getString(key, ""), returnType);
        return object;
    }

}
