package com.gabilheri.githubviewer.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.util.Base64;

import com.gabilheri.githubviewer.MainActivity;

import java.io.UnsupportedEncodingException;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/23/14.
 */
public class CustomUtils {

    public static Typeface getGithubTypeface(Context context) {
        return Typeface.createFromAsset(context.getApplicationContext().getAssets(), "octicons.ttf");
    }

    public static String getBase64string(String base64) {
        byte[] data = Base64.decode(base64, Base64.DEFAULT);
        try {
            return new String(data, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            return "Problem decoding base 64 String!!";
        }
    }

    public static void lockOrientation(Activity context) {
        int currentOrientation = context.getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            context.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        } else {
            context.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        }
    }

    public static void hideDrawerToggle(MainActivity activity) {
        activity.getDrawerToggle().setDrawerIndicatorEnabled(false);
    }

    public static void showDrawerToggle(MainActivity activity) {
        activity.getDrawerToggle().setDrawerIndicatorEnabled(true);
    }

    public static void unlockOrientation(Activity context) {
        context.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }

    private static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
