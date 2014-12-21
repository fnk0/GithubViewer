package com.gabilheri.githubviewer.cards;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gabilheri.githubviewer.MainActivity;
import com.gabilheri.githubviewer.R;
import com.gabilheri.githubviewer.data.repo.RepoContent;
import com.gabilheri.githubviewer.network.GithubClient;
import com.gabilheri.githubviewer.utils.FileType;
import com.gabilheri.githubviewer.utils.FileUtils;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/26/14.
 */
public class CardFileItem extends Card implements Card.OnCardClickListener {

    private static final String LOG_TAG = CardFileItem.class.getSimpleName();
    private RepoContent repoContent;

    public CardFileItem(Context context, RepoContent repoContent) {
        super(context, R.layout.list_item_file);
        this.repoContent = repoContent;
        this.setOnClickListener(this);
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        super.setupInnerViewElements(parent, view);

        TextView titleView = (TextView) view.findViewById(R.id.list_title);
        TextView descriptionview = (TextView) view.findViewById(R.id.list_message);
        ImageView fileIcon = (ImageView) view.findViewById(R.id.list_icon);
        ImageView infoIcon = (ImageView) view.findViewById(R.id.info_icon);

        if(repoContent != null) {
            titleView.setText(repoContent.getName());
            descriptionview.setText("");

            if(repoContent.getType().equals(getContext().getString(R.string.dir))) {
                fileIcon.setImageResource(FileUtils.getFileDrawable(FileType.DIR));
            } else {
                fileIcon.setImageResource(FileUtils.getFileDrawable(FileUtils.getFileType(repoContent.getName())));
            }
        }
    }

    @Override
    public void onClick(Card card, View view) {
        Log.i(LOG_TAG, repoContent.getName());


        Bundle b = new Bundle();
        String url = repoContent.getUrl().replaceAll(GithubClient.API_URL + "/", "");
        b.putString(getContext().getString(R.string.url), url);
        b.putString(getContext().getString(R.string.title), repoContent.getPath());
        MainActivity m = (MainActivity) this.getContext();


        if(repoContent.getType().equals(getContext().getString(R.string.dir))) {
            m.displayView(MainActivity.REPO_LIST_FRAG, b);
        } else {
            m.displayView(MainActivity.REPO_ITEM_FRAG, b);
        }


    }
}
