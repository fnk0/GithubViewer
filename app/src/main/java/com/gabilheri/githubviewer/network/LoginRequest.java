package com.gabilheri.githubviewer.network;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/23/14.
 */
public class LoginRequest {

    final String client_id = "def6f1d5824fb5df5bdc";
    final String client_secret = "3a4218a886e84648cd0997f2aad9cff757dbb53f";
    String[] scopes = {"user","repo","gist","notifications","repo:status"};
    final String note = "Github Viewer App";

    public LoginRequest() {

    }

    public String getClient_id() {
        return client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public String[] getScopes() {
        return scopes;
    }

    public String getNote() {
        return note;
    }
}
