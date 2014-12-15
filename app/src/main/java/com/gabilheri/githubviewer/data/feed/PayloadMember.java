package com.gabilheri.githubviewer.data.feed;

import com.gabilheri.simpleorm.annotations.OrmField;
import com.gabilheri.simpleorm.annotations.Table;
import com.gabilheri.simpleorm.builders.OrmObject;
import com.google.gson.annotations.SerializedName;


/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/24/14.
 */

@Table(name = "payloads_member_table")
public class PayloadMember extends OrmObject {

    @OrmField(name = "login")
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
