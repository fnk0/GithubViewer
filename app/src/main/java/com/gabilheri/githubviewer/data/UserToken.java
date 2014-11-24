package com.gabilheri.githubviewer.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/22/14.
 */
public class UserToken {

    @SerializedName("token")
    private String token;

    @SerializedName("note")
    private String note;

    public String getToken() {
        return token;
    }

    public String getNote() {
        return note;
    }
}
