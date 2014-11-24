package com.gabilheri.githubviewer.network;

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

    private String username, password;

    public BasicInterceptor(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void intercept(RequestFacade request) {
        String encodedCredentials = Credentials.basic(username, password);

        //Log.i(LOG_TAG, "Credentials: " + username + ":" + password + ", Encoded: " + encodedCredentials);

        request.addHeader("User-Agent", "GithubViewer");
        request.addHeader("Accept", "application/vnd.github.v3+json");
        request.addHeader("Authorization", encodedCredentials);
    }
}
