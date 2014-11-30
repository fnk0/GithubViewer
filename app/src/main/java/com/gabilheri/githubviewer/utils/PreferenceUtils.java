package com.gabilheri.githubviewer.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.apache.http.NameValuePair;

import java.util.List;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/16/14.
 */
public class PreferenceUtils {


    public static String getStringPreference(Context context, String preference, String defaultValue) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(preference, defaultValue);
    }

    public static void clearAllPreferences(Context context) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.clear();
    }


    public static void setPreferenceList(Context context, List<NameValuePair> values) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();

        for(NameValuePair val : values) {
            try {
                editor.putInt(val.getName(), Integer.parseInt(val.getValue()));
            } catch (Exception ex) {
                editor.putString(val.getName(), val.getValue());
            }
        }
        editor.apply();
    }

    public static void setStringPreference(Context context, String name, String value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(name, value);
        editor.apply();
    }

    public static void setIntegerPreference(Context context, String name, int value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(name, value);
        editor.apply();
    }

    public static void setLongPreference(Context context, String name, Long value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(name, value);
        editor.apply();
    }

}
