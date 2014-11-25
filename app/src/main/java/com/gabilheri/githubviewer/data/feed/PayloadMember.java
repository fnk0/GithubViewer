package com.gabilheri.githubviewer.data.feed;

import com.google.gson.annotations.SerializedName;


/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/24/14.
 */

public class PayloadMember  {

    @SerializedName("login")
    private String login;

    public PayloadMember() {
    }

    public String getLogin() {
        return login;
    }

}
