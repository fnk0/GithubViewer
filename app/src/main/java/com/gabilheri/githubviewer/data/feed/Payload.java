package com.gabilheri.githubviewer.data.feed;

import com.google.gson.annotations.SerializedName;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/23/14.
 */

public class Payload {

    @SerializedName("action")
    private String action;

    @SerializedName("ref_type")
    private String refType;

    @SerializedName("description")
    private String description;

    @SerializedName("pusher_type")
    private String user;

    @SerializedName("member")
    private PayloadMember member;

    public Payload() {
    }

    public String getRefType() {
        return refType;
    }

    public String getDescription() {
        return description;
    }

    public String getUser() {
        return user;
    }

    public PayloadMember getMember() {
        return member;
    }

    public String getAction() {
        return action;
    }

}
