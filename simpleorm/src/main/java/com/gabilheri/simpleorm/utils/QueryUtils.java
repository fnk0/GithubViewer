package com.gabilheri.simpleorm.utils;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 12/6/14.
 */
public class QueryUtils {

    public static String getColType(Class<?> col) {

        if ((col.equals(Boolean.class)) ||
                (col.equals(Boolean.TYPE)) ||
                (col.equals(Integer.class)) ||
                (col.equals(Integer.TYPE)) ||
                (col.equals(Long.class)) ||
                (col.equals(Long.TYPE)))  {
            return "INTEGER";
        }

        // SQlite v3 conventions
        if ((col.equals(Double.class)) || (col.equals(Double.TYPE)) || (col.equals(Float.class)) ||
                (col.equals(Float.TYPE))) {
            return "REAL";
        }

        // Save Date as text.
        if ((col.equals(String.class)) || (col.equals(Character.TYPE)) ||
                (col.equals(java.util.Date.class)) ||
                (col.equals(java.sql.Date.class)) ||
                (col.equals(java.util.Calendar.class))) {
            return "TEXT";
        }

        return "";
    }
}
