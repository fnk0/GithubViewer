package com.gabilheri.githubviewer.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gabilheri.githubviewer.R;
import com.gabilheri.githubviewer.data.repo.RepoContent;
import com.gabilheri.githubviewer.utils.FileUtils;

import java.util.List;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/26/14.
 */
public class FileAdapter extends RecyclerView.Adapter<FileAdapter.FileViewHolder> {

    private static final String LOG_TAG = FileAdapter.class.getSimpleName();

    List<RepoContent> repocontent;
    Context context;

    public FileAdapter(List<RepoContent> repocontent, Context context) {
        this.repocontent = repocontent;
        this.context = context;
    }

    @Override
    public FileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_file, parent, false);

        return new FileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FileViewHolder holder, int position) {
        RepoContent repo = repocontent.get(position);
        holder.titleView.setText(repo.getName());
        holder.descriptionview.setText("");
        holder.fileIcon.setImageResource(FileUtils.getFileDrawable(FileUtils.getFileType(repo.getName())));
    }

    @Override
    public int getItemCount() {
        return repocontent.size();
    }

    public static class FileViewHolder extends RecyclerView.ViewHolder {

        protected ImageView fileIcon, infoIcon;
        protected TextView titleView, descriptionview;

        public FileViewHolder(View itemView) {
            super(itemView);

            titleView = (TextView) itemView.findViewById(R.id.list_title);
            descriptionview = (TextView) itemView.findViewById(R.id.list_message);
            fileIcon = (ImageView) itemView.findViewById(R.id.list_icon);
            infoIcon = (ImageView) itemView.findViewById(R.id.info_icon);
        }
    }
}
