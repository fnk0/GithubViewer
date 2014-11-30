package com.gabilheri.githubviewer.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gabilheri.githubviewer.R;
import com.gabilheri.githubviewer.base.DefaultFragment;
import com.gabilheri.githubviewer.data.Owner;
import com.gabilheri.githubviewer.network.GithubClient;
import com.gabilheri.githubviewer.network.TokenInterceptor;
import com.squareup.picasso.Picasso;

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

    private TextView userName, userLogin, userBio, userLocation, userCompany, userEmail, userWebsite, userJoined;
    private TextView followersCount, starredCount, followingCount;
    private CircleImageView profileImage;

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
        followersCount = (TextView) view.findViewById(R.id.followers);
        followingCount = (TextView) view.findViewById(R.id.following);
        starredCount = (TextView) view.findViewById(R.id.starred);



    }

    class GetUserProfileInfo extends AsyncTask<String, Void, Owner> {

        @Override
        protected Owner doInBackground(String... params) {

            TokenInterceptor interceptor = new TokenInterceptor(getActivity());
            RestAdapter restAdapter = GithubClient.getBaseRestAdapter(interceptor, getActivity());

            GithubClient.GithubOwner githubOwner = restAdapter.create(GithubClient.GithubOwner.class);

            return githubOwner.getOwner();
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
                userJoined.setText(owner.getCreatedAt().toString());
                userLocation.setText(owner.getLocation());

                if(owner.getCompany() != null) {
                    userCompany.setText(owner.getCompany());
                } else {
                    userCompany.setVisibility(LinearLayout.GONE);
                }

                if(owner.getEmail() != null) {
                    userEmail.setText(owner.getEmail());
                } else {
                    userEmail.setVisibility(LinearLayout.GONE);
                }

                userBio.setText(owner.getBio());
                followersCount.setText(owner.getFollowers());
                followingCount.setText(owner.getFollowing());
            }
        }
    }
}
