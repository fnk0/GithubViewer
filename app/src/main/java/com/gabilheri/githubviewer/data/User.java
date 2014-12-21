package com.gabilheri.githubviewer.data;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.gabilheri.githubviewer.base.CommonColumns;
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
@Table(name = "users_table")
public class User extends OrmObject {

    public static final String COMPANY = "company";
    public static final String BLOG = "blog";
    public static final String FOLLOWERS = "followers";
    public static final String FOLLOWING = "following";
    public static final String STARRED_COUNT = "starred_count";
    public static final String BIO = "bio";

    public static final int COL_NAME = 0;
    public static final int COL_LOGIN = 1;
    public static final int COL_AVATAR_URL = 2;
    public static final int COL_EMAIL = 3;
    public static final int COL_LOCATION = 4;
    public static final int COL_CREATED_AT = 5;
    public static final int COL_UPDATED_AT = 6;
    public static final int COL_FOLLOWERS = 7;
    public static final int COL_FOLLOWING = 8;
    public static final int COL_STARRED_COUNT = 9;
    public static final int COL_BLOG = 10;
    public static final int COL_COMPANY = 11;
    public static final int COL_BIO = 12;

    public static final String[] PROVIDER_COLUMNS = {
                CommonColumns.NAME,
                CommonColumns.LOGIN,
                CommonColumns.AVATAR_URL,
                CommonColumns.EMAIL,
                CommonColumns.LOCATION,
                CommonColumns.CREATED_AT,
                CommonColumns.UPDATED_AT,
                FOLLOWERS,
                FOLLOWING,
                STARRED_COUNT,
                BLOG,
                COMPANY,
                BIO
    };

    @OrmField(name = CommonColumns.LOGIN)
    @SerializedName(CommonColumns.LOGIN)
    private String login;

    @OrmField(name = CommonColumns.AVATAR_URL)
    @SerializedName(CommonColumns.AVATAR_URL)
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

    @OrmField(name = CommonColumns.NAME)
    @SerializedName(CommonColumns.NAME)
    private String name;

    @OrmField(name = CommonColumns.EMAIL)
    @SerializedName(CommonColumns.EMAIL)
    private String email;

    @OrmField(name = BLOG)
    @SerializedName(BLOG)
    private String blog;

    @OrmField(name = CommonColumns.LOCATION)
    @SerializedName(CommonColumns.LOCATION)
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

    @OrmField(name = FOLLOWERS)
    @SerializedName(FOLLOWERS)
    private Integer followers;

    @OrmField(name = FOLLOWING)
    @SerializedName(FOLLOWING)
    private Integer following;

    @OrmField(name = BIO)
    @SerializedName(BIO)
    private String bio;

    @OrmField(name = STARRED_COUNT)
    @SerializedName(STARRED_COUNT)
    private Integer starredCount;

    @OrmField(name = CommonColumns.CREATED_AT)
    @SerializedName(CommonColumns.CREATED_AT)
    private Date createdAt;

    @OrmField(name = CommonColumns.UPDATED_AT)
    @SerializedName(CommonColumns.UPDATED_AT)
    private Date updatedAt;

    @OrmField(name = COMPANY)
    @SerializedName(COMPANY)
    private String company;

    @OrmField(name = "is_following")
    private Integer isFollowing;

    @OrmField(name = "is_follower")
    private Integer isFollower;

    public User() {
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

    public Integer getFollowers() {
        return followers;
    }

    public Integer getFollowing() {
        return following;
    }

    public String getBio() {
        return bio;
    }

    public Integer getStarredCount() {
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


    public Integer isFollowing() {
        if(isFollowing == null) return 0;
        return isFollowing;
    }

    public void setIsFollowing(Integer isFollowing) {
        this.isFollowing = isFollowing;
    }

    public Integer isFollower() {
        if(isFollower == null) return 0;
        return isFollower;
    }

    public void setIsFollower(Integer isFollower) {
        this.isFollower = isFollower;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public void setFollowersUrl(String followersUrl) {
        this.followersUrl = followersUrl;
    }

    public void setFollowingUrl(String followingUrl) {
        this.followingUrl = followingUrl;
    }

    public void setGistsUrl(String gistsUrl) {
        this.gistsUrl = gistsUrl;
    }

    public void setStarredUrl(String starredUrl) {
        this.starredUrl = starredUrl;
    }

    public void setSubscriptionsUrl(String subscriptionsUrl) {
        this.subscriptionsUrl = subscriptionsUrl;
    }

    public void setReposUrl(String reposUrl) {
        this.reposUrl = reposUrl;
    }

    public void setOrganizationsUrl(String organizationsUrl) {
        this.organizationsUrl = organizationsUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPublicRepos(String publicRepos) {
        this.publicRepos = publicRepos;
    }

    public void setPublicGists(String publicGists) {
        this.publicGists = publicGists;
    }

    public void setPrivateGists(String privateGists) {
        this.privateGists = privateGists;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public void setFollowing(Integer following) {
        this.following = following;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setStarredCount(Integer starredCount) {
        this.starredCount = starredCount;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public static final class OwnerEntry implements BaseColumns  {

        public static final String CONTENT_AUTHORITY = "com.gabilheri.githubviewer.app";

        public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

        public static final String PATH_OWNER = "owner";

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_OWNER).build();

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_OWNER;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_OWNER;

        public static final String TABLE_NAME = User.class.getAnnotation(Table.class).name();

        public static String getTableName() {
            Table t = User.class.getAnnotation(Table.class);
            return t.name();
        }

        public static Uri buildOwner(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }


        public static Uri buildOwner(String login) {
            return CONTENT_URI.buildUpon().appendQueryParameter("login", login).build();
        }

        public static String getLoginFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }
    }

}
