package com.gabilheri.githubviewer.data.feed;

import com.gabilheri.simpleorm.annotations.OrmField;
import com.gabilheri.simpleorm.annotations.Table;
import com.gabilheri.simpleorm.builders.OrmObject;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/23/14.
 */

@Table(name = "feeds_table")
public class Feed extends OrmObject {

    @OrmField(name = "type")
    @SerializedName("type")
    private String type;

    @OrmField(name = "actor", foreignKey = true, referenceTable = "feed_actor_table")
    @SerializedName("actor")
    private FeedActor feedActor;

    @OrmField(name = "repo", foreignKey = true, referenceTable = "repo_feed_table")
    @SerializedName("repo")
    private FeedRepo feedRepo;

    @OrmField(name = "created_at")
    @SerializedName("created_at")
    private Date createdAt;

    @OrmField(name = "payload", foreignKey = true, referenceTable = "payloads_table")
    @SerializedName("payload")
    private Payload payload;

    public String getType() {
        return type;
    }

    public FeedActor getFeedActor() {
        return feedActor;
    }

    public FeedRepo getFeedRepo() {
        return feedRepo;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Payload getPayload() {
        return payload;
    }

    public Feed() {

    }

    @Override
    public String toString() {
        return "Feed{" +
                "type='" + type + '\'' +
                ", feedActor=" + feedActor +
                ", feedRepo=" + feedRepo +
                ", createdAt=" + createdAt +
                ", payload=" + payload +
                '}';
    }
}
