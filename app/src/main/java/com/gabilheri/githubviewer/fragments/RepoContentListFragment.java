package com.gabilheri.githubviewer.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gabilheri.githubviewer.R;
import com.gabilheri.githubviewer.base.DefaultFragment;
import com.gabilheri.githubviewer.cards.CardFileItem;
import com.gabilheri.githubviewer.data.GithubDbHelper;
import com.gabilheri.githubviewer.data.repo.RepoContent;
import com.gabilheri.githubviewer.network.GithubClient;
import com.gabilheri.githubviewer.network.TokenInterceptor;
import com.gabilheri.githubviewer.utils.NetworkUtils;
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
 * @since 11/27/14.
 */
public class RepoContentListFragment extends DefaultFragment {

    private static final String LOG_TAG = RepoContentListFragment.class.getSimpleName();
    private List<RepoContent> repos;
    private CardRecyclerView reposList;
    private GithubDbHelper dbHelper;
    private CardArrayRecyclerViewAdapter mCardArrayAdapter;
    private String dbUrl;
    private String title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dbHelper = new GithubDbHelper(getActivity());
        return inflater.inflate(R.layout.list_repo_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        String url = getArguments().getString(getActivity().getString(R.string.url));
        title = getArguments().getString(getActivity().getString(R.string.title));
        reposList = (CardRecyclerView) view.findViewById(R.id.recycler_list);
        reposList.setHasFixedSize(false);
        reposList.setLayoutManager(new LinearLayoutManager(getActivity()));

        repos = new ArrayList<>();
        try {
            repos = QueryUtils.findObjects(RepoContent.class, dbHelper.getWritableDatabase(), getActivity(), "db_url", url);
            setAdapterFromlist(repos);
        } catch (Exception ex) {
            Log.d(LOG_TAG, ex.getMessage());
        }

        if(NetworkUtils.isNetworkAvailable(getActivity())) {
            new GetRepoContents(getActivity()).execute(url);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(title);
    }

    public void setAdapterFromlist(List<RepoContent> repos) {
        ArrayList<Card> repoCards = new ArrayList<>();

        for(RepoContent rp : repos) {
            repoCards.add(new CardFileItem(getActivity(), rp));
        }

        mCardArrayAdapter = new CardArrayRecyclerViewAdapter(getActivity(), repoCards);
        reposList.setAdapter(mCardArrayAdapter);
    }

    private Runnable refreshList() {
        return new Runnable() {
            @Override
            public void run() {
                mCardArrayAdapter.notifyDataSetChanged();
                reposList.refreshDrawableState();
            }
        };
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
            return ghRepo.getRepoContent(dbUrl);
        }

        @Override
        protected void onPostExecute(List<RepoContent> r) {
            repos = r;

            dbHelper.delete(RepoContent.class, "db_url", dbUrl);

            for(RepoContent rc : repos) {
                rc.setDbUrl(dbUrl);
                dbHelper.save(RepoContent.class, rc);
            }

            setAdapterFromlist(repos);
            refreshList().run();
        }
    }
}
