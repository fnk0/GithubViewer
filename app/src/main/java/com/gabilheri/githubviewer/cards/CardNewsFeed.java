package com.gabilheri.githubviewer.cards;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gabilheri.githubviewer.MainActivity;
import com.gabilheri.githubviewer.R;
import com.gabilheri.githubviewer.data.feed.Feed;
import com.gabilheri.githubviewer.utils.CustomUtils;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/28/14.
 */
public class CardNewsFeed extends Card implements Card.OnCardClickListener {

    Feed feed;

    public CardNewsFeed(Context context, Feed feed) {
        super(context, R.layout.feed_card);
        this.feed = feed;
        this.setOnClickListener(this);
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View itemView) {
        super.setupInnerViewElements(parent, itemView);

        CircleImageView userAvatar = (CircleImageView) itemView.findViewById(R.id.user_avatar);
        TextView feedTitle = (TextView) itemView.findViewById(R.id.feed_title);
        TextView feedIcon = (TextView) itemView.findViewById(R.id.action_icon);
        TextView feedTime = (TextView) itemView.findViewById(R.id.feed_time);
        feedIcon.setTypeface(CustomUtils.getGithubTypeface(getContext()));

        Picasso.with(getContext())
                .load(feed.getFeedActor().getAvatarUrl())
                .fit()
                .error(R.drawable.ic_action_account_circle)
                .into(userAvatar);

        String type = feed.getType();
        String actor = feed.getFeedActor().getLogin();
        String repoName = feed.getFeedRepo().getName();
        String title = "";
        switch (type) {
            case "WatchEvent":
                feedIcon.setText("\uf02a");
                title = String.format("<b>%s</b><font color=#9E9E9E> starred </font><b>%s</b>", actor, repoName);
                break;
            case "CreateEvent":
                feedIcon.setText("\uf001");
                title = String.format("<b>%s</b> <font color=#9E9E9E> created repository </font><b>%s</b>", actor, repoName);
                break;

            case "MemberEvent":
                feedIcon.setText("\uf018");
                String member = feed.getPayload().getMember().getLogin();
                title = String.format("<b>%s</b> <font color=#9E9E9E > added </font><b>%s</b> <font color=#9E9E9E > as a collaborator to </font> <b>%s</b>", actor, member, repoName);
                break;

            case "ForkEvent":
                feedIcon.setText("\uf002");
                title = String.format("<b>%s</b> <font color=#9E9E9E > forked repository </font><b>%s</b>", actor, repoName);
                break;

            default:
                feedIcon.setText("\uf06d");
                title = String.format("<b>%s</b> <font color=#9E9E9E > test </font><b>%s</b>", actor, repoName);
                break;
        }

        feedTitle.setText(Html.fromHtml(title));

        feedTime.setText(feed.getCreatedAt().toString());

    }

    @Override
    public void onClick(Card card, View view) {
        Bundle b = new Bundle();
        String url =
                "repos/" + feed.getFeedRepo().getName() + "/contents";

        b.putString("url", url);

        MainActivity m = (MainActivity) this.getContext();

        m.setTitle(feed.getFeedRepo().getName());
        m.displayView(MainActivity.REPO_LIST_FRAG, b);
    }
}
