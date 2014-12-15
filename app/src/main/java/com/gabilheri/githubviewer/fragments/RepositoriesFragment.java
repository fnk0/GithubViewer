package com.gabilheri.githubviewer.fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gabilheri.githubviewer.R;
import com.gabilheri.githubviewer.base.DefaultFragment;
import com.gabilheri.githubviewer.cards.CardRepo;
import com.gabilheri.githubviewer.data.GithubDbHelper;
import com.gabilheri.githubviewer.data.repo.Repo;
import com.gabilheri.githubviewer.data.repo.RepoContent;
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
public class RepositoriesFragment extends DefaultFragment {

    private List<Repo> repos;
    private CardRecyclerView reposList;
    private GithubDbHelper dbHelper;
    private ProgressDialog pd;

    private static String LOG_TAG = RepositoriesFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        dbHelper = new GithubDbHelper(getActivity());
        reposList = (CardRecyclerView) view.findViewById(R.id.recycler_list);
        reposList.setHasFixedSize(false);
        reposList.setLayoutManager(new LinearLayoutManager(getActivity()));

        repos = new ArrayList<Repo>();

        if(NetworkUtils.isNetworkAvailable(getActivity())) {
            new GetRepos().execute();
        } else {
            repos = QueryUtils.getAll(Repo.class, dbHelper.getWritableDatabase(), getActivity());
            setAdapterFromList(repos);
        }
    }

    private void setAdapterFromList(List<Repo> repos) {
        ArrayList<Card> repoCards = new ArrayList<>();

        for(Repo rp : repos) {
            repoCards.add(new CardRepo(getActivity(), rp));
        }

        CardArrayRecyclerViewAdapter mCardArrayAdapter = new CardArrayRecyclerViewAdapter(getActivity(), repoCards);
        reposList.setAdapter(mCardArrayAdapter);
    }

    private class GetRepos extends AsyncTask<String, Void, List<Repo>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(getActivity(), R.style.FrameworkRoot_Theme);
            pd.setIndeterminate(true);
            pd.setCancelable(false);
            pd.setTitle("Loading repositories...");
            pd.show();
        }

        @Override
        protected List<Repo> doInBackground(String... params) {

            TokenInterceptor interceptor = new TokenInterceptor(getActivity());

            RestAdapter restAdapter = GithubClient.getBaseRestAdapter(interceptor, getActivity());

            GithubClient.GithubOwnerRepos ghRepos = restAdapter.create(GithubClient.GithubOwnerRepos.class);

            //Log.i(LOG_TAG, "Owner: " + PreferenceUtils.getStringPreference(getActivity(), "owner", ""));

            return ghRepos.getRepos();
        }

        @Override
        protected void onPostExecute(List<Repo> r) {
            super.onPostExecute(repos);

            if(pd.isShowing()) {
                pd.dismiss();
            }

            dbHelper.deleteAllEntriesForClass(Repo.class);
            repos = r;
            dbHelper.saveAll(Repo.class, repos);
            setAdapterFromList(repos);
        }
    }

    private class GetAllReposContent extends AsyncTask<String, Void, List<RepoContent>> {

        @Override
        protected List<RepoContent> doInBackground(String... params) {

            for(Repo repo : repos) {
                String url = "repos/" + PreferenceUtils.getStringPreference(getActivity(), "owner", "") +
                                "/" + repo.getName() + "/contents";
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<RepoContent> repoContents) {
            super.onPostExecute(repoContents);
        }
    }
}
