package com.gabilheri.githubviewer.cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gabilheri.githubviewer.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 12/18/14.
 */
public class UserCard extends Card implements Card.OnCardClickListener {

    private CircleImageView circleImageView;
    private TextView userLogin;

    private String avatarUrl, login;

    public UserCard(Context context) {
        super(context, R.layout.user_card);
    }

    public UserCard(Context context, String avatarUrl, String login) {
        super(context, R.layout.user_card);
        this.avatarUrl = avatarUrl;
        this.login = login;
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        super.setupInnerViewElements(parent, view);

        circleImageView = (CircleImageView) view.findViewById(R.id.user_avatar);
        userLogin = (TextView) view.findViewById(R.id.user_login);

        if(userLogin != null && circleImageView != null) {
            userLogin.setText(login);
            Picasso.with(getContext())
                    .load(avatarUrl)
                    .fit()
                    .error(R.drawable.ic_action_account_circle)
                    .into(circleImageView);
        }

    }

    @Override
    public void onClick(Card card, View view) {

    }
}
