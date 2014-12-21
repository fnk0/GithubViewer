package com.gabilheri.githubviewer.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gabilheri.githubviewer.R;
import com.gabilheri.githubviewer.base.DefaultFragment;
import com.gabilheri.githubviewer.data.gists.Gist;
import com.gabilheri.githubviewer.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/16/14.
 */
public class GistsFragment extends DefaultFragment {

    private List<Gist> gists;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        gists = new ArrayList<>();

        if(NetworkUtils.isNetworkAvailable(getActivity())) {
            new GetGists().execute();
        }
    }

    public void setGistsToAdapter(List<Gist> gists) {

    }

    private class GetGists extends AsyncTask<String, Void, List<Gist>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Gist> doInBackground(String... params) {
            return null;
        }

        @Override
        protected void onPostExecute(List<Gist> gists) {
            super.onPostExecute(gists);
        }
    }
}
