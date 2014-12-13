package com.gabilheri.simpleorm.utils;

import android.content.Context;
import android.text.format.DateFormat;

import java.util.Date;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 12/11/14.
 */
public class DateUtils {

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
}
