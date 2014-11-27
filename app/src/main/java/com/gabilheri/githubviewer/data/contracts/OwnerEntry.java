package com.gabilheri.githubviewer.data.contracts;

import android.provider.BaseColumns;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/25/14.
 */
public class OwnerEntry implements BaseColumns {

    public static final String COLUMN_OWNER_ID = "id";
    public static final String COLUMN_OWNER_LOGIN = "login";
    public static final String COLUMN_OWNER_AVATAR_URL = "avatar_url";
    public static final String COLUMN_OWNER_GRAVATAR_ID = "gravatar_id";
    public static final String COLUMN_OWNER_URL = "url";
    public static final String COLUMN_OWNER_HTML_URL = "html_url";
    public static final String COLUMN_OWNER_FOLLOWERS_URL = "followers_url";
    public static final String COLUMN_OWNER_FOLLOWING_URL = "following_url";
    public static final String COLUMN_OWNER_GISTS_URL = "gists_url";
    public static final String COLUMN_OWNER_STARRED_URL = "starred_url";
    public static final String COLUMN_OWNER_SUBSCRIPTIONS_URL = "subscriptions_url";
    public static final String COLUMN_OWNER_ORGANIZATIONS_URL = "organizations_url";
    public static final String COLUMN_OWNER_REPOS_URL = "repos_url";
    public static final String COLUMN_OWNER_EVENTS_URL = "events_url";
    public static final String COLUMN_OWNER_RECEIVED_EVENTS_URL = "received_events_url";
    public static final String COLUMN_OWNER_TYPE = "type";
    public static final String COLUMN_OWNER_SITE_ADMIN = "site_admin";

}
