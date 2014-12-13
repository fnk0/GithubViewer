package com.gabilheri.githubviewer.utils;


import android.content.Context;
import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/30/14.
 */
public class CustomDateUtils {

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

    /**
     *
     * @param date
     * @param context
     * @return
     */
    public static String getLastUpdatedString(Date date, Context context) {

        Calendar rightNow = Calendar.getInstance();
        Calendar lastDate = Calendar.getInstance();
        lastDate.setTime(date);

        


        return null;
    }
}
