package com.gabilheri.githubviewer.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/22/14.
 */
public abstract class BaseResponse {

    @SerializedName("id")
    private long id;

    @SerializedName("url")
    private String url;

    public long getId() {
        return id;
    }

    private String error = null;

    public String getError() {
        return error;
    }
}
