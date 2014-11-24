package com.gabilheri.githubviewer.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gabilheri.githubviewer.R;
import com.gabilheri.githubviewer.data.Repo;

import java.util.List;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/23/14.
 */
public class RepoRecyclerViewAdapter extends RecyclerView.Adapter<RepoRecyclerViewAdapter.RepoViewHolder> {

    private static String LOG_TAG = RepoRecyclerViewAdapter.class.getSimpleName();
    private List<Repo> repos;
    private Context context;

    public RepoRecyclerViewAdapter(Context context, List<Repo> repos) {
        this.repos = repos;
        this.context = context;
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View repoView = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_card, parent, false);

        return new RepoRecyclerViewAdapter.RepoViewHolder(repoView, context);
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {
        Repo repo = repos.get(position);
        holder.titleView.setText(repo.getName());
        holder.subTitleView.setText(repo.getDescription());
        holder.starCountView.setText("" + repo.getStargazersCount());

        if(repo.isForked()) {
            holder.repoIcon.setText("\uf002");
        } else {
            holder.repoIcon.setText("\uf001");
        }
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    public static class RepoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView titleView, subTitleView, starCountView, repoIcon;

        public RepoViewHolder(View itemView, Context context) {
            super(itemView);
            itemView.setOnClickListener(this);
            titleView = (TextView) itemView.findViewById(R.id.repo_title);
            subTitleView = (TextView) itemView.findViewById(R.id.repo_subtitle);
            starCountView = (TextView) itemView.findViewById(R.id.stars_count);
            repoIcon = (TextView) itemView.findViewById(R.id.repo_icon);
            Typeface mFont = Typeface.createFromAsset(context.getApplicationContext().getAssets(), "octicons.ttf");
            repoIcon.setTypeface(mFont);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
