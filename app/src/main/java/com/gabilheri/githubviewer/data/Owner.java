package com.gabilheri.githubviewer.data;

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
 * @since 11/22/14.
 */
@Table(name = "owner_table")
public class Owner extends OrmObject {

    @OrmField(name = "login")
    @SerializedName("login")
    private String login;

    @OrmField(name = "avatar_url")
    @SerializedName("avatar_url")
    private String avatarUrl;

    @OrmField(name = "html_url")
    @SerializedName("html_url")
    private String htmlUrl;

    @OrmField(name = "followers_url")
    @SerializedName("followers_url")
    private String followersUrl;

    @OrmField(name = "following_url")
    @SerializedName("following_url")
    private String followingUrl;

    @OrmField(name = "gists_url")
    @SerializedName("gists_url")
    private String gistsUrl;

    @OrmField(name = "starred_url")
    @SerializedName("starred_url")
    private String starredUrl;

    @OrmField(name = "subscriptions_url")
    @SerializedName("subscriptions_url")
    private String subscriptionsUrl;

    @OrmField(name = "repos_url")
    @SerializedName("repos_url")
    private String reposUrl;

    @OrmField(name = "organizations_url")
    @SerializedName("organizations_url")
    private String organizationsUrl;

    @OrmField(name = "name")
    @SerializedName("name")
    private String name;

    @OrmField(name = "email")
    @SerializedName("email")
    private String email;

    @OrmField(name = "blog")
    @SerializedName("blog")
    private String blog;

    @OrmField(name = "location")
    @SerializedName("location")
    private String location;

    @OrmField(name = "public_repos")
    @SerializedName("public_repos")
    private String publicRepos;

    @OrmField(name = "public_gists")
    @SerializedName("public_gists")
    private String publicGists;

    @OrmField(name = "private_gists")
    @SerializedName("private_gists")
    private String privateGists;

    @OrmField(name = "followers")
    @SerializedName("followers")
    private int followers;

    @OrmField(name = "following")
    @SerializedName("following")
    private int following;

    @OrmField(name = "bio")
    @SerializedName("bio")
    private String bio;

    @OrmField(name = "starred_count")
    @SerializedName("starred_count")
    private int starredCount;

    @OrmField(name = "created_at")
    @SerializedName("created_at")
    private Date createdAt;

    @OrmField(name = "updated_at")
    @SerializedName("updated_at")
    private Date updatedAt;

    @OrmField(name = "company")
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
