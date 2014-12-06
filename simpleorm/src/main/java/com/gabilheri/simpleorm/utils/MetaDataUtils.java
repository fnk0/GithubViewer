package com.gabilheri.simpleorm.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import gabilheri.com.simpleorm.R;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 12/6/14.
 */
public class MetaDataUtils {

    private static final String LOG_TAG = MetaDataUtils.class.getSimpleName();

    public static int getDbVersion(Context context) {
        Integer databaseVersion = getIntegerMetaData(context, context.getString(R.string.db_version));

        if ((databaseVersion == null) || (databaseVersion == 0)) {
            databaseVersion = context.getResources().getInteger(R.integer.db_default_version);
        }

        return databaseVersion;
    }


    public static String getDatabaseName(Context context) {
        String databaseName = getStringMetaData(context, context.getString(R.string.db_name));

        if (databaseName == null) {
            databaseName = context.getString(R.string.db_default_name);
        }

        return databaseName;
    }



    public static boolean getDebug(Context context) {
        return getBooleanMetaData(context, context.getString(R.string.db_log));
    }


    public static Integer getIntegerMetaData(Context context, String name) {
        Integer value = null;

        PackageManager pm = context.getPackageManager();
        try {
            ApplicationInfo ai = pm.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            value = ai.metaData.getInt(name);
        } catch (Exception e) {
            Log.d(LOG_TAG, "Couldn't find integer value: " + name);
        }

        return value;
    }

    private static String getStringMetaData(Context context, String name) {
        String value = null;

        PackageManager pm = context.getPackageManager();
        try {
            ApplicationInfo ai = pm.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            value = ai.metaData.getString(name);
        } catch (Exception e) {
            Log.d(LOG_TAG, "Couldn't find string value: " + name);
        }

        return value;
    }

    private static Boolean getBooleanMetaData(Context context, String name) {
        Boolean value = false;

        PackageManager pm = context.getPackageManager();
        try {
            ApplicationInfo ai = pm.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            value = ai.metaData.getBoolean(name);
        } catch (Exception e) {
            Log.d(LOG_TAG, "Couldn't find boolean value: " + name);
        }

        return value;
    }

}
