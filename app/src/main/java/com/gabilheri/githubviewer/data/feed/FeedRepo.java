package com.gabilheri.githubviewer.data.feed;

import com.google.gson.annotations.SerializedName;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/23/14.
 */

public class FeedRepo {

    @SerializedName("name")
    private String name;

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
