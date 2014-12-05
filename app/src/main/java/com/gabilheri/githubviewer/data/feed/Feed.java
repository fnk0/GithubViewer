package com.gabilheri.githubviewer.data.feed;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/23/14.
 */

@DatabaseTable(tableName = "feed")
public class Feed {

    @DatabaseField(generatedId = true)
    int id;

    @DatabaseField
    @SerializedName("type")
    private String type;

    @DatabaseField
    @SerializedName("actor")
    private FeedActor feedActor;

    @DatabaseField
    @SerializedName("repo")
    private FeedRepo feedRepo;

    @DatabaseField
    @SerializedName("created_at")
    private Date createdAt;

    @DatabaseField
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
