package com.gabilheri.githubviewer.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gabilheri.githubviewer.R;
import com.gabilheri.githubviewer.data.feed.Feed;
import com.gabilheri.githubviewer.utils.CustomUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/23/14.
 */
public class NewsFeedRecyclerView extends RecyclerView.Adapter<NewsFeedRecyclerView.NewsFeedViewHolder>  {

    private List<Feed> feeds;
    private Context context;

    public NewsFeedRecyclerView(List<Feed> feeds, Context context) {
        this.feeds = feeds;
        this.context = context;
    }

    @Override
    public NewsFeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_card, parent, false);

        return new NewsFeedViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(NewsFeedViewHolder holder, int position) {
        Feed feed = feeds.get(position);
        Picasso.with(context)
                .load(feed.getFeedActor().getAvatarUrl())
                .fit()
                .error(R.drawable.ic_action_account_circle)
                .into(holder.userAvatar);

        String type = feed.getType();
        String actor = feed.getFeedActor().getLogin();
        String repoName = feed.getFeedRepo().getName();
        String title = "";
        switch (type) {
            case "WatchEvent":
                holder.feedIcon.setText("\uf02a");
                title = String.format("<b>%s</b><font color=#9E9E9E> starred </font><b>%s</b>", actor, repoName);
                break;
            case "CreateEvent":
                holder.feedIcon.setText("\uf001");
                title = String.format("<b>%s</b> <font color=#9E9E9E> created repository </font><b>%s</b>", actor, repoName);
                break;

            case "MemberEvent":
                holder.feedIcon.setText("\uf018");
                String member = feed.getPayload().getMember().getLogin();
                title = String.format("<b>%s</b> <font color=#9E9E9E > added </font><b>%s</b> <font color=#9E9E9E > as a collaborator to </font> <b>%s</b>", actor, member, repoName);
                break;

            case "ForkEvent":
                holder.feedIcon.setText("\uf002");
                title = String.format("<b>%s</b> <font color=#9E9E9E > forked repository </font><b>%s</b>", actor, repoName);
                break;

            default:
                holder.feedIcon.setText("\uf06d");
                title = String.format("<b>%s</b> <font color=#9E9E9E > test </font><b>%s</b>", actor, repoName);
                break;
        }

        holder.feedTitle.setText(Html.fromHtml(title));

        holder.feedTime.setText(feed.getCreatedAt().toString());
    }

    @Override
    public int getItemCount() {
        return feeds.size();
    }

    public static class NewsFeedViewHolder extends RecyclerView.ViewHolder {

        protected TextView feedTitle, feedIcon, feedTime;
        protected CircleImageView userAvatar;

        public NewsFeedViewHolder(View itemView, Context context) {
            super(itemView);

            userAvatar = (CircleImageView) itemView.findViewById(R.id.user_avatar);
            feedTitle = (TextView) itemView.findViewById(R.id.feed_title);
            feedIcon = (TextView) itemView.findViewById(R.id.action_icon);
            feedTime = (TextView) itemView.findViewById(R.id.feed_time);
            feedIcon.setTypeface(CustomUtils.getGithubTypeface(context));

        }
    }
}
