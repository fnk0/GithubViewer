package com.gabilheri.githubviewer.data;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/22/14.
 */
public class Owner extends SugarRecord<Owner> {

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
    private String followers;

    @SerializedName("following")
    private String following;


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

    public String getFollowers() {
        return followers;
    }

    public String getFollowing() {
        return following;
    }
}
