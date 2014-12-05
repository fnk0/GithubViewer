package com.gabilheri.githubviewer.data.repo;

import com.gabilheri.githubviewer.utils.CustomUtils;
import com.google.gson.annotations.SerializedName;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/24/14.
 */

public class RepoContent {

    @SerializedName("name")
    private String name;

    @SerializedName("path")
    private String path;

    @SerializedName("type")
    private String type;

    @SerializedName("url")
    private String url;

    @SerializedName("size")
    private long size;

    @SerializedName("sha")
    private String sha;

    @SerializedName("git_url")
    private String gitUrl;

    @SerializedName("content")
    private String content;

    public RepoContent() {
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public long getSize() {
        return size;
    }

    public String getSha() {
        return sha;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public String getContent() {
        return CustomUtils.getBase64string(content);
    }
}
