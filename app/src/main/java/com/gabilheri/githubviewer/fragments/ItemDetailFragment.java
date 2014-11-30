package com.gabilheri.githubviewer.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.gabilheri.githubviewer.R;
import com.gabilheri.githubviewer.base.DefaultFragment;
import com.gabilheri.githubviewer.data.repo.RepoContent;
import com.gabilheri.githubviewer.network.GithubClient;
import com.gabilheri.githubviewer.network.TokenInterceptor;
import com.gabilheri.githubviewer.utils.FileType;
import com.gabilheri.githubviewer.utils.FileUtils;

import org.markdown4j.Markdown4jProcessor;

import java.io.IOException;

import retrofit.RestAdapter;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/27/14.
 */
public class ItemDetailFragment extends DefaultFragment {

    private WebView content;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.file_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        content = (WebView) view.findViewById(R.id.content);

        new GetRepoContent(getActivity()).execute(getArguments().getString("url"));

    }

    private class GetRepoContent extends AsyncTask<String, Void, RepoContent> {

        private Context context;

        private GetRepoContent(Context context) {
            this.context = context;
        }

        @Override
        protected RepoContent doInBackground(String... params) {

            TokenInterceptor interceptor = new TokenInterceptor(context);

            RestAdapter restAdapter = GithubClient.getBaseRestAdapter(interceptor, context);

            GithubClient.GithubRepoContent ghRepos = restAdapter.create(GithubClient.GithubRepoContent.class);

            //Log.i(LOG_TAG, "Owner: " + PreferenceUtils.getStringPreference(getActivity(), "owner", ""));
            String url = params[0];
            Log.i("QUERY_REPO_CONTENT", "https://api.github.com/" + url);

            return ghRepos.getRepoContent(url);
        }

        @Override
        protected void onPostExecute(RepoContent r) {
            if(r != null) {
                StringBuilder renderedString = new StringBuilder();
                try  {
                    if(FileUtils.getFileType(r.getName()) == FileType.MARKDOWN) {
                        Log.i("RENDER TEXT: ", "Markdown!!");
                        renderedString.append(new Markdown4jProcessor().process(r.getContent()));
                    } else {
                        String language = "markup";
                        switch (FileUtils.getFileType(r.getName())) {
                            case JAVA:
                                language = "java";
                                break;
                            case PYTHON:
                                language = "python";
                                break;
                            case RUBY:
                                language = "ruby";
                                break;
                            case CSS:
                                language = "css";
                                break;
                            case JS:
                                language = "js";
                                break;
                        }
                        renderedString.append("<!doctype html>\n")
                                .append("<html lang=\"en\">\n")
                                .append("<head>\n")
                                .append("    <meta charset=\"utf-8\">")
                                .append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">")
                                .append("<link rel=\"stylesheet\" href=\"prism.css\">\n")
                                .append("</head>\n")
                                .append("<body>")
                                //.append("<pre><code>")
                                //.append(FileUtils.getFileExtension(r.getName()))
                                //.append("\">")
                                .append(new Markdown4jProcessor().process("```language-" + language + "\n" +  r.getContent() + "```"))
                                //.append("</code></pre>")
                                .append("<script src=\"prism.js\"></script>")
                                .append("</body>\n")
                                .append("</html>");
                    }

                    content.getSettings().setJavaScriptEnabled(true);
                    content.setWebChromeClient(new WebChromeClient());
                    content.getSettings().setBuiltInZoomControls(true);
                    content.getSettings().setSupportZoom(true);
                    content.getSettings().setUseWideViewPort(true);
                    //Log.i("HTML STRING: ", renderedString.toString());
                    content.loadDataWithBaseURL("file:///android_asset/", renderedString.toString(), "text/html", "utf-8", null);
                } catch (IOException ex) {
                    content.loadDataWithBaseURL("file:///android_asset/", "<h1>404 not found! </h1>", "text/html", "utf-8", null);
                }

            }
        }
    }
}
