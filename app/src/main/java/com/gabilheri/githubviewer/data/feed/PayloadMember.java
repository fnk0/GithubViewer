package com.gabilheri.githubviewer.data.feed;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;


/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/24/14.
 */

public class PayloadMember extends SugarRecord<PayloadMember> {

    @SerializedName("login")
    private String login;

    public PayloadMember() {
    }

    public String getLogin() {
        return login;
    }


    @Override
    public String toString() {
        return "PayloadMember{" +
                "login='" + login + '\'' +
                '}';
    }
}
