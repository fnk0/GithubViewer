package com.gabilheri.githubviewer;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.gabilheri.githubviewer.adapters.NavDrawerAdapter;
import com.gabilheri.githubviewer.adapters.NavDrawerItem;
import com.gabilheri.githubviewer.base.DefaultFragment;
import com.gabilheri.githubviewer.base.DrawerActivity;
import com.gabilheri.githubviewer.fragments.Fragment404;
import com.gabilheri.githubviewer.fragments.ItemDetailFragment;
import com.gabilheri.githubviewer.fragments.LoginFragment;
import com.gabilheri.githubviewer.fragments.NewsFeedFragment;
import com.gabilheri.githubviewer.fragments.ProfilePageFragment;
import com.gabilheri.githubviewer.fragments.RepoContentListFragment;
import com.gabilheri.githubviewer.fragments.RepositoriesFragment;
import com.gabilheri.githubviewer.fragments.SearchOssFragment;
import com.gabilheri.githubviewer.fragments.UsersListFragment;
import com.gabilheri.githubviewer.network.LogoutTask;
import com.gabilheri.githubviewer.utils.CustomUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends DrawerActivity {

    public static final int LOGIN_FRAG = -5;
    public static final int FRAG_404 = -4;
    public static final int USER_LIST_FRAG = -3;
    public static final int REPO_ITEM_FRAG = -2;
    public static final int REPO_LIST_FRAG = -1;
    public static final int NEWS_FEED_FRAG = 1;
    public static final int OWNER_REPOS_FRAG = 2;
    public static final int SEARCH_OSS_FRAG = 3;
    public static final int PROFILE_FRAG = 4;
    public static final int SIGN_OUT = 5;
    private DefaultFragment activeFragment = null;
    private NavDrawerAdapter navDrawerAdapter;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private String[] navMenuTitles, navIcons;
    private HashMap<Integer, String> fragmentTitles;
    private Bundle currentBundle;
    private DefaultFragment leftFragment = null;

    @Override
    protected BaseAdapter getAdapter() {
        return navDrawerAdapter;
    }

    @Override
    public void init() {
        navIcons = getResources().getStringArray(R.array.nav_icons);
        navMenuTitles = getResources().getStringArray(R.array.nav_titles);
        navDrawerItems = new ArrayList<>();

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
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CustomUtils.showDrawerToggle(this);
        boolean loadLeft = true;
        switch (position) {
            case NEWS_FEED_FRAG:
                activeFragment = new NewsFeedFragment();
                clearBackStack();
                break;
            case LOGIN_FRAG:
                activeFragment = new LoginFragment();
                clearBackStack();
                CustomUtils.hideDrawerToggle(this);
                loadLeft = false;
                break;
            case OWNER_REPOS_FRAG:
                activeFragment = new RepositoriesFragment();
                clearBackStack();
                break;
            case REPO_LIST_FRAG:
                activeFragment = new RepoContentListFragment();
                break;
            case REPO_ITEM_FRAG:
                activeFragment = new ItemDetailFragment();
                loadLeft = false;
                break;
            case SIGN_OUT:
                Log.i("SIGN OUT!!:", "Clicked sign out button!");
                new LogoutTask(this).execute();
                clearBackStack();
                break;

            case SEARCH_OSS_FRAG:
                activeFragment = new SearchOssFragment();
                clearBackStack();
                break;

            case PROFILE_FRAG:
                activeFragment = new ProfilePageFragment();
                loadLeft = false;
                clearBackStack();
                break;

            case USER_LIST_FRAG:
                activeFragment = new UsersListFragment();
                break;

            case FRAG_404:
                activeFragment = new Fragment404();
                break;

            default:
                break;
        }

        if(isTablet()) {
            if(loadLeft) {
                getLeftLayout().setVisibility(LinearLayout.VISIBLE);
                if(leftFragment == null) {
                    leftFragment = new ProfilePageFragment();
                }
                loadLeftFrag(leftFragment);
            } else {
                getLeftLayout().setVisibility(LinearLayout.GONE);
            }
        }

        if(activeFragment != null) {
            if(fragmentBundle != null) {
                currentBundle = fragmentBundle;
                activeFragment.setArguments(fragmentBundle);
            }

            fragmentTransaction.replace(R.id.frame_container, activeFragment).addToBackStack(null).commit();
            if(position >= 0) {
                getDrawerList().setItemChecked(position, true);
                getDrawerList().setSelection(position);
                setTitle(navMenuTitles[position]);
            }
        } else {
            Log.i(getLogTag(), "Error creating fragment");
        }
    }


}
