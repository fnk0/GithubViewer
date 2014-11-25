package com.gabilheri.githubviewer.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gabilheri.githubviewer.utils.CustomUtils;

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

    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "github.db";

    List<DatabaseObject> objectList;

    public GithubDbHelper(Context context, List<DatabaseObject> objects) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.objectList = objects;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String getCreateTableQuery(DatabaseObject obj, String extra) {

        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ");
        sb.append(obj.getTableName());
        sb.append(" (");

        Field[] fields = obj.getClass().getDeclaredFields();

        for(Field f : fields) {
            switch (f.getType().getSimpleName()) {

                case "String":
                    sb.append(" TEXT NOT NULL, ");
                    sb.append(f.getName());
                    break;
                case "int":
                case "long":
                    sb.append(" INTEGER NOT NULL, ");
                    sb.append(f.getName());
                    break;
                default:
                    sb.append(" FOREIGN KEY (");
                    sb.append(f.getName());
                    sb.append(") REFERENCES ");
                    sb.append(CustomUtils.getTableNameForClass(f.getType().getSimpleName()));
                    sb.append(" (_ID)");
            }

        }


        sb.append(");");


        return sb.toString();
    }
}
