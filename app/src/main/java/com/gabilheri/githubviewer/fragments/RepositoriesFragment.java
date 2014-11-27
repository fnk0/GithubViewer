package com.gabilheri.githubviewer.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gabilheri.githubviewer.R;
import com.gabilheri.githubviewer.base.DefaultFragment;
import com.gabilheri.githubviewer.cards.CardRepo;
import com.gabilheri.githubviewer.data.repo.Repo;
import com.gabilheri.githubviewer.network.GithubClient;
import com.gabilheri.githubviewer.network.TokenInterceptor;
import com.gabilheri.githubviewer.utils.PreferenceUtils;

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
public class RepositoriesFragment extends DefaultFragment {

    private List<Repo> repos;
    private CardRecyclerView reposList;

    private static String LOG_TAG = RepositoriesFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        reposList = (CardRecyclerView) view.findViewById(R.id.recycler_list);
        reposList.setHasFixedSize(false);
        reposList.setLayoutManager(new LinearLayoutManager(getActivity()));

        repos = new ArrayList<Repo>();

        new GetRepos().execute();

    }

    private class GetRepos extends AsyncTask<String, Void, List<Repo>> {

        @Override
        protected List<Repo> doInBackground(String... params) {

            TokenInterceptor interceptor = new TokenInterceptor(getActivity());

            RestAdapter restAdapter = GithubClient.getBaseRestAdapter(interceptor, getActivity());

            GithubClient.GithubRepos ghRepos = restAdapter.create(GithubClient.GithubRepos.class);

            //Log.i(LOG_TAG, "Owner: " + PreferenceUtils.getStringPreference(getActivity(), "owner", ""));

            return ghRepos.getRepos(PreferenceUtils.getStringPreference(getActivity(), "owner", ""));
        }

        @Override
        protected void onPostExecute(List<Repo> r) {
            super.onPostExecute(repos);
            repos = r;

            //Field[] rF = r.get(0).getClass().getFields();

            ArrayList<Card> repoCards = new ArrayList<>();

            for(Repo rp : repos) {
                repoCards.add(new CardRepo(getActivity(), rp));
            }

            CardArrayRecyclerViewAdapter mCardArrayAdapter = new CardArrayRecyclerViewAdapter(getActivity(), repoCards);
            reposList.setAdapter(mCardArrayAdapter);
        }
    }
}
