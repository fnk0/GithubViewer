package com.gabilheri.simpleorm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gabilheri.simpleorm.utils.MetaDataUtils;

import java.util.List;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 12/5/14.
 */

public abstract class SimpleOrmOpenHelper extends SQLiteOpenHelper {

    private Context context;

    public SimpleOrmOpenHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, MetaDataUtils.getDatabaseName(context), factory, MetaDataUtils.getDbVersion(context));
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for(Class<?> table : getTables()) {
            TableBuilder.createTable(table, db);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //todo make a better way of upgrading the DB. This is only good for caching.
        TableBuilder.deleteTables(getTables(), db, context);
        onCreate(db);
    }

    public void deleteTables() {
        TableBuilder.deleteTables(getTables(), getWritableDatabase(), context);
    }

    public abstract List<Class<?>> getTables();
}
