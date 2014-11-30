package com.gabilheri.githubviewer.network;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/27/14.
 */
public class MarkdownRequest {

    String text, mode;

    public MarkdownRequest() {
    }

    public MarkdownRequest(String text) {
        this.text = text;
        this.mode = "markdown";
    }

    public MarkdownRequest(String text, String mode, String context) {
        this.text = text;
        this.mode = mode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
