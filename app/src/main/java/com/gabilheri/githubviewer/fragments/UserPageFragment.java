package com.gabilheri.githubviewer.fragments;

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
import com.gabilheri.githubviewer.data.Owner;
import com.gabilheri.githubviewer.data.repo.Repo;
import com.gabilheri.githubviewer.network.GithubClient;
import com.gabilheri.githubviewer.network.TokenInterceptor;
import com.gabilheri.githubviewer.utils.DateUtils;
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
public class UserPageFragment extends DefaultFragment {

    private static final String LOG_TAG = UserPageFragment.class.getSimpleName();
    private TextView userName, userLogin, userBio, userLocation, userCompany, userEmail, userWebsite, userJoined;
    private TextView followersCount, starredCount, followingCount;
    private CircleImageView profileImage;
    private LinearLayout profileLayout, companyLayout, bioLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
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

        new GetUserProfileInfo().execute();

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
            if(owner != null) {
                Picasso.with(getActivity())
                        .load(owner.getAvatarUrl())
                        .error(R.drawable.ic_action_account_circle)
                        .into(profileImage);

                userName.setText(owner.getName());
                userLogin.setText(owner.getLogin());
                userJoined.setText("Joined " + DateUtils.getMediumDate(owner.getCreatedAt(), getActivity()));
                userLocation.setText(owner.getLocation());

                if(owner.getCompany().equals("") || owner.getCompany() != null) {
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
    }
}
