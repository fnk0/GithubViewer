package com.gabilheri.githubviewer.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.gabilheri.githubviewer.R;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 12/15/14.
 */
public class OwnerProvider extends ContentProvider {

    private static final int OWNER = 100;

    private GithubDbHelper dbHelper;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private static UriMatcher buildUriMatcher() {

        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = User.OwnerEntry.CONTENT_AUTHORITY;

        matcher.addURI(authority, User.OwnerEntry.PATH_OWNER + "/*", OWNER);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        dbHelper = new GithubDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        Cursor retCursor;
        switch (sUriMatcher.match(uri)) {
            case OWNER:
                retCursor = dbHelper.getWritableDatabase().query(
                        User.OwnerEntry.getTableName(),
                        projection,
                        "login = " + User.OwnerEntry.getLoginFromUri(uri),
                        null,
                        null,
                        null,
                        sortOrder
                );
                break;
            default:
                throw new UnsupportedOperationException(String.format(getContext().getString(R.string.unknown_uri), uri));

        }

        retCursor.setNotificationUri(getContext().getContentResolver(), uri);

        return retCursor;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);

        switch (match) {
            case OWNER:
                return User.OwnerEntry.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException(String.format(getContext().getString(R.string.unknown_uri), uri));
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case OWNER:
                long _id = db.insert(User.OwnerEntry.getTableName(), null, values);

                if(_id > 0) {
                    returnUri = User.OwnerEntry.buildOwner(_id);
                } else {
                    throw new UnsupportedOperationException(String.format(getContext().getString(R.string.failed_insert_row), uri));
                }
                break;
            default:
                throw new UnsupportedOperationException(String.format(getContext().getString(R.string.unknown_uri), uri));
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsDeleted;
        switch (match) {
            case OWNER:
                rowsDeleted = db.delete(User.OwnerEntry.getTableName(), selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException(String.format(getContext().getString(R.string.unknown_uri), uri));
        }

        if(selection == null || rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsUpdated;
        switch (match) {
            case OWNER:
                rowsUpdated = db.update(User.OwnerEntry.getTableName(), values, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException(String.format(getContext().getString(R.string.unknown_uri), uri));
        }
        if(rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }
}
