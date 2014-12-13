package com.gabilheri.simpleorm.utils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.gabilheri.simpleorm.OrmInstance;
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

    public static void setFieldFromCursor(Cursor c, Field f, Object object, SQLiteDatabase db) {

        Class colType = f.getType();
        OrmField ormF = null;
        try {

            if(f.isAnnotationPresent(OrmField.class)) {
                ormF = f.getAnnotation(OrmField.class);
                f.setAccessible(true);
            } else {
                f.set(object, null);
                return;
            }

            if(colType == Double.TYPE
                    || colType == Double.class) {
                f.set(object, c.getDouble(c.getColumnIndex(ormF.name())));
            } else if(colType == Float.TYPE
                    || colType == Float.class) {
                f.set(object, c.getFloat(c.getColumnIndex(ormF.name())));
            } else if(colType == String.class
                    || colType == Date.class ||
                    colType == java.sql.Date.class) {
                f.set(object, c.getString(c.getColumnIndex(ormF.name())));
            } else if(colType == Integer.TYPE
                    || colType == Integer.class) {
                f.set(object, c.getInt(c.getColumnIndex(ormF.name())));
            } else if(colType == Long.TYPE
                    || colType == Long.class) {
                f.set(object, c.getLong(c.getColumnIndex(ormF.name())));
            } else if(colType == OrmInstance.class) {
                OrmInstance inst = QueryUtils.findById(colType.getClass(), db, c.getLong(c.getColumnIndex(ormF.name())));
                f.set(object, inst);
            }

        } catch (IllegalAccessException ex) {
            Log.d(LOG_TAG, "Illegal Access Exception!! " + ex.getMessage());
        }

    }

}
