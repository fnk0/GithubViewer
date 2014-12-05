package com.gabilheri.githubviewer.data.feed;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/23/14.
 */

@DatabaseTable(tableName = "feedActor")
public class FeedActor {

    @DatabaseField(generatedId = true)
    int id;

    @DatabaseField
    @SerializedName("login")
    private String login;

    @DatabaseField
    @SerializedName("avatar_url")
    private String avatarUrl;

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public FeedActor() {
    }

    @Override
    public String toString() {
        return "FeedActor{" +
                "login='" + login + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
