package com.gabilheri.githubviewer.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.gabilheri.githubviewer.data.Owner;
import com.gabilheri.githubviewer.data.feed.Feed;
import com.gabilheri.githubviewer.data.feed.FeedActor;
import com.gabilheri.githubviewer.data.feed.FeedRepo;
import com.gabilheri.githubviewer.data.feed.Payload;
import com.gabilheri.githubviewer.data.feed.PayloadMember;
import com.gabilheri.githubviewer.data.feed.UserEvent;
import com.gabilheri.githubviewer.data.repo.Repo;
import com.gabilheri.githubviewer.data.repo.RepoContent;
import com.gabilheri.githubviewer.utils.CustomUtils;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/24/14.
 */
public class GithubDbHelper extends OrmLiteSqliteOpenHelper {

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
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        Log.i(LOG_TAG, "onCreate");

        try {
            TableUtils.createTable(connectionSource, Feed.class);
            TableUtils.createTable(connectionSource, FeedActor.class);
            TableUtils.createTable(connectionSource, FeedRepo.class);
            TableUtils.createTable(connectionSource, Payload.class);
            TableUtils.createTable(connectionSource, PayloadMember.class);
            TableUtils.createTable(connectionSource, Repo.class);
            TableUtils.createTable(connectionSource, UserEvent.class);
            TableUtils.createTable(connectionSource, RepoContent.class);
            TableUtils.createTable(connectionSource, Owner.class);

        } catch (SQLException ex) {
            Log.e(GithubDbHelper.class.getSimpleName(), "Can't create database", ex);
            throw new RuntimeException(ex);
        }
    }

    public Dao<Feed, Integer> getFeed() throws SQLException {

        if(feed == null) {
            this.feed = getDao(Feed.class);
        }

        return feed;
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(LOG_TAG, "onUpgrade");

            TableUtils.dropTable(connectionSource, Feed.class, true);
            TableUtils.dropTable(connectionSource, FeedActor.class, true);
            TableUtils.dropTable(connectionSource, FeedRepo.class, true);
            TableUtils.dropTable(connectionSource, Payload.class, true);
            TableUtils.dropTable(connectionSource, PayloadMember.class, true);
            TableUtils.dropTable(connectionSource, Repo.class, true);
            TableUtils.dropTable(connectionSource, UserEvent.class, true);
            TableUtils.dropTable(connectionSource, RepoContent.class, true);
            TableUtils.dropTable(connectionSource, Owner.class, true);

            onCreate(database, connectionSource);
        } catch (SQLException e) {
            Log.e(GithubDbHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
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
