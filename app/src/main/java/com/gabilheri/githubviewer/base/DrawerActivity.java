package com.gabilheri.githubviewer.base;

import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.gabilheri.githubviewer.R;


/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/5/14.
 */
public abstract class DrawerActivity extends ActionBarActivity {

    private final String LOG_TAG = getLogTag();

    /**
     * Nav Drawer stuff
     */
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle, mTitle;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        if(toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setElevation(4);
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        /**
         * Drawer Layout stuff
         */
        mTitle = mDrawerTitle = getTitle();
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList.setOnItemClickListener(new DrawerListener());


        init();

        mDrawerList.setAdapter(getAdapter());

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                toolbar,
                R.string.app_name, // Nav drawer open - description for accessibility
                R.string.app_name // Nav drawer close
        ) {

            @Override
            public void onDrawerStateChanged(int newState) {
                super.onDrawerStateChanged(newState);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
                syncState();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
                syncState();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        if(savedInstanceState != null) {
            restoreFragment(savedInstanceState);
        } else if(savedInstanceState == null) {
            displayView(0, null);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstance has occurred
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * Drawer listener.
     */
    private class DrawerListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
            /**
             * A handler with a postDelayed is used so it only changes fragments once the drawer is
             * already closed. This can be adjusted if the timing is not right.
             * Similar behavior that most Google apps offers.
             */
            mDrawerLayout.closeDrawer(mDrawerList);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    displayView(position, null);
                }
            }, 300);
        }
    }

    /**
     * Method to define what clicking on the drawer will do.
     *
     * @param position
     *          The position of the clicked item
     * @param fragmentBundle
     *          A bundle in case something needs to be passed to a specific fragment
     */
    public abstract void displayView(int position, Bundle fragmentBundle);

    /**
     *
     * @param savedInstanceState
     */
    public abstract void restoreFragment(Bundle savedInstanceState);

    /**
     * Any specific initializations should go here.
     */
    public abstract void init();

    /**
     * Override this method to change the log tag string;
     * @return
     */
    public String getLogTag() {
        return "DrawerActivity";
    }

    /**
     * Override this method in case of need for a different list colors, etc..
     * Should use same Id's to avoid confusion
     * @return
     *      The Activity layout for this drawer activity
     */
    private int getLayout() {
        return R.layout.drawer_activity;
    }

    /**
     * Getter for the drawer toggle
     * @return
     *      The drawer toggle for this activity
     */
    public ActionBarDrawerToggle getDrawerToggle() {
        return mDrawerToggle;
    }

    /**
     *
     * @return
     *      The List used by the drawer
     */
    public ListView getDrawerList() {
        return mDrawerList;
    }

    /**
     *
     * @return
     *      The Drawer Layout used by this activity
     */
    public DrawerLayout getDrawerLayout() {
        return mDrawerLayout;
    }

    /**
     *
     * Method to be Overriden that will return an Adapter that extends Base Adapter
     * The adapter will them be used by the Drawer Layout
     *
     * @return
     */
    protected abstract BaseAdapter getAdapter();

    /**
     * Handy method to clear the back stack. We want to do this to avoid back stack bugs.
     */
    public void clearBackStack() {
        FragmentManager manager = getFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    @Override
    public void onBackPressed() {
        // If the back stack is empty we let android handle the back button
        if(getFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else {
            // Otherwise we remove it from the back stack and the framework will handle the
            // fragment change for us :)
            getFragmentManager().popBackStack();
            getSupportActionBar().setTitle(mTitle);
        }
    }
}
