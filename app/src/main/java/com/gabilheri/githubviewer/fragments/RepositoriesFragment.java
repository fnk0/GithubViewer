package com.gabilheri.githubviewer.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.gabilheri.githubviewer.base.DefaultListFragment;
import com.gabilheri.githubviewer.cards.CardRepo;
import com.gabilheri.githubviewer.data.repo.Repo;
import com.gabilheri.githubviewer.network.GithubClient;
import com.gabilheri.githubviewer.network.TokenInterceptor;
import com.gabilheri.githubviewer.utils.NetworkUtils;
import com.gabilheri.simpleorm.utils.QueryUtils;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/16/14.
 */
public class RepositoriesFragment extends DefaultListFragment {

    private List<Repo> repos;
    private static String LOG_TAG = RepositoriesFragment.class.getSimpleName();
    private boolean starredRepos = false;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        repos = new ArrayList<>();

        if(getArguments() != null) {
            starredRepos = getArguments().getBoolean(Repo.STARRED_COL);
        }

        if(starredRepos) {
            getActivity().setTitle("Starred Repos");
            repos = QueryUtils.findObjects(Repo.class, getDbHelper().getWritableDatabase(), getActivity(), Repo.STARRED_COL, 1L);
            Log.d(LOG_TAG, "Starred size: " + repos.size());
            setAdapter();
        } else {
            getActivity().setTitle("Repositories");
            repos = QueryUtils.getAll(Repo.class, getDbHelper().getWritableDatabase(), getActivity());
            setAdapter();

            if(NetworkUtils.isNetworkAvailable(getActivity())) {
                new GetRepos().execute();
            }
        }
    }

    @Override
    public List<Card> getCardsList() {
        ArrayList<Card> repoCards = new ArrayList<>();

        for(Repo rp : repos) {
            repoCards.add(new CardRepo(getActivity(), rp));
        }
        return repoCards;
    }

    private class GetRepos extends AsyncTask<String, Void, List<Repo>> {

        @Override
        protected List<Repo> doInBackground(String... params) {

            TokenInterceptor interceptor = new TokenInterceptor(getActivity());

            RestAdapter restAdapter = GithubClient.getBaseRestAdapter(interceptor, getActivity());

            try {
                GithubClient.GithubOwnerRepos ghRepos = restAdapter.create(GithubClient.GithubOwnerRepos.class);
                return ghRepos.getRepos();
            } catch (RetrofitError error) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Repo> r) {
            if(r != null) {
                getDbHelper().deleteAllEntriesForClass(Repo.class);
                repos = r;
                getDbHelper().saveAll(Repo.class, repos);
                setAdapter();
                refreshList().run();
            }

        }
    }
}
