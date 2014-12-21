package com.gabilheri.simpleorm.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.gabilheri.simpleorm.annotations.OrmField;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 12/13/14.
 */
public class FieldUtils {

    public static final String LOG_TAG = FieldUtils.class.getSimpleName();

    public static void setFieldFromCursor(Cursor c, Field f, Object object, SQLiteDatabase db, Context context) {

        Class colType = f.getType();
        OrmField ormF = null;
        try {

            if(f.isAnnotationPresent(OrmField.class) && object != null) {
                ormF = f.getAnnotation(OrmField.class);
                f.setAccessible(true);
            } else {
                Log.d(LOG_TAG, "No ORM field present!");
                f.set(object, null);
                return;
            }

            //Log.d(LOG_TAG, "Col Type: " + colType);

            if(colType == Double.TYPE
                    || colType == Double.class) {
                f.set(object, c.getDouble(c.getColumnIndex(ormF.name())));
            } else if(colType == Float.TYPE
                    || colType == Float.class) {
                f.set(object, c.getFloat(c.getColumnIndex(ormF.name())));
            } else if(colType == String.class) {
                f.set(object, c.getString(c.getColumnIndex(ormF.name())));
            } else if(colType == Integer.TYPE
                    || colType == Integer.class) {
                f.set(object, c.getInt(c.getColumnIndex(ormF.name())));
            } else if(colType == Long.TYPE
                    || colType == Long.class) {
                f.set(object, c.getLong(c.getColumnIndex(ormF.name())));
            } else if(colType == Date.class ||
                    colType == java.sql.Date.class) {
                f.set(object, DateUtils.getDateFromString(c.getString(c.getColumnIndex(ormF.name())), context));
            } else {//if(colType.isAssignableFrom(OrmObject.class)) {
                //Log.d(LOG_TAG, "Found ORM Object of type" + colType);
                Object inst = QueryUtils.findById(colType, db, c.getLong(c.getColumnIndex(ormF.name())), context);
                f.set(object, inst);
            }

        } catch (IllegalAccessException ex) {
            Log.d(LOG_TAG, "Illegal Access Exception!! " + ex.getMessage());
        }
    }
}
