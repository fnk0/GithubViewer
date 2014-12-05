package com.gabilheri.githubviewer.data.feed;

import com.google.gson.annotations.SerializedName;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 12/2/14.
 */
public class UserEvent {

    @SerializedName("type")
    private String type;

    @SerializedName("actor")
    private FeedActor actor;

    @SerializedName("repo")
    private FeedRepo repo;

    @SerializedName("payload")
    private Payload payload;

    public UserEvent() {
    }

    public String getType() {
        return type;
    }

    public FeedActor getActor() {
        return actor;
    }

    public FeedRepo getRepo() {
        return repo;
    }

    public Payload getPayload() {
        return payload;
    }
}
