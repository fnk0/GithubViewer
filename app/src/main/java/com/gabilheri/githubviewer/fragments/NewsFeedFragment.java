package com.gabilheri.githubviewer.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gabilheri.githubviewer.R;
import com.gabilheri.githubviewer.adapters.NewsFeedRecyclerView;
import com.gabilheri.githubviewer.base.DefaultFragment;
import com.gabilheri.githubviewer.data.feed.Feed;
import com.gabilheri.githubviewer.network.GithubClient;
import com.gabilheri.githubviewer.network.TokenInterceptor;
import com.gabilheri.githubviewer.utils.PreferenceUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit.RestAdapter;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/16/14.
 */
public class NewsFeedFragment extends DefaultFragment {

    private static final String LOG_TAG = NewsFeedFragment.class.getSimpleName();
    private List<Feed> feeds;
    private RecyclerView feedsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        feedsList = (RecyclerView) view.findViewById(R.id.recycler_list);
        feedsList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        feedsList.setLayoutManager(llm);

        feeds = new ArrayList<>();
        new GetFeed().execute();
    }

    private class GetFeed extends AsyncTask<String, Void, List<Feed>> {

        @Override
        protected List<Feed> doInBackground(String... params) {
            TokenInterceptor interceptor = new TokenInterceptor(getActivity());

            RestAdapter restAdapter = GithubClient.getBaseRestAdapter(interceptor, getActivity());

            GithubClient.GithubFeed ghFeed = restAdapter.create(GithubClient.GithubFeed.class);

            return ghFeed.getFeed(PreferenceUtils.getStringPreference(getActivity(), "owner", ""));
        }

        @Override
        protected void onPostExecute(List<Feed> f) {
            //super.onPostExecute(feeds);
            feeds = f;
            Feed.deleteAll(Feed.class);
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            for(Feed fs : feeds) {
                String fiJson = gson.toJson(fs);
                //Log.i(LOG_TAG, "Json: " + fiJson);
                fs = gson.fromJson(fiJson, Feed.class);
                fs.save();
                //Log.i(LOG_TAG, fs.toString());
            }

            List<Feed> tFeed = Feed.listAll(Feed.class);

            Log.i(LOG_TAG, "Size After Inserting: " + tFeed.size());

            NewsFeedRecyclerView adapter = new NewsFeedRecyclerView(feeds, getActivity());
            feedsList.setAdapter(adapter);
        }
    }
}
