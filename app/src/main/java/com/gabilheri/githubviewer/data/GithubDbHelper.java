package com.gabilheri.githubviewer.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.gabilheri.githubviewer.data.feed.Feed;
import com.gabilheri.githubviewer.data.feed.FeedActor;
import com.gabilheri.githubviewer.data.feed.FeedRepo;
import com.gabilheri.githubviewer.data.feed.Payload;
import com.gabilheri.githubviewer.data.feed.PayloadMember;
import com.gabilheri.githubviewer.data.feed.UserEvent;
import com.gabilheri.githubviewer.data.gists.Gist;
import com.gabilheri.githubviewer.data.repo.Repo;
import com.gabilheri.githubviewer.data.repo.RepoContent;
import com.gabilheri.githubviewer.data.repo.RepoDetail;
import com.gabilheri.simpleorm.SimpleOrmOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 12/14/14.
 */
public class GithubDbHelper extends SimpleOrmOpenHelper {

    public GithubDbHelper(Context context) {
        super(context, null);
    }

    public GithubDbHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, factory);
    }

    @Override
    public List<Class<?>> getTables() {

        List<Class<?>> tables = new ArrayList<>();
        tables.add(UserEvent.class);
        tables.add(FeedActor.class);
        tables.add(PayloadMember.class);
        tables.add(Payload.class);
        tables.add(FeedRepo.class);
        tables.add(Feed.class);
        tables.add(User.class);
        tables.add(RepoContent.class);
        tables.add(Repo.class);
        tables.add(RepoDetail.class);
        tables.add(Gist.class);
        return tables;
    }
}
