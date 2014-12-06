package com.gabilheri.githubviewer.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gabilheri.simpleorm.annotations.Increments;
import com.gabilheri.simpleorm.annotations.NotNull;
import com.gabilheri.simpleorm.annotations.OrmField;
import com.gabilheri.simpleorm.annotations.Unique;
import com.gabilheri.simpleorm.utils.QueryUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/24/14.
 */
public class GithubDbHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = GithubDbHelper.class.getSimpleName();
    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "github.db";

    List<DatabaseObject> objectList;

    public GithubDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

    }


    @Override
    public void onUpgrade(SQLiteDatabase database,  int oldVersion, int newVersion) {

    }

    @Override
    public void close() {
        super.close();

    }

    public String getCreateTableQuery(DatabaseObject obj, String extra) {

        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ");
        sb.append(obj.getTableName());
        sb.append(" (");

        Field[] fields = obj.getClass().getDeclaredFields();

        for(Field f : fields) {

            String colType = QueryUtils.getColType(f.getClass());

            if(colType != null) {

                OrmField ormField = f.getAnnotation(OrmField.class);

                Annotation[] annotations = f.getAnnotations();

                for(Annotation a : annotations) {

                    if(a.annotationType() == Unique.class) {

                    }

                    if(a.annotationType() == NotNull.class) {

                    }

                    if(a.annotationType() == Increments.class) {

                    }
                }


            }

        }

        sb.append(");");


        return sb.toString();
    }
}
