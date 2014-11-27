package com.gabilheri.githubviewer.cards;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gabilheri.githubviewer.MainActivity;
import com.gabilheri.githubviewer.R;
import com.gabilheri.githubviewer.data.repo.Repo;
import com.gabilheri.githubviewer.utils.CustomUtils;
import com.gabilheri.githubviewer.utils.PreferenceUtils;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/26/14.
 */
public class CardRepo extends Card implements Card.OnCardClickListener{

    private static final String LOG_TAG = CardRepo.class.getSimpleName();

    private Repo repo;

    public CardRepo(Context context, Repo repo) {
        super(context, R.layout.repo_card);
        this.repo = repo;
        this.setOnClickListener(this);
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        super.setupInnerViewElements(parent, view);

        TextView titleView = (TextView) view.findViewById(R.id.repo_title);
        TextView subTitleView = (TextView) view.findViewById(R.id.repo_subtitle);
        TextView starCountView = (TextView) view.findViewById(R.id.stars_count);
        TextView repoIcon = (TextView) view.findViewById(R.id.repo_icon);
        repoIcon.setTypeface(CustomUtils.getGithubTypeface(this.getContext()));

        if(repo != null) {
            titleView.setText(repo.getName());
            subTitleView.setText(repo.getDescription());
            starCountView.setText("" + repo.getStargazersCount());

            if(repo.isForked()) {
                repoIcon.setText("\uf002");
            } else {
                repoIcon.setText("\uf001");
            }
        }
    }

    @Override
    public void onClick(Card card, View view) {
        Log.i(LOG_TAG, repo.getName());

        Bundle b = new Bundle();
        String url =
                "repos/" +
                PreferenceUtils.getStringPreference(getContext(), "owner", "") +
                "/" + repo.getName() + "/contents";

        b.putString("url", url);

        MainActivity m = (MainActivity) this.getContext();

        m.setTitle(repo.getName());
        m.displayView(MainActivity.REPO_LIST_FRAG, b);

    }
}
