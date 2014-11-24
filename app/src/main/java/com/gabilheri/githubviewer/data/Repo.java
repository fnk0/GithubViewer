package com.gabilheri.githubviewer.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/23/14.
 */
public class Repo {

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("stargazers_count")
    private long stargazersCount;

    @SerializedName("fork")
    private boolean isForked;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getStargazersCount() {
        return stargazersCount;
    }

    public boolean isForked() {
        return isForked;
    }

    @Override
    public String toString() {
        return "Repo{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", stargazersCount=" + stargazersCount +
                ", isForked=" + isForked +
                '}';
    }
}
