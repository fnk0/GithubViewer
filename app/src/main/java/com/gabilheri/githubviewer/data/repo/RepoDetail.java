package com.gabilheri.githubviewer.data.repo;

import com.gabilheri.simpleorm.annotations.OrmField;
import com.gabilheri.simpleorm.annotations.Table;
import com.gabilheri.simpleorm.annotations.Unique;
import com.gabilheri.simpleorm.builders.OrmObject;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 12/17/14.
 */
@Table(name = "repo_detail")
public class RepoDetail extends OrmObject {

    @Unique
    @OrmField(name = "url")
    private String url;

    @OrmField(name = "html_string")
    private String htmlString;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtmlString() {
        return htmlString;
    }

    public void setHtmlString(String htmlString) {
        this.htmlString = htmlString;
    }

    @Override
    public String toString() {
        return "RepoDetail{" +
                "url='" + url + '\'' +
                ", htmlString='" + htmlString + '\'' +
                '}';
    }
}
