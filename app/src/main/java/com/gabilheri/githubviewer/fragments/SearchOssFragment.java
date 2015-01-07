package com.gabilheri.githubviewer.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.gabilheri.githubviewer.R;
import com.gabilheri.githubviewer.base.DefaultFragment;
import com.gabilheri.githubviewer.data.GithubDbHelper;
import com.gabilheri.githubviewer.data.repo.Repo;
import com.gabilheri.githubviewer.data.repo.SearchRepo;
import com.gabilheri.githubviewer.network.GithubClient;
import java.util.List;
import it.gmariotti.cardslib.library.recyclerview.internal.CardArrayRecyclerViewAdapter;
import it.gmariotti.cardslib.library.recyclerview.view.CardRecyclerView;
import retrofit.RestAdapter;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 12/21/14.
 */
public class SearchOssFragment extends DefaultFragment {

    private static final String LOG_TAG = SearchOssFragment.class.getSimpleName();
    private CardRecyclerView recyclerViewList;
    private GithubDbHelper dbHelper;
    private CardArrayRecyclerViewAdapter mCardArrayAdapter;
    private EditText searchBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_oss, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        recyclerViewList = (CardRecyclerView) view.findViewById(R.id.recycler_list);
        recyclerViewList.setHasFixedSize(false);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchBar = (EditText) view.findViewById(R.id.search_bar);

        searchBar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //Log.d(LOG_TAG, searchBar.getText().toString());
                new SearchRepos().execute(searchBar.getText().toString());
                return true;
            }
        });
    }

    private class SearchRepos extends AsyncTask<String, Void, List<Repo>> {

        @Override
        protected List<Repo> doInBackground(String... params) {
            RestAdapter restAdapter = GithubClient.getBaseRestAdapterWithoutInterceptors();
            GithubClient.GithubSearchRepos searchRepos = restAdapter.create(GithubClient.GithubSearchRepos.class);
            SearchRepo s = searchRepos.getSearchResult(params[0], null, null);
            return s.getItems();
        }

        @Override
        protected void onPostExecute(List<Repo> repos) {
                super.onPostExecute(repos);
        }
    }
}
