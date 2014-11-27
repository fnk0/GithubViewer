package com.gabilheri.githubviewer.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Typeface;

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

    public static String getTableNameForClass(String className) {

        switch (className) {

            case "Feed":
                return "TABLE_FEED";
            case "FeedActor":
                return "TABLE_FEED_ACTOR";
            case "FeedRepo":
                return "TABLE_FEED_REPO";
            case "Payload":
                return "TABLE_FEED_PAYLOAD";
            case "PayloadMember":
                return "PayloadMember";
            case "Repo":
                return "TABLE_REPO";
            case "RepoContent":
                return "TABLE_REPO_CONTENT";
        }
        return null;
    }

    public static void lockOrientation(Activity context) {
        int currentOrientation = context.getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            context.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        } else {
            context.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        }
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
