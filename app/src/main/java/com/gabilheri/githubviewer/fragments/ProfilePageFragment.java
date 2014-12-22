package com.gabilheri.githubviewer.fragments;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gabilheri.githubviewer.MainActivity;
import com.gabilheri.githubviewer.R;
import com.gabilheri.githubviewer.base.DefaultFragment;
import com.gabilheri.githubviewer.data.GithubDbHelper;
import com.gabilheri.githubviewer.data.User;
import com.gabilheri.githubviewer.data.repo.Repo;
import com.gabilheri.githubviewer.network.GithubClient;
import com.gabilheri.githubviewer.network.TokenInterceptor;
import com.gabilheri.githubviewer.utils.CustomDateUtils;
import com.gabilheri.githubviewer.utils.NetworkUtils;
import com.gabilheri.githubviewer.utils.PreferenceUtils;
import com.gabilheri.simpleorm.utils.DateUtils;
import com.gabilheri.simpleorm.utils.QueryUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit.RestAdapter;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/16/14.
 */
public class ProfilePageFragment extends DefaultFragment implements View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    private static final String LOG_TAG = ProfilePageFragment.class.getSimpleName();
    private static final int PROFILE_LOADER = 0;
    private TextView userName, userLogin, userBio, userLocation, userCompany, userEmail, userWebsite, userJoined;
    private TextView followersCount, starredCount, followingCount;
    private CircleImageView profileImage;
    private LinearLayout profileLayout, companyLayout, bioLayout;
    private User user;
    private GithubDbHelper dbHelper;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        //getLoaderManager().initLoader(PROFILE_LOADER, null, null);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        dbHelper = new GithubDbHelper(getActivity());
        profileImage = (CircleImageView) view.findViewById(R.id.profile_image);
        userName = (TextView) view.findViewById(R.id.user_name);
        userLogin = (TextView) view.findViewById(R.id.user_login);
        userLocation = (TextView) view.findViewById(R.id.user_location);
        userCompany = (TextView) view.findViewById(R.id.user_company);
        userEmail = (TextView) view.findViewById(R.id.email_address);
        userWebsite = (TextView) view.findViewById(R.id.website_address);
        userBio = (TextView) view.findViewById(R.id.profile_bio);
        userJoined = (TextView) view.findViewById(R.id.date_joined);
        followersCount = (TextView) view.findViewById(R.id.followers_count);
        followingCount = (TextView) view.findViewById(R.id.following_count);
        starredCount = (TextView) view.findViewById(R.id.starred_count);

        profileLayout = (LinearLayout) view.findViewById(R.id.profile_layout);
        bioLayout = (LinearLayout) view.findViewById(R.id.bio_layout);
        companyLayout = (LinearLayout) view.findViewById(R.id.company_layout);

        profileLayout.setVisibility(LinearLayout.GONE);

        userCompany.setOnClickListener(this);
        userEmail.setOnClickListener(this);
        userWebsite.setOnClickListener(this);
        followersCount.setOnClickListener(this);
        followingCount.setOnClickListener(this);
        starredCount.setOnClickListener(this);

        long id = PreferenceUtils.getLongPreference(getActivity(), "user_id", -1L);
        user = QueryUtils.findById(User.class, dbHelper.getWritableDatabase(), id, getActivity());
        loadOwner(user);

        if (NetworkUtils.isNetworkAvailable(getActivity())) {
            new GetUserProfileInfo().execute();
        }
    }

    public void loadOwner(User user) {
        if (user != null) {
            setUser(user);
            Picasso.with(getActivity())
                    .load(user.getAvatarUrl())
                    .error(R.drawable.ic_action_account_circle)
                    .into(profileImage);

            userName.setText(user.getName());
            userLogin.setText(user.getLogin());
            userJoined.setText("Joined " + CustomDateUtils.getMediumDate(user.getCreatedAt(), getActivity()));
            userLocation.setText(user.getLocation());

            if (user.getCompany().equals("")) {
                companyLayout.setVisibility(LinearLayout.GONE);
            } else if (user.getCompany() != null) {
                userCompany.setText(user.getCompany());
            } else {
                Log.i(LOG_TAG, "Company null!");
                companyLayout.setVisibility(LinearLayout.GONE);
            }

            if (user.getEmail() != null) {
                userEmail.setText(user.getEmail());
            } else {
                userEmail.setVisibility(LinearLayout.GONE);
            }

            if (user.getBlog() != null) {
                userWebsite.setText(user.getBlog());
            } else {
                userWebsite.setVisibility(LinearLayout.GONE);
            }

            if (user.getBio() != null) {
                Log.i(LOG_TAG, "Bio not null!");
                userBio.setText(user.getBio());
            } else {
                Log.i(LOG_TAG, "Bio is null!");
                bioLayout.setVisibility(LinearLayout.GONE);
            }

            followersCount.setText("" + user.getFollowers());
            followingCount.setText("" + user.getFollowing());
            starredCount.setText("" + user.getStarredCount());

            profileLayout.setVisibility(LinearLayout.VISIBLE);
        }
    }

    public void loadOwnerFromCursor(Cursor cursor) {
        if(user == null) {
            user = new User();
        }
        user.setName(cursor.getString(User.COL_NAME));
        user.setLogin(cursor.getString(User.COL_LOGIN));
        user.setEmail(cursor.getString(User.COL_EMAIL));
        user.setCompany(cursor.getString(User.COL_COMPANY));
        user.setLocation(cursor.getString(User.COL_LOCATION));
        user.setAvatarUrl(cursor.getString(User.COL_AVATAR_URL));
        user.setBlog(cursor.getString(User.COL_BLOG));
        user.setStarredCount(cursor.getInt(User.COL_STARRED_COUNT));
        user.setFollowers(cursor.getInt(User.COL_FOLLOWERS));
        user.setFollowing(cursor.getInt(User.COL_FOLLOWING));
        user.setUpdatedAt(DateUtils.getDateFromString(cursor.getString(User.COL_UPDATED_AT), getActivity()));
        user.setCreatedAt(DateUtils.getDateFromString(cursor.getString(User.COL_CREATED_AT), getActivity()));
        user.setBio(cursor.getString(User.COL_BIO));
        loadOwner(user);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    class GetUserProfileInfo extends AsyncTask<String, Void, User> {

        @Override
        protected User doInBackground(String... params) {

            TokenInterceptor interceptor = new TokenInterceptor(getActivity());
            RestAdapter restAdapter = GithubClient.getBaseRestAdapter(interceptor, getActivity());

            GithubClient.GithubOwner githubOwner = restAdapter.create(GithubClient.GithubOwner.class);

            User user = githubOwner.getOwner();

            GithubClient.GithubUserStarred sr = restAdapter.create(GithubClient.GithubUserStarred.class);
            List<Repo> starredRepos = sr.getRepos();
            dbHelper.delete(Repo.class, Repo.STARRED_COL, "1");
            for(Repo r : starredRepos) {
                r.setStarred(1);
                dbHelper.save(Repo.class, r);
            }

            user.setStarredCount(starredRepos.size());

            //Log.i("OWNER: ", owner.toString());

            return user;
        }

        @Override
        protected void onPostExecute(User user) {

            long id = PreferenceUtils.getLongPreference(getActivity(), "user_id", -1L);

            if (id != -1) {
                dbHelper.delete(User.class, id);
            }
            id = dbHelper.save(User.class, user);
            PreferenceUtils.setLongPreference(getActivity(), "user_id", id);
            loadOwner(user);
        }
    }

    @Override
    public void onClick(View v) {
        Intent i;
        MainActivity m = (MainActivity) getActivity();
        Bundle b = new Bundle();
        b.putString("user", user.getLogin());
        switch (v.getId()) {

            case R.id.user_company:
                //i.setData(Uri.parse(owner.getCompany()));
                break;
            case R.id.website_address:
                i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://" + user.getBlog()));
                startActivity(i);
                break;

            case R.id.email_address:
                i = new Intent(Intent.ACTION_SEND);
                break;

            case R.id.followers_count:
                m.setTitle("Followers");
                b.putInt("type", UsersListFragment.FOLLOWERS);
                m.displayView(MainActivity.USER_LIST_FRAG, b);
                break;

            case R.id.following_count:
                m.setTitle("Following");
                b.putInt("type", UsersListFragment.FOLLOWING);
                m.displayView(MainActivity.USER_LIST_FRAG, b);
                break;

            case R.id.starred_count:
                m.setTitle("Starred Repos");
                b.putBoolean(Repo.STARRED_COL, true);
                m.displayView(MainActivity.OWNER_REPOS_FRAG, b);
                break;
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        long _id = PreferenceUtils.getLongPreference(getActivity(), "user_id", -1L);
        Uri ownerUri = User.OwnerEntry.buildOwner(_id);
        return new CursorLoader(getActivity(), ownerUri, User.PROVIDER_COLUMNS, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        loadOwnerFromCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        loadOwnerFromCursor(null);
    }
}
