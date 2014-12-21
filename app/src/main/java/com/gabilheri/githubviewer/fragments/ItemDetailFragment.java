package com.gabilheri.githubviewer.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.gabilheri.githubviewer.R;
import com.gabilheri.githubviewer.base.DefaultFragment;
import com.gabilheri.githubviewer.data.GithubDbHelper;
import com.gabilheri.githubviewer.data.repo.RepoContent;
import com.gabilheri.githubviewer.data.repo.RepoDetail;
import com.gabilheri.githubviewer.network.GithubClient;
import com.gabilheri.githubviewer.network.TokenInterceptor;
import com.gabilheri.githubviewer.utils.FileUtils;
import com.gabilheri.githubviewer.utils.NetworkUtils;
import com.gabilheri.simpleorm.utils.QueryUtils;

import retrofit.RestAdapter;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/27/14.
 */
public class ItemDetailFragment extends DefaultFragment {

    private WebView content;
    private GithubDbHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dbHelper = new GithubDbHelper(getActivity());
        return inflater.inflate(R.layout.file_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        content = (WebView) view.findViewById(R.id.content);

        String url = getArguments().getString("url");

        if(NetworkUtils.isNetworkAvailable(getActivity())) {
            new GetRepoContent(getActivity()).execute(url);
        } else {
            RepoDetail detail = QueryUtils.findObject(RepoDetail.class,
                    dbHelper.getWritableDatabase(),
                    getActivity(),
                    "url",
                    url);
            loadWebViewContent(detail.getHtmlString());
        }
    }

    public void loadWebViewContent(String htmlString) {
        if(htmlString != null) {
            content.getSettings().setJavaScriptEnabled(true);
            content.setWebChromeClient(new WebChromeClient());
            content.getSettings().setBuiltInZoomControls(true);
            content.getSettings().setSupportZoom(true);
            content.getSettings().setUseWideViewPort(true);
            //Log.i("HTML STRING: ", renderedString);
            content.loadDataWithBaseURL("file:///android_asset/", htmlString, "text/html", "utf-8", null);
        } else {
            content.loadDataWithBaseURL("file:///android_asset/", "<h1>404 not found! </h1>", "text/html", "utf-8", null);
        }
    }

    private class GetRepoContent extends AsyncTask<String, Void, RepoContent> {

        private Context context;
        private String url;
        private GetRepoContent(Context context) {
            this.context = context;
        }

        @Override
        protected RepoContent doInBackground(String... params) {

            TokenInterceptor interceptor = new TokenInterceptor(context);

            RestAdapter restAdapter = GithubClient.getBaseRestAdapter(interceptor, context);

            GithubClient.GithubRepoContent ghRepos = restAdapter.create(GithubClient.GithubRepoContent.class);

            //Log.i(LOG_TAG, "Owner: " + PreferenceUtils.getStringPreference(getActivity(), "owner", ""));
            this.url = params[0];
            Log.i("QUERY_REPO_CONTENT", "https://api.github.com/" + url);

            return ghRepos.getRepoContent(url);
        }

        @Override
        protected void onPostExecute(RepoContent r) {
            if(r != null) {
                dbHelper.delete(RepoDetail.class, "url", url);
                String renderedString = FileUtils.getHtmlString(r);
                loadWebViewContent(renderedString);
                RepoDetail detail = new RepoDetail();
                detail.setHtmlString(renderedString);
                detail.setUrl(url);
                dbHelper.save(RepoDetail.class, detail);
            }
        }
    }
}
