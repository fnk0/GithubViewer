package com.gabilheri.githubviewer.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gabilheri.githubviewer.R;
import com.gabilheri.githubviewer.base.DefaultFragment;
import com.gabilheri.githubviewer.cards.CardNewsFeed;
import com.gabilheri.githubviewer.data.GithubDbHelper;
import com.gabilheri.githubviewer.data.feed.Feed;
import com.gabilheri.githubviewer.network.GithubClient;
import com.gabilheri.githubviewer.network.TokenInterceptor;
import com.gabilheri.githubviewer.utils.NetworkUtils;
import com.gabilheri.githubviewer.utils.PreferenceUtils;
import com.gabilheri.simpleorm.utils.QueryUtils;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.recyclerview.internal.CardArrayRecyclerViewAdapter;
import it.gmariotti.cardslib.library.recyclerview.view.CardRecyclerView;
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
    private CardRecyclerView feedsList;
    private GithubDbHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dbHelper = new GithubDbHelper(getActivity());
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        feedsList = (CardRecyclerView) view.findViewById(R.id.recycler_list);
        feedsList.setHasFixedSize(false);
        feedsList.setLayoutManager(new LinearLayoutManager(getActivity()));

        feeds = new ArrayList<>();

        if(NetworkUtils.isNetworkAvailable(getActivity())) {
            new GetFeed().execute();
        } else {
            Log.d(LOG_TAG, "Loading from the Database....");
            List<Feed> feeds = QueryUtils.getAll(Feed.class, dbHelper.getWritableDatabase(), getActivity());
            setAdapterFromList(feeds);
        }

    }

    public void setAdapterFromList(List<Feed> feeds) {
        ArrayList<Card> feedCards = new ArrayList<>();
        for(Feed fi : feeds) {
            feedCards.add(new CardNewsFeed(getActivity(), fi));
        }

        CardArrayRecyclerViewAdapter adapter = new CardArrayRecyclerViewAdapter(getActivity(), feedCards);
        feedsList.setAdapter(adapter);
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

            dbHelper.deleteAllEntriesForClass(Feed.class);


            dbHelper.saveAll(Feed.class, feeds);

            setAdapterFromList(feeds);
        }
    }
}
