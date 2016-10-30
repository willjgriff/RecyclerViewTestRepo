package com.github.willjgriff.playground.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by Will on 03/04/2016.
 */
public class SharedPreferenceUtils {

    public static final String SHARED_PREFS_NAME = "com.github.willjgriff.playground.utils;SHARED_PREFS_NAME";

    public static final String SHARED_MOVIES_CONFIG = "com.github.willjgriff.playground.utils;SHARED_MOVIES_CONFIG";

    public static <T> void writeObjectToPreferences(Context context, String key, T value) {
        Gson gson = new Gson();
        String json = gson.toJson(value);
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key, json).apply();
    }

    public static <T> T readObjectFromPreferences(Context context, String key, Class<T> returnType) {
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        return gson.fromJson(sharedPreferences.getString(key, ""), returnType);
    }

    public static <T> T readComplexObjectFromPreferences(Context context, String key, TypeToken<T> typeToken) {
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return gson.fromJson(sharedPreferences.getString(key, ""), typeToken.getType());
    }

}
