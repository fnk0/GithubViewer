package com.gabilheri.githubviewer.network;

import android.content.Context;

import com.gabilheri.githubviewer.data.User;
import com.gabilheri.githubviewer.data.UserToken;
import com.gabilheri.githubviewer.data.feed.Feed;
import com.gabilheri.githubviewer.data.repo.Repo;
import com.gabilheri.githubviewer.data.repo.RepoContent;
import com.gabilheri.githubviewer.data.repo.SearchRepo;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/22/14.
 */

public class GithubClient {

    public static final String API_URL = "https://api.github.com";

    public static class Contributor {
        public String login;
        public int contributions;
    }

    public interface GithubAuth {
        @POST("/authorizations")
        UserToken getUserToken(
                @Body LoginRequest body
        );
    }

    public interface GithubOwner {
        @GET("/user")
        User getOwner();
    }

    public interface GitHubContributors {
        @GET("/repos/{owner}/{repo}/contributors")
        List<Contributor> contributors(
                @Path("owner") String owner,
                @Path("repo") String repo
        );
    }

    public interface GithubRepos {
        @GET("/users/{owner}/repos")
        ArrayList<Repo> getRepos(
            @Path("owner") String owner
        );
    }

    public interface GithubSearchRepos {
        @GET("/search/repositories?q={query}+language:{lang}")
        SearchRepo getSearchResult(
                @Path("query") String query,
                @Query("sort") String sort,
                @Query("order") String order
        );
    }

    public interface GithubOwnerRepos {
        @GET("/user/repos")
        ArrayList<Repo> getRepos();
    }

    public interface GithubFeed {
        @GET("/users/{owner}/received_events")
        ArrayList<Feed> getFeed(
            @Path("owner") String owner
        );
    }

    public interface GithubListRepoContent {
        @GET("/{url}")
        ArrayList<RepoContent> getRepoContent(
           @Path(value = "url", encode = false) String url

        );
    }

    public interface GithubSignOut {
        @DELETE("/authorizations/{id}")
        void signOut(
            @Path("id") String id, Callback<Object> callback
        );
    }

    public interface GithubRepoContent {
        @GET("/{url}")
        RepoContent getRepoContent(
                @Path(value = "url", encode = false) String url
        );
    }

    public interface GithubUserStarred {
        @GET("/user/starred?page=1&per_page=10000")
        List<Repo> getRepos();
    }

    public interface GithubFollowers {
        @GET("/users/{user}/followers")
        List<User> getFollowers(
            @Path("user") String user
        );
    }

    public interface GithubFollowing {
        @GET("/users/{user}/following")
        List<User> getFollowing(
                @Path("user") String user
        );
    }

    public static RestAdapter getBaseRestAdapter(RequestInterceptor interceptor, Context context) {

        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(API_URL);

        if(interceptor != null) {
            builder.setRequestInterceptor(interceptor);
        }

        return builder.build();
    }

    public static RestAdapter getBaseRestAdapterWithoutInterceptors() {
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(API_URL);
        return builder.build();
    }
}
