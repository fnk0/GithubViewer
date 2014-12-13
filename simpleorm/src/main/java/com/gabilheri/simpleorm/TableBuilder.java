package com.gabilheri.simpleorm;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.gabilheri.simpleorm.annotations.Increments;
import com.gabilheri.simpleorm.annotations.NotNull;
import com.gabilheri.simpleorm.annotations.OrmField;
import com.gabilheri.simpleorm.annotations.Table;
import com.gabilheri.simpleorm.annotations.Unique;
import com.gabilheri.simpleorm.utils.QueryUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

import gabilheri.com.simpleorm.R;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 12/6/14.
 */
public class TableBuilder {

    private static final String LOG_TAG = TableBuilder.class.getSimpleName();

    public static boolean createTable(Class<?> ormClass, SQLiteDatabase db) {

        if(ormClass.getAnnotations().length == 0) {
            return false;
        }

        Table table = ormClass.getAnnotation(Table.class);

        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ");
        sb.append(table.name());
        sb.append(" (");

        if(table.id()) {
            sb.append( " _ID INTEGER PRIMARY KEY AUTOINCREMENT, ");
        }

        Field[] fields = ormClass.getDeclaredFields();

        for(Field f : fields) {

            if(f.getAnnotations().length > 0) {
                String colType = QueryUtils.getColType(f.getType());

                if (colType != null) {

                    OrmField ormField = f.getAnnotation(OrmField.class);

                    if(ormField.foreignKey()) {
                        String colName = ormField.name().equals("") ? f.getName() : ormField.name();
                        sb.append(colName).append(" INTEGER NOT NULL, ");
                        sb.append("FOREIGN KEY (");
                        sb.append(colName).append(") REFERENCES ");
                        sb.append(ormField.referenceTable()).append(" (");
                        sb.append(ormField.refKey()).append(")");
                    } else {
                        sb.append(ormField.name()).append(" ");
                        Log.i(LOG_TAG, "Col Type: " + colType);
                        sb.append(colType).append(" ");
                        Annotation[] annotations = f.getAnnotations();

                        for (Annotation a : annotations) {

                            if (a.annotationType() == Unique.class) {
                                sb.append("UNIQUE ");
                            }

                            if (a.annotationType() == Increments.class) {
                                sb.append("AUTOINCREMENT ");
                            }

                            if (a.annotationType() == NotNull.class) {
                                sb.append("NOT NULL ");
                            }
                        }
                    }
                    sb.append(", ");
                }
            }
        }

        sb.delete(sb.length() -2 , sb.length());
        sb.append(");");

        try {
            String str = sb.toString();
            db.execSQL(sb.toString());
            Log.d(LOG_TAG, sb.toString());
            return true;
        } catch (SQLException ex) {
            Log.d(LOG_TAG, "Could not create DB for: " + table.name());
            ex.printStackTrace();
            return false;
        }
    }

    public static void deleteTables(List<Class<?>> tables, SQLiteDatabase db, Context context) {

        for(Class<?> table : tables) {

            Table t = table.getAnnotation(Table.class);

            db.execSQL(String.format(context.getResources().getString(R.string.drop_table), t.name()));
        }
    }
}
