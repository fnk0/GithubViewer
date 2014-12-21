package com.gabilheri.githubviewer.base;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gabilheri.githubviewer.R;
import com.gabilheri.githubviewer.data.GithubDbHelper;

import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.recyclerview.internal.CardArrayRecyclerViewAdapter;
import it.gmariotti.cardslib.library.recyclerview.view.CardRecyclerView;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 12/18/14.
 */
public abstract class DefaultListFragment extends DefaultFragment {

    private CardRecyclerView recyclerViewList;
    private GithubDbHelper dbHelper;
    private CardArrayRecyclerViewAdapter mCardArrayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dbHelper = new GithubDbHelper(getActivity());
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        recyclerViewList = (CardRecyclerView) view.findViewById(R.id.recycler_list);
        recyclerViewList.setHasFixedSize(false);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public void setAdapter() {
        mCardArrayAdapter = new CardArrayRecyclerViewAdapter(getActivity(), getCardsList());
        recyclerViewList.setAdapter(mCardArrayAdapter);
    }

    public Runnable refreshList() {
        return new Runnable() {
            @Override
            public void run() {
                mCardArrayAdapter.notifyDataSetChanged();
                recyclerViewList.refreshDrawableState();
            }
        };
    }

    public abstract List<Card> getCardsList();

    public CardRecyclerView getRecyclerViewList() {
        return recyclerViewList;
    }

    public GithubDbHelper getDbHelper() {
        return dbHelper;
    }

    public CardArrayRecyclerViewAdapter getCardArrayAdapter() {
        return mCardArrayAdapter;
    }
}
