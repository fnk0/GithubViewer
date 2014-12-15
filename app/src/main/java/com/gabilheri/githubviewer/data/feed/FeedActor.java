package com.gabilheri.githubviewer.data.feed;

import com.gabilheri.simpleorm.annotations.OrmField;
import com.gabilheri.simpleorm.annotations.Table;
import com.gabilheri.simpleorm.builders.OrmObject;
import com.google.gson.annotations.SerializedName;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/23/14.
 */

@Table(name = "feed_actor_table")
public class FeedActor extends OrmObject {

    @OrmField(name = "login")
    @SerializedName("login")
    private String login;

    @OrmField(name = "avatar_url")
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
