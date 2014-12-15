package com.gabilheri.githubviewer.data.repo;

import com.gabilheri.githubviewer.data.Owner;
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

@Table(name = "repo_table")
public class Repo extends OrmObject {

    @OrmField(name = "name")
    @SerializedName("name")
    private String name;

    @OrmField(name = "full_name")
    @SerializedName("full_name")
    private String fullName;

    @OrmField(name = "owner", foreignKey = true, referenceTable = "owner_table")
    @SerializedName("owner")
    private Owner owner;

    @OrmField(name = "url")
    @SerializedName("url")
    private String url;

    @OrmField(name = "description")
    @SerializedName("description")
    private String description;

    @OrmField(name = "stargazers_count")
    @SerializedName("stargazers_count")
    private long stargazersCount;

    @OrmField(name = "fork")
    @SerializedName("fork")
    private boolean isForked;

    @OrmField(name = "private")
    @SerializedName("private")
    private boolean isPrivate;

    @OrmField(name = "contents_url")
    @SerializedName("contents_url")
    private String contentsUrl;

    @OrmField(name = "commits_url")
    @SerializedName("commits_url")
    private String commitsUrl;

    @OrmField(name = "branches_url")
    @SerializedName("branches_url")
    private String branchesUrl;

    @OrmField(name = "collaborators_url")
    @SerializedName("collaborators_url")
    private String collaboratorsUrl;

    @OrmField(name = "stargazers_url")
    @SerializedName("stargazers_url")
    private String stargazersUrl;

    @OrmField(name = "subscribers_url")
    @SerializedName("subscribers_url")
    private String subscribersUrl;

    @OrmField(name = "comments_url")
    @SerializedName("comments_url")
    private String commentsUrl;

    @OrmField(name = "releases_url")
    @SerializedName("releases_url")
    private String releasesUrl;

    @OrmField(name = "forks_url")
    @SerializedName("forks_url")
    private String forksUrl;

    @OrmField(name = "issue_events_url")
    @SerializedName("issue_events_url")
    private String issueEventsUrl;

    @OrmField(name = "created_at")
    @SerializedName("created_at")
    private Date createdAt;

    @OrmField(name = "updated_at")
    @SerializedName("updated_at")
    private Date updatedAt;

    @OrmField(name = "pushed_at")
    @SerializedName("pushed_at")
    private Date pushedAt;

    @OrmField(name = "git_url")
    @SerializedName("git_url")
    private String gitUrl;

    @OrmField(name = "watchers_count")
    @SerializedName("watchers_count")
    private long watchersCount;

    @OrmField(name = "language")
    @SerializedName("language")
    private String language;

    public Repo() {
    }

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

    public boolean isPrivate() {
        return isPrivate;
    }

    public String getContentsUrl() {
        return contentsUrl;
    }

    public String getCommitsUrl() {
        return commitsUrl;
    }

    public String getBranchesUrl() {
        return branchesUrl;
    }

    public String getCollaboratorsUrl() {
        return collaboratorsUrl;
    }

    public String getStargazersUrl() {
        return stargazersUrl;
    }

    public String getSubscribersUrl() {
        return subscribersUrl;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public String getReleasesUrl() {
        return releasesUrl;
    }

    public String getForksUrl() {
        return forksUrl;
    }

    public String getIssueEventsUrl() {
        return issueEventsUrl;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Date getPushedAt() {
        return pushedAt;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public long getWatchersCount() {
        return watchersCount;
    }

    public String getLanguage() {
        return language;
    }

    public String getFullName() {
        return fullName;
    }

    public Owner getOwner() {
        return owner;
    }

    public String getUrl() {
        return url;
    }

}
