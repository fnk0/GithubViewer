package com.gabilheri.githubviewer.fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.gabilheri.githubviewer.MainActivity;
import com.gabilheri.githubviewer.R;
import com.gabilheri.githubviewer.base.DefaultFragment;
import com.gabilheri.githubviewer.data.Owner;
import com.gabilheri.githubviewer.data.UserToken;
import com.gabilheri.githubviewer.network.BasicInterceptor;
import com.gabilheri.githubviewer.network.GithubClient;
import com.gabilheri.githubviewer.network.LoginRequest;
import com.gabilheri.githubviewer.network.TokenInterceptor;
import com.gabilheri.githubviewer.utils.PreferenceUtils;

import retrofit.RestAdapter;


/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/16/14.
 */
public class LoginFragment extends DefaultFragment implements View.OnClickListener {

    private static String LOG_TAG = LoginFragment.class.getSimpleName();

    private EditText username, password;

    private ProgressDialog pd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Button b = (Button) view.findViewById(R.id.sign_in);
        b.setOnClickListener(this);

        username = (EditText) view.findViewById(R.id.username);
        password = (EditText) view.findViewById(R.id.password);


        if(PreferenceUtils.getStringPreference(getActivity(), "token", null) != null) {
            ((MainActivity) getActivity()).displayView(MainActivity.REPOS_FRAG, null);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in:
                if(username.getText().toString() != null && password.getText().toString() != null) {
                    new ExecuteLogin().execute(username.getText().toString(), password.getText().toString());
                }
                break;
        }
    }

    class ExecuteLogin extends AsyncTask<String, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(getActivity(), R.style.FrameworkRoot_Theme);
            pd.setIndeterminate(true);
            pd.setCancelable(false);
            pd.setTitle("Signing in....");
            pd.show();
        }

        @Override
        protected Boolean doInBackground(String... params) {

            BasicInterceptor interceptor = new BasicInterceptor(params[0], params[1], getActivity());

            RestAdapter restAdapter = GithubClient.getBaseRestAdapter(interceptor, getActivity());

            GithubClient.GithubAuth testAuth = restAdapter.create(GithubClient.GithubAuth.class);

            UserToken userToken = testAuth.getUserToken(new LoginRequest());

            if(userToken.getToken() != null) {
                PreferenceUtils.setStringPreference(getActivity(), "token", userToken.getToken());

                //Log.i(LOG_TAG, "Token: " + PreferenceUtils.getStringPreference(getActivity(), "token", ""));

                TokenInterceptor tokenInterceptor = new TokenInterceptor(getActivity());

                restAdapter = GithubClient.getBaseRestAdapter(tokenInterceptor, getActivity());

                GithubClient.GithubOwner githubOwner = restAdapter.create(GithubClient.GithubOwner.class);

                Owner owner = githubOwner.getOwner();

                PreferenceUtils.setStringPreference(getActivity(), "owner", owner.getLogin());
                PreferenceUtils.setStringPreference(getActivity(), "owner_name", owner.getName());
                PreferenceUtils.setStringPreference(getActivity(), "owner_avatar", owner.getAvatarUrl());

                return true;
            }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(pd.isShowing()) {
                pd.dismiss();
            }

            if(aBoolean) {
                ((MainActivity) getActivity()).displayView(MainActivity.REPOS_FRAG, null);
            }
        }
    }
}
