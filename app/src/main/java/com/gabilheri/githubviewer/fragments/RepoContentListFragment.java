package com.gabilheri.githubviewer.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.gabilheri.githubviewer.MainActivity;
import com.gabilheri.githubviewer.R;
import com.gabilheri.githubviewer.base.DefaultListFragment;
import com.gabilheri.githubviewer.cards.CardFileItem;
import com.gabilheri.githubviewer.data.repo.RepoContent;
import com.gabilheri.githubviewer.network.GithubClient;
import com.gabilheri.githubviewer.network.TokenInterceptor;
import com.gabilheri.githubviewer.utils.NetworkUtils;
import com.gabilheri.simpleorm.utils.QueryUtils;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import retrofit.RestAdapter;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/27/14.
 */
public class RepoContentListFragment extends DefaultListFragment {

    private static final String LOG_TAG = RepoContentListFragment.class.getSimpleName();
    private List<RepoContent> repos;
    private String dbUrl;
    private String title;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String url = getArguments().getString(getActivity().getString(R.string.url));
        title = getArguments().getString(getActivity().getString(R.string.title));
        getActivity().setTitle(title);

        repos = new ArrayList<>();
        try {
            repos = QueryUtils.findObjects(RepoContent.class, getDbHelper().getWritableDatabase(), getActivity(), "db_url", url);
            setAdapter();
        } catch (Exception ex) {
            Log.d(LOG_TAG, ex.getMessage());
        }

        if(NetworkUtils.isNetworkAvailable(getActivity())) {
            new GetRepoContents(getActivity()).execute(url);
        }
    }

    @Override
    public List<Card> getCardsList() {

        List<Card> repoContents = new ArrayList<>();

        for(RepoContent r : repos) {
            repoContents.add(new CardFileItem(getActivity(), r));
        }

        return repoContents;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(title);
    }

    private class GetRepoContents extends AsyncTask<String, Void, List<RepoContent>> {

        private Context context;
        private String dbUrl;
        private GetRepoContents(Context context) {
            this.context = context;
        }

        @Override
        protected List<RepoContent> doInBackground(String... params) {

            TokenInterceptor interceptor = new TokenInterceptor(context);

            RestAdapter restAdapter = GithubClient.getBaseRestAdapter(interceptor, context);

            GithubClient.GithubListRepoContent ghRepo = restAdapter.create(GithubClient.GithubListRepoContent.class);

            this.dbUrl = params[0];

            List<RepoContent> repos;

            try {
                repos = ghRepo.getRepoContent(dbUrl);
            } catch (RuntimeException ex) {
                return null;
            }

            return repos;
        }

        @Override
        protected void onPostExecute(List<RepoContent> r) {
            if(repos != null) {
                repos = r;

                getDbHelper().delete(RepoContent.class, "db_url", dbUrl);

                for(RepoContent rc : repos) {
                    rc.setDbUrl(dbUrl);
                    getDbHelper().save(RepoContent.class, rc);
                }
                setAdapter();
                refreshList().run();
            } else {
                ((MainActivity) getActivity()).displayView(MainActivity.FRAG_404, null);
            }
        }
    }
}
