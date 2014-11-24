package com.gabilheri.githubviewer.network;

import android.content.Context;

import com.gabilheri.githubviewer.utils.PreferenceUtils;

import retrofit.RequestInterceptor;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/23/14.
 */
public class TokenInterceptor implements RequestInterceptor {

    private Context context;

    public TokenInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public void intercept(RequestFacade request) {
        request.addHeader("User-Agent", "GithubViewer");
        request.addHeader("Accept", "application/vnd.github.v3+json");
        request.addHeader("Authorization", "Token " + PreferenceUtils.getStringPreference(context, "token", ""));
    }
}
