package com.gabilheri.githubviewer.data.repo;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

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

    @SerializedName("full_name")
    private String fullName;

    @SerializedName("description")
    private String description;

    @SerializedName("stargazers_count")
    private long stargazersCount;

    @SerializedName("fork")
    private boolean isForked;

    @SerializedName("private")
    private boolean isPrivate;

    @SerializedName("contents_url")
    private String contentsUrl;

    @SerializedName("commits_url")
    private String commitsUrl;

    @SerializedName("branches_url")
    private String branchesUrl;

    @SerializedName("collaborators_url")
    private String collaboratorsUrl;

    @SerializedName("stargazers_url")
    private String stargazersUrl;

    @SerializedName("subscribers_url")
    private String subscribersUrl;

    @SerializedName("comments_url")
    private String commentsUrl;

    @SerializedName("releases_url")
    private String releasesUrl;

    @SerializedName("forks_url")
    private String forksUrl;

    @SerializedName("issue_events_url")
    private String issueEventsUrl;

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("updated_at")
    private Date updatedAt;

    @SerializedName("pushed_at")
    private Date pushedAt;

    @SerializedName("git_url")
    private String gitUrl;

    @SerializedName("watchers_count")
    private long watchersCount;

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
}
