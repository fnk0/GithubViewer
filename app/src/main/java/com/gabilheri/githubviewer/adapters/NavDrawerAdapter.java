package com.gabilheri.githubviewer.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gabilheri.githubviewer.R;
import com.gabilheri.githubviewer.utils.PreferenceUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/16/14.
 */
public class NavDrawerAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<NavDrawerItem> navDrawerItems;

    /**
     * Default Constructor
     */
    public NavDrawerAdapter() {
    }

    /**
     *
     * @param context
     *      The Context on which this NavDrawer is being created
     * @param navDrawerItems
     *      The ArrayList containing the DrawersItems for the Adapter.
     */
    public NavDrawerAdapter(Context context, ArrayList<NavDrawerItem> navDrawerItems) {
        this.context = context;
        this.navDrawerItems = navDrawerItems;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<NavDrawerItem> getNavDrawerItems() {
        return navDrawerItems;
    }

    public void setNavDrawerItems(ArrayList<NavDrawerItem> navDrawerItems) {
        this.navDrawerItems = navDrawerItems;
    }

    /**
     * Internally used by the framework.
     * @return
     *      The number of elements on this adapter
     */
    @Override
    public int getCount() {
        return navDrawerItems.size();
    }

    /**
     * The getItem is also necessary. Will be used by the onItemClickListener on the ListView for this adapter
     *
     * @param position
     *      The clicked position
     * @return
     *      The object for the position
     */
    @Override
    public Object getItem(int position) {
        return navDrawerItems.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean isEnabled(int position) {

        return position != 0 && super.isEnabled(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            if(position == 0) {
                convertView = mInflater.inflate(R.layout.profile_drawer_item, null);

                CircleImageView circleImageView = (CircleImageView) convertView.findViewById(R.id.profile_image);
                String avatarUrl = PreferenceUtils.getStringPreference(context, "owner_avatar", null);
                if(avatarUrl != null) {
                    Log.i("NAV_DRAWER", "Url: " + avatarUrl);
                    Picasso.with(context).
                            load(avatarUrl)
                            .fit()
                            .error(R.drawable.ic_action_account_circle)
                            .into(circleImageView);
                }

                TextView name = (TextView) convertView.findViewById(R.id.drawer_name);
                name.setText(PreferenceUtils.getStringPreference(context, "owner_name", ""));

                TextView login = (TextView) convertView.findViewById(R.id.drawer_username);
                login.setText(PreferenceUtils.getStringPreference(context, "owner", ""));

            } else {
                convertView = mInflater.inflate(R.layout.drawer_list_item, null);
                TextView imageIcon = (TextView) convertView.findViewById(R.id.navDrawerIcon);
                TextView title = (TextView) convertView.findViewById(R.id.navDrawerTitle);

                Typeface mFont = Typeface.createFromAsset(context.getApplicationContext().getAssets(), "octicons.ttf");
                imageIcon.setTypeface(mFont);

                title.setText(navDrawerItems.get(position).getTitle());
                imageIcon.setText(navDrawerItems.get(position).getIcon());
            }
        }

        return convertView;
    }
}
