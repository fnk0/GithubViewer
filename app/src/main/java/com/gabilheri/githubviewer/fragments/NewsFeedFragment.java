package com.gabilheri.githubviewer.fragments;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gabilheri.githubviewer.R;
import com.gabilheri.githubviewer.base.DefaultFragment;
import com.gabilheri.githubviewer.cards.CardNewsFeed;
import com.gabilheri.githubviewer.data.Dummy;
import com.gabilheri.githubviewer.data.feed.Feed;
import com.gabilheri.githubviewer.data.repo.Person;
import com.gabilheri.githubviewer.network.GithubClient;
import com.gabilheri.githubviewer.network.TokenInterceptor;
import com.gabilheri.githubviewer.utils.PreferenceUtils;
import com.gabilheri.simpleorm.SimpleOrmOpenHelper;

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
public class NewsFeedFragment extends DefaultFragment {

    private static final String LOG_TAG = NewsFeedFragment.class.getSimpleName();
    private List<Feed> feeds;
    private CardRecyclerView feedsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        feedsList = (CardRecyclerView) view.findViewById(R.id.recycler_list);
        feedsList.setHasFixedSize(false);
        feedsList.setLayoutManager(new LinearLayoutManager(getActivity()));

        feeds = new ArrayList<>();
        new GetFeed().execute();
    }

    private class GetFeed extends AsyncTask<String, Void, List<Feed>> {

        @Override
        protected List<Feed> doInBackground(String... params) {
            TokenInterceptor interceptor = new TokenInterceptor(getActivity());

            RestAdapter restAdapter = GithubClient.getBaseRestAdapter(interceptor, getActivity());

            GithubClient.GithubFeed ghFeed = restAdapter.create(GithubClient.GithubFeed.class);

            return ghFeed.getFeed(PreferenceUtils.getStringPreference(getActivity(), "owner", ""));
        }

        @Override
        protected void onPostExecute(List<Feed> f) {
            //super.onPostExecute(feeds);
            feeds = f;

            ArrayList<Card> feedCards = new ArrayList<>();

            for(Feed fi : feeds) {
                feedCards.add(new CardNewsFeed(getActivity(), fi));

            }

            SimpleOrmOpenHelper openHelper = new SimpleOrmOpenHelper(getActivity(), null) {
                @Override
                public List<Class<?>> getTables() {

                    List<Class<?>> tables = new ArrayList<>();
                    tables.add(Dummy.class);
                    tables.add(Person.class);
                    return tables;
                }
            };

            SQLiteDatabase db = openHelper.getWritableDatabase();

            if(db.isOpen()) {
                Log.i(LOG_TAG, "Db is open!");

                ContentValues c = new ContentValues();
                c.put("name", "Marcus");
                c.put("age", 26);

                long row = db.insert("dummy_table", null, c);
                Log.i(LOG_TAG, "Row: " + row);

                ContentValues personValues = new ContentValues();
                personValues.put("name", "Marcus");
                personValues.put("age", 26);
                personValues.put("birthday", "April 16, 1988");

                long rowId = db.insert("person_table", null, personValues);
                Log.i(LOG_TAG, "Person row: " + rowId);


            }

            /*
            Annotation[] annotations = Dummy.class.getAnnotations();
            Field[] fields = Dummy.class.getDeclaredFields();

            for(Annotation a : annotations) {
                Log.i(LOG_TAG, "Annotation: " + a.toString());
            }

            for(Field fi : fields) {
                Annotation[] annotations1 = fi.getDeclaredAnnotations();
                Log.i(LOG_TAG, "Field: " + fi.getName());
                for(Annotation an : annotations1) {

                    Log.i(LOG_TAG, "Annotation: " + an.annotationType());
                    if(NotNull.class == an.annotationType()) {
                        Log.i(LOG_TAG, "Not Null!!");
                    }

                    if(Unique.class == an.annotationType()) {
                        Log.i(LOG_TAG, "Unique!!");
                    }
                }
            }
            */
            CardArrayRecyclerViewAdapter adapter = new CardArrayRecyclerViewAdapter(getActivity(), feedCards);
            feedsList.setAdapter(adapter);
        }
    }
}
