package com.gabilheri.githubviewer.data;

import android.net.Uri;
import android.provider.BaseColumns;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/16/14.
 */
public class DataContract {

    public static final String CONTENT_AUTHORITY = "com.gabilheri.githubviewer.app";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String DATE_FORMAT = "yyyyMMdd";

    public static final String PATH_REPOS = "repos";

    public static String getDbDateString(Date date){
        // Because the API returns a unix timestamp (measured in seconds),
        // it must be converted to milliseconds in order to be converted to valid date.
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }

    public static Date getDateFromDb(String dateText) {
        SimpleDateFormat dbDateFormat = new SimpleDateFormat(DATE_FORMAT);
        try {
            return dbDateFormat.parse(dateText);
        } catch ( ParseException e ) {
            e.printStackTrace();
            return null;
        }
    }

    public static final class RepoEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_REPOS).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_REPOS;
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_REPOS;

        public static final String TABLE_NAME = "repos";

        public static final String COLUMN_REPO_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_FULL_NAME = "full_name";
        public static final String COLUMN_OWNER = "owner";
        public static final String COLUMN_PRIVATE = "private";
        public static final String COLUMN_HTML_URL = "html_url";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_STARGAZERS_COUNT = "stargazers_count";
        public static final String COLUMN_FORK = "fork";
        public static final String COLUMN_CONTENTS_URL = "contents_url";
        public static final String COLUMN_COMMITS_URL = "commits_url";
        public static final String COLUMN_BRANCHES_URL = "branches_url";
        public static final String COLUMN_COLLABORATORS_URL = "collaborators_url";
        public static final String COLUMN_STARGAZERS_URL = "stargazers_url";
        public static final String COLUMN_SUBSCRIBERS_URL = "subscribers_url";
        public static final String COLUMN_COMMENTS_URL = "comments_url";
        public static final String COLUMN_RELEASES_URL = "releases_url";
        public static final String COLUMN_FORKS_URL = "releases_url";



    }

}
