package com.gabilheri.githubviewer.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gabilheri.githubviewer.R;
import com.gabilheri.githubviewer.adapters.RepoRecyclerViewAdapter;
import com.gabilheri.githubviewer.base.DefaultFragment;
import com.gabilheri.githubviewer.data.Repo;
import com.gabilheri.githubviewer.network.GithubClient;
import com.gabilheri.githubviewer.network.TokenInterceptor;
import com.gabilheri.githubviewer.utils.PreferenceUtils;

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
public class RepositoriesFragment extends DefaultFragment {

    private List<Repo> repos;
    private RecyclerView reposList;

    private static String LOG_TAG = RepositoriesFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        reposList = (RecyclerView) view.findViewById(R.id.recycler_list);
        reposList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        reposList.setLayoutManager(llm);

        repos = new ArrayList<Repo>();

        new GetRepos().execute();

    }

    private class GetRepos extends AsyncTask<String, Void, List<Repo>> {

        @Override
        protected List<Repo> doInBackground(String... params) {

            TokenInterceptor interceptor = new TokenInterceptor(getActivity());

            RestAdapter restAdapter = GithubClient.getBaseRestAdapter(interceptor);

            GithubClient.GithubRepos ghRepos = restAdapter.create(GithubClient.GithubRepos.class);

            //Log.i(LOG_TAG, "Owner: " + PreferenceUtils.getStringPreference(getActivity(), "owner", ""));

            return ghRepos.getRepos(PreferenceUtils.getStringPreference(getActivity(), "owner", ""));
        }

        @Override
        protected void onPostExecute(List<Repo> r) {
            super.onPostExecute(repos);
            repos = r;
            RepoRecyclerViewAdapter adapter = new RepoRecyclerViewAdapter(getActivity(), repos);
            reposList.setAdapter(adapter);
        }
    }
}
