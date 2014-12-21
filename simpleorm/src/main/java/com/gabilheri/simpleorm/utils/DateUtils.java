package com.gabilheri.simpleorm.utils;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 12/11/14.
 */
public class DateUtils {

    private static final String LOG_TAG = DateUtils.class.getSimpleName();

    /**
     *
     * @param date
     *        Date to be formatted
     * @param context
     *        Context to be used by the DateFormat
     * @return
     *      The formatted date in medium format.
     */
    public static String getMediumDate(Date date, Context context) {
        java.text.DateFormat format = DateFormat.getMediumDateFormat(context);
        return format.format(date);
    }

    public static String getLongDate(Date date, Context context) {
        java.text.DateFormat format = DateFormat.getLongDateFormat(context);
        return format.format(date);
    }

    public static Date getDateFromString(String dateStr, Context context) {
        java.text.DateFormat format = DateFormat.getMediumDateFormat(context);
        if(dateStr != null) {
            try {
                return format.parse(dateStr);
            } catch (ParseException ex) {
                Log.d(LOG_TAG, "Parse Exception: " + ex.getMessage());
                return null;
            }
        } else {
            return new Date();
        }

    }
}
