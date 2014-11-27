package com.gabilheri.githubviewer.data.contracts;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/25/14.
 */
public class RepoEntry implements BaseColumns {
    public static final Uri CONTENT_URI =
            DataContract.BASE_CONTENT_URI.buildUpon().appendPath(DataContract.PATH_REPOS).build();

    public static final String CONTENT_TYPE =
            "vnd.android.cursor.dir/" + DataContract.CONTENT_AUTHORITY + "/" + DataContract.PATH_REPOS;
    public static final String CONTENT_ITEM_TYPE =
            "vnd.android.cursor.item/" + DataContract.CONTENT_AUTHORITY + "/" + DataContract.PATH_REPOS;

    public static final String TABLE_NAME = "repos";

    public static final String COLUMN_REPO_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_FULL_NAME = "full_name";
    public static final String COLUMN_OWNER = "owner";
    public static final String COLUMN_PRIVATE = "private";
    public static final String COLUMN_HTML_URL = "html_url";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_STARGAZERS_COUNT = "stargazers_count";
    public static final String COLUMN_FORKS_COUNT = "forks_count";
    public static final String COLUMN_WATCHERS_COUNT = "watchers_count";
    public static final String COLUMN_SIZE = "size";
    public static final String COLUMN_DEFAULT_BRANCH = "default_branch";
    public static final String COLUMN_HAS_ISSUES = "has_issues";
    public static final String COLUMN_HAS_WIKI = "has_wiki";
    public static final String COLUMN_HAS_PAGES = "has_pages";
    public static final String COLUMN_HAS_DOWNLOADS = "has_downloads";
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
    public static final String COLUMN_CLONE_URL = "clone_url";
    public static final String COLUMN_GIT_URL = "git_url";
    public static final String COLUMN_SSH_URL = "ssh_url";
    public static final String COLUMN_SVN_URL = "svn_url";
    public static final String COLUMN_MIRROR_URL = "mirror_url";
    public static final String COLUMN_LANGUAGE = "language";
    public static final String COLUMN_PUSHET_AT = "pushed_at";
    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_UPDATED_AT = "updated_at";
    public static final String COLUMN_PERMISSIONS = "permissions";

    public static Uri buildRepoUri(long id) {
        return ContentUris.withAppendedId(CONTENT_URI, id);
    }


}
