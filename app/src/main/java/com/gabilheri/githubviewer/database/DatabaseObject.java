package com.gabilheri.githubviewer.database;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/24/14.
 */
public abstract class DatabaseObject {

    private int _ID;

    protected DatabaseObject() {
    }

    public void set_ID(int _ID) {
        this._ID = _ID;
    }

    public int get_ID() {
        return _ID;
    }

    public abstract String getTableName();
}
