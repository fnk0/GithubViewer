package com.gabilheri.githubviewer.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.gabilheri.githubviewer.base.DefaultListFragment;
import com.gabilheri.githubviewer.cards.UserCard;
import com.gabilheri.githubviewer.data.User;
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
 * @since 12/18/14.
 */
public class UsersListFragment extends DefaultListFragment {

    private static final String LOG_TAG = UsersListFragment.class.getSimpleName();
    private List<User> usersList;
    public static final int FOLLOWING = 0;
    public static final int FOLLOWERS = 1;
    private int type = -1;
    private String user;
    private String col;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getArguments() != null) {
            type = getArguments().getInt("type");
            user = getArguments().getString("user");
        }

        usersList = new ArrayList<>();
        col = null;
        if(type == FOLLOWERS) {
            col = "is_follower";
        } else {
            col = "is_following";
        }

        try {
            usersList = QueryUtils.findObjects(User.class, getDbHelper().getWritableDatabase(), getActivity(), col, 1L);
            setAdapter();
        } catch (Exception ex) {
            Log.d(LOG_TAG, ex.getMessage());
        }

        if(NetworkUtils.isNetworkAvailable(getActivity())) {
            new LoadUSerList().execute();
        }

    }

    @Override
    public List<Card> getCardsList() {
        ArrayList<Card> cards = new ArrayList<>();

        for(User u : usersList) {
            cards.add(new UserCard(getActivity(), u.getAvatarUrl(), u.getLogin()));
        }

        return cards;
    }

    private class LoadUSerList extends AsyncTask<String, Void, List<User>> {
        @Override
        protected List<User> doInBackground(String... params) {

            TokenInterceptor interceptor = new TokenInterceptor(getActivity());

            RestAdapter restAdapter = GithubClient.getBaseRestAdapter(interceptor, getActivity());

            switch (type) {
                case FOLLOWING:
                    GithubClient.GithubFollowing following = restAdapter.create(GithubClient.GithubFollowing.class);
                    return following.getFollowing(user);
                case FOLLOWERS:
                    GithubClient.GithubFollowers followers = restAdapter.create(GithubClient.GithubFollowers.class);
                    return followers.getFollowers(user);
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<User> users) {
            super.onPostExecute(users);
            usersList = users;
            getDbHelper().delete(User.class, col, "1");
            for(User u : usersList) {
                if(type == FOLLOWING) {
                    u.setIsFollowing(1);
                } else if(type == FOLLOWERS) {
                    u.setIsFollower(1);
                }
                getDbHelper().save(User.class, u);
            }

            setAdapter();
            refreshList().run();
        }
    }
}
