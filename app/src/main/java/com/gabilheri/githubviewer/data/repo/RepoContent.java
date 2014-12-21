package com.gabilheri.githubviewer.data.repo;

import com.gabilheri.githubviewer.utils.CustomUtils;
import com.gabilheri.simpleorm.annotations.OrmField;
import com.gabilheri.simpleorm.annotations.Table;
import com.gabilheri.simpleorm.builders.OrmObject;
import com.google.gson.annotations.SerializedName;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/24/14.
 */

@Table(name = "repo_content")
public class RepoContent extends OrmObject {

    @OrmField(name = "name")
    @SerializedName("name")
    private String name;

    @OrmField(name = "path")
    @SerializedName("path")
    private String path;

    @OrmField(name = "type")
    @SerializedName("type")
    private String type;

    @OrmField(name = "url")
    @SerializedName("url")
    private String url;

    @OrmField(name = "size")
    @SerializedName("size")
    private long size;

    @OrmField(name = "sha")
    @SerializedName("sha")
    private String sha;

    @OrmField(name = "git_url")
    @SerializedName("git_url")
    private String gitUrl;

    @OrmField(name = "content")
    @SerializedName("content")
    private String content;

    @OrmField(name = "db_url")
    private String dbUrl;

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

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }
}
