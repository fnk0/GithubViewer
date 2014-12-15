package com.gabilheri.githubviewer.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gabilheri.githubviewer.R;
import com.gabilheri.githubviewer.base.DefaultFragment;
import com.gabilheri.githubviewer.data.GithubDbHelper;
import com.gabilheri.githubviewer.data.Owner;
import com.gabilheri.githubviewer.data.repo.Repo;
import com.gabilheri.githubviewer.network.GithubClient;
import com.gabilheri.githubviewer.network.TokenInterceptor;
import com.gabilheri.githubviewer.utils.CustomDateUtils;
import com.gabilheri.githubviewer.utils.NetworkUtils;
import com.gabilheri.githubviewer.utils.PreferenceUtils;
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
public class ProfilePageFragment extends DefaultFragment implements View.OnClickListener {

    private static final String LOG_TAG = ProfilePageFragment.class.getSimpleName();
    private TextView userName, userLogin, userBio, userLocation, userCompany, userEmail, userWebsite, userJoined;
    private TextView followersCount, starredCount, followingCount;
    private CircleImageView profileImage;
    private LinearLayout profileLayout, companyLayout, bioLayout;
    private Owner owner;
    private GithubDbHelper dbHelper;

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

        if(NetworkUtils.isNetworkAvailable(getActivity())) {
            new GetUserProfileInfo().execute();
        } else {
            owner = QueryUtils.findObject(Owner.class,
                    dbHelper.getWritableDatabase(),
                    getActivity(),
                    "login",
                    PreferenceUtils.getStringPreference(getActivity(), "owner", ""));
            loadOwner(owner);
        }


    }

    public void loadOwner(Owner owner) {
        if(owner != null) {
            setOwner(owner);
            Picasso.with(getActivity())
                    .load(owner.getAvatarUrl())
                    .error(R.drawable.ic_action_account_circle)
                    .into(profileImage);

            userName.setText(owner.getName());
            userLogin.setText(owner.getLogin());
            userJoined.setText("Joined " + CustomDateUtils.getMediumDate(owner.getCreatedAt(), getActivity()));
            userLocation.setText(owner.getLocation());

            if(owner.getCompany().equals("")) {
                companyLayout.setVisibility(LinearLayout.GONE);
            } else if (owner.getCompany() != null) {
                userCompany.setText(owner.getCompany());
            } else {
                Log.i(LOG_TAG, "Company null!");
                companyLayout.setVisibility(LinearLayout.GONE);
            }

            if(owner.getEmail() != null) {
                userEmail.setText(owner.getEmail());
            } else {
                userEmail.setVisibility(LinearLayout.GONE);
            }

            if(owner.getBlog() != null) {
                userWebsite.setText(owner.getBlog());
            } else {
                userWebsite.setVisibility(LinearLayout.GONE);
            }

            if(owner.getBio() != null) {
                Log.i(LOG_TAG, "Bio not null!");
                userBio.setText(owner.getBio());
            } else {
                Log.i(LOG_TAG, "Bio is null!");
                bioLayout.setVisibility(LinearLayout.GONE);
            }

            followersCount.setText("" + owner.getFollowers());
            followingCount.setText("" + owner.getFollowing());
            starredCount.setText("" + owner.getStarredCount());

            profileLayout.setVisibility(LinearLayout.VISIBLE);
        }
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    class GetUserProfileInfo extends AsyncTask<String, Void, Owner> {

        @Override
        protected Owner doInBackground(String... params) {

            TokenInterceptor interceptor = new TokenInterceptor(getActivity());
            RestAdapter restAdapter = GithubClient.getBaseRestAdapter(interceptor, getActivity());

            GithubClient.GithubOwner githubOwner = restAdapter.create(GithubClient.GithubOwner.class);

            Owner owner = githubOwner.getOwner();

            GithubClient.GithubUserStarred sr = restAdapter.create(GithubClient.GithubUserStarred.class);
            List<Repo> starredRepos = sr.getRepos();

            owner.setStarredCount(starredRepos.size());

            Log.i("OWNER: ", owner.toString());

            return owner;
        }

        @Override
        protected void onPostExecute(Owner owner) {
            QueryUtils.save(Owner.class, owner, dbHelper.getWritableDatabase(), getActivity());
            loadOwner(owner);
        }
    }

    @Override
    public void onClick(View v) {

        Intent i = null;

        switch (v.getId()) {

            case R.id.user_company:
                //i.setData(Uri.parse(owner.getCompany()));
                break;
            case R.id.website_address:
                i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(owner.getBlog()));
                startActivity(i);
                break;

            case R.id.email_address:
                i = new Intent(Intent.ACTION_SEND);
                break;

            case R.id.followers_count:
                break;

            case R.id.following_count:
                break;

            case R.id.starred_count:
                break;


        }
    }
}
