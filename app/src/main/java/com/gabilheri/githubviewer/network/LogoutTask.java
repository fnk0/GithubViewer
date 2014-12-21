package com.gabilheri.githubviewer.network;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.gabilheri.githubviewer.MainActivity;
import com.gabilheri.githubviewer.data.GithubDbHelper;
import com.gabilheri.githubviewer.utils.PreferenceUtils;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/30/14.
 */
public class LogoutTask extends AsyncTask<String, Void, Boolean> {

    private Context context;

    public LogoutTask(Context context) {
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(String... params) {

        String credentials = PreferenceUtils.getStringPreference(context, "basic", "");
        BasicInterceptor interceptor = new BasicInterceptor(credentials, context);
        RestAdapter restAdapter = GithubClient.getBaseRestAdapter(interceptor, context);
        GithubClient.GithubSignOut signOut = restAdapter.create(GithubClient.GithubSignOut.class);
        final GithubDbHelper dbHelper = new GithubDbHelper(context);
        try {

            Callback callback = new Callback() {
                @Override
                public void success(Object o, Response response) {
                    Log.i("SIGN_OUT: ", "Successfully Logged out!!");
                    PreferenceUtils.clearAllPreferences(context);

                    List<Class<?>> tables = dbHelper.getTables();

                    for(Class<?> t : tables) {
                        dbHelper.deleteAllEntriesForClass(t);
                    }

                    ((MainActivity) context).displayView(MainActivity.LOGIN_FRAG, null);
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    Log.i("SIGN_OUT: ", "ERROR!! Logging out!!");
                }
            };

            signOut.signOut(PreferenceUtils.getStringPreference(context, "token_id", ""), callback);

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.i("Sign Out Exception: ", ex.getMessage());
            return false;
        }
    }
}
