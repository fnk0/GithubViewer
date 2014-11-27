package com.gabilheri.githubviewer.network;

import android.content.Context;

import com.gabilheri.githubviewer.data.Owner;
import com.gabilheri.githubviewer.data.UserToken;
import com.gabilheri.githubviewer.data.feed.Feed;
import com.gabilheri.githubviewer.data.repo.Repo;
import com.gabilheri.githubviewer.data.repo.RepoContent;

import java.util.ArrayList;
import java.util.List;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

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
        UserToken getUserToken(@Body LoginRequest body);
    }

    public interface GithubOwner {
        @GET("/user")
        Owner getOwner();
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

    public interface GithubFeed {
        @GET("/users/{owner}/received_events")
        ArrayList<Feed> getFeed(
            @Path("owner") String owner
        );
    }

    public interface GithubRepoContent {
        @GET("/{url}")
        ArrayList<RepoContent> getRepoContent(
           @Path(value = "url", encode = false) String url

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
}
