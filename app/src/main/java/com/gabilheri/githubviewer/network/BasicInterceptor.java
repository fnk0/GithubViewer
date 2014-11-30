package com.gabilheri.githubviewer.network;

import android.content.Context;

import com.squareup.okhttp.Credentials;

import retrofit.RequestInterceptor;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/23/14.
 */
public class BasicInterceptor implements RequestInterceptor {

    private static final String LOG_TAG = BasicInterceptor.class.getCanonicalName();

    private String encodedCredentials;
    private Context context;

    public BasicInterceptor(String username, String password, Context context) {
        this.encodedCredentials = Credentials.basic(username, password);
        this.context = context;
    }

    public BasicInterceptor(String encodedCredentials, Context context) {
        this.encodedCredentials = encodedCredentials;
        this.context = context;
    }

    @Override
    public void intercept(RequestFacade request) {


        //Log.i(LOG_TAG, "Credentials: " + username + ":" + password + ", Encoded: " + encodedCredentials);

        request.addHeader("User-Agent", "GithubViewer");
        request.addHeader("Accept", "application/vnd.github.v3+json");
        request.addHeader("Authorization", encodedCredentials);
    }
}
