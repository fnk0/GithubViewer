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
import com.gabilheri.githubviewer.data.repo.RepoContent;
import com.gabilheri.githubviewer.network.GithubClient;
import com.gabilheri.githubviewer.network.TokenInterceptor;

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

    private List<RepoContent> repos;
    private CardRecyclerView reposList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_repo_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


        reposList = (CardRecyclerView) view.findViewById(R.id.recycler_list);
        reposList.setHasFixedSize(false);
        reposList.setLayoutManager(new LinearLayoutManager(getActivity()));

        repos = new ArrayList<RepoContent>();

        new GetRepoContents(getActivity()).execute(getArguments().getString("url"));
    }

    private class GetRepoContents extends AsyncTask<String, Void, List<RepoContent>> {

        private Context context;

        private GetRepoContents(Context context) {
            this.context = context;
        }

        @Override
        protected List<RepoContent> doInBackground(String... params) {

            TokenInterceptor interceptor = new TokenInterceptor(context);

            RestAdapter restAdapter = GithubClient.getBaseRestAdapter(interceptor, context);

            GithubClient.GithubListRepoContent ghRepo = restAdapter.create(GithubClient.GithubListRepoContent.class);

            //Log.i(LOG_TAG, "Owner: " + PreferenceUtils.getStringPreference(getActivity(), "owner", ""));
            String url = params[0];
            Log.i("QUERY_REPO_CONTENT", "https://api.github.com/" + url);

            return ghRepo.getRepoContent(url);
        }

        @Override
        protected void onPostExecute(List<RepoContent> r) {
            //super.onPostExecute(r);
            repos = r;

            //Field[] rF = r.get(0).getClass().getFields();

            ArrayList<Card> repoCards = new ArrayList<>();

            for(RepoContent rp : repos) {
                repoCards.add(new CardFileItem(getActivity(), rp));
            }

            CardArrayRecyclerViewAdapter mCardArrayAdapter = new CardArrayRecyclerViewAdapter(getActivity(), repoCards);
            reposList.setAdapter(mCardArrayAdapter);
        }
    }
}
