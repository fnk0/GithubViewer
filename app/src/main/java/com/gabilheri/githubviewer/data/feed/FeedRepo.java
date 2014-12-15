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
@Table(name = "feed_repo_table")
public class FeedRepo extends OrmObject {

    @OrmField(name = "name")
    @SerializedName("name")
    private String name;

    @OrmField(name = "url")
    @SerializedName("url")
    private String url;

    public FeedRepo() {
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "FeedRepo{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
