package com.gabilheri.simpleorm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gabilheri.simpleorm.annotations.Table;
import com.gabilheri.simpleorm.builders.OrmObject;
import com.gabilheri.simpleorm.builders.TableBuilder;
import com.gabilheri.simpleorm.utils.MetaDataUtils;
import com.gabilheri.simpleorm.utils.QueryUtils;

import java.util.List;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 12/5/14.
 */

public abstract class SimpleOrmOpenHelper extends SQLiteOpenHelper {


    private static final String _ID = "_ID";
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

    public long save(Class<?> ormClass, OrmObject obj) {
        SQLiteDatabase db = this.getWritableDatabase();
        return QueryUtils.save(ormClass, obj, db, this.getContext());
    }

    public void saveAll(Class<?> ormClass, List<?> list) {
        SQLiteDatabase db = this.getWritableDatabase();
        for(Object o : list) {
            QueryUtils.save(ormClass, (OrmObject) o, db, this.getContext());
        }

        this.closeDB();
    }

    public void delete(Class<?> ormClass, long id) {
        Table table = ormClass.getAnnotation(Table.class);
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table.name(), _ID + " = ?", new String[] {String.valueOf(id)});
        this.closeDB();
    }

    public void delete(Class<?> ormClass, String col, String value) {
        Table table = ormClass.getAnnotation(Table.class);
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table.name(), col + " = ?", new String[] {value});
        this.closeDB();
    }


    public void deleteAllEntriesForClass(Class<?> ormClass) {
        Table table = ormClass.getAnnotation(Table.class);
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table.name(), null, null);
        this.closeDB();
    }

    /**
     * Closes the Database Connection.
     */
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    public Context getContext() {
        return context;
    }
}
