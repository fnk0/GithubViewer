package com.gabilheri.githubviewer.data;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/22/14.
 */
public class Owner  {

    @SerializedName("login")
    private String login;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("html_url")
    private String htmlUrl;

    @SerializedName("followers_url")
    private String followersUrl;

    @SerializedName("following_url")
    private String followingUrl;

    @SerializedName("gists_url")
    private String gistsUrl;

    @SerializedName("starred_url")
    private String starredUrl;

    @SerializedName("subscriptions_url")
    private String subscriptionsUrl;

    @SerializedName("repos_url")
    private String reposUrl;

    @SerializedName("organizations_url")
    private String organizationsUrl;

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("blog")
    private String blog;

    @SerializedName("location")
    private String location;

    @SerializedName("public_repos")
    private String publicRepos;

    @SerializedName("public_gists")
    private String publicGists;

    @SerializedName("private_gists")
    private String privateGists;

    @SerializedName("followers")
    private int followers;

    @SerializedName("following")
    private int following;

    @SerializedName("bio")
    private String bio;

    @SerializedName("starred_count")
    private int starredCount;

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("updated_at")
    private Date updatedAt;

    @SerializedName("company")
    private String company;

    public Owner() {
    }

    public String getReposUrl() {
        return reposUrl;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getFollowersUrl() {
        return followersUrl;
    }

    public String getFollowingUrl() {
        return followingUrl;
    }

    public String getGistsUrl() {
        return gistsUrl;
    }

    public String getStarredUrl() {
        return starredUrl;
    }

    public String getSubscriptionsUrl() {
        return subscriptionsUrl;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBlog() {
        return blog;
    }

    public String getLocation() {
        return location;
    }

    public String getPublicRepos() {
        return publicRepos;
    }

    public String getPublicGists() {
        return publicGists;
    }

    public String getPrivateGists() {
        return privateGists;
    }

    public String getOrganizationsUrl() {
        return organizationsUrl;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
    }

    public String getBio() {
        return bio;
    }

    public int getStarredCount() {
        return starredCount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getCompany() {
        return company;
    }

    public void setStarredCount(int starredCount) {
        this.starredCount = starredCount;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "login='" + login + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", htmlUrl='" + htmlUrl + '\'' +
                ", followersUrl='" + followersUrl + '\'' +
                ", followingUrl='" + followingUrl + '\'' +
                ", gistsUrl='" + gistsUrl + '\'' +
                ", starredUrl='" + starredUrl + '\'' +
                ", subscriptionsUrl='" + subscriptionsUrl + '\'' +
                ", reposUrl='" + reposUrl + '\'' +
                ", organizationsUrl='" + organizationsUrl + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", blog='" + blog + '\'' +
                ", location='" + location + '\'' +
                ", publicRepos='" + publicRepos + '\'' +
                ", publicGists='" + publicGists + '\'' +
                ", privateGists='" + privateGists + '\'' +
                ", followers=" + followers +
                ", following=" + following +
                ", bio='" + bio + '\'' +
                ", starredCount=" + starredCount +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", company='" + company + '\'' +
                '}';
    }
}
