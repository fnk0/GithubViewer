package com.gabilheri.githubviewer.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gabilheri.githubviewer.data.feed.Feed;
import com.gabilheri.githubviewer.data.feed.FeedActor;
import com.gabilheri.githubviewer.utils.CustomUtils;
import com.j256.ormlite.dao.Dao;

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

    private Dao<Feed, Integer> feed = null;
    private Dao<FeedActor, Integer> feedActor = null;

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
