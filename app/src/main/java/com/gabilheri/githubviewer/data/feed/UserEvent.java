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
 * @since 12/2/14.
 */
@Table(name = "user_events_table")
public class UserEvent extends OrmObject {

    @OrmField(name = "type")
    @SerializedName("type")
    private String type;

    @OrmField(name = "actor", foreignKey = true, referenceTable = "feed_actor_table")
    @SerializedName("actor")
    private FeedActor actor;

    @OrmField(name = "repo", foreignKey = true, referenceTable = "repo_feed_table")
    @SerializedName("repo")
    private FeedRepo repo;

    @OrmField(name = "payload", foreignKey = true, referenceTable = "payloads_table")
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
