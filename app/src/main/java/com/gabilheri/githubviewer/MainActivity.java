package com.gabilheri.githubviewer;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.BaseAdapter;

import com.gabilheri.githubviewer.adapters.NavDrawerAdapter;
import com.gabilheri.githubviewer.adapters.NavDrawerItem;
import com.gabilheri.githubviewer.base.DefaultFragment;
import com.gabilheri.githubviewer.base.DrawerActivity;
import com.gabilheri.githubviewer.fragments.LoginFragment;
import com.gabilheri.githubviewer.fragments.NewsFeedFragment;
import com.gabilheri.githubviewer.fragments.RepositoriesFragment;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends DrawerActivity {

    public static final int LOGIN_FRAG = 5;
    public static final int NEWS_FEED_FRAG = 1;
    public static final int REPOS_FRAG = 2;
    private DefaultFragment activeFragment = null;
    private NavDrawerAdapter navDrawerAdapter;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private String[] navMenuTitles, navIcons;
    private HashMap<Integer, String> fragmentTitles;
    private Bundle currentBundle;

    @Override
    protected BaseAdapter getAdapter() {
        return navDrawerAdapter;
    }

    @Override
    public void init() {
        navIcons = getResources().getStringArray(R.array.nav_icons);
        navMenuTitles = getResources().getStringArray(R.array.nav_titles); // Retrieve the titles
        navDrawerItems = new ArrayList<NavDrawerItem>(); // Initialize the ArrayList

        // Now let's add add items to the ArrayList of NavDrawer items.
        for(int i = 0; i < navMenuTitles.length; i++) {
            navDrawerItems.add(new NavDrawerItem(navMenuTitles[i], navIcons[i]));
        }

        navDrawerAdapter = new NavDrawerAdapter(this, navDrawerItems);

    }

    @Override
    public void restoreFragment(Bundle savedInstanceState) {

    }

    @Override
    public void displayView(int position, Bundle fragmentBundle) {
        FragmentManager fragmentManager = getSupportFragmentManager(); // Get the fragmentManager for this activity
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (position) {
            case NEWS_FEED_FRAG:
                activeFragment = new NewsFeedFragment();
                break;
            case LOGIN_FRAG:
                activeFragment = new LoginFragment(); // Set the ActiveFragment to our selected item on the list
                clearBackStack(); // Clear the back stack to avoid back presses bugs
                break;
            case REPOS_FRAG:
                activeFragment = new RepositoriesFragment();
                clearBackStack();
                break;
            default:
                break;
        }

        if(activeFragment != null) {
            if(fragmentBundle != null) {
                currentBundle = fragmentBundle;
                activeFragment.setArguments(fragmentBundle);
            }

            fragmentTransaction.replace(R.id.frame_container, activeFragment).commit(); // Commit the change
            // update selected item and title
            if(position >= 0) {
                getDrawerList().setItemChecked(position, true); // We now set the item on the drawer that has been cliced as active
                getDrawerList().setSelection(position); // Same concept as above...
                setTitle(navMenuTitles[position]); // We not change the title of the Action Bar to match our fragment.
            } else {
                if(fragmentBundle == null) {
                    setTitle(fragmentTitles.get(position)); // We not change the title of the Action Bar to match our fragment.
                }
            }
        } else {
            Log.i(getLogTag(), "Error creating fragment"); // if the fragment does not create we Log an error.
        }
    }
}
