package com.stephteltz.android_ble;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        DrawerLayout fullView = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        /**
         * To lock the navigation drawer open, override lockDrawer() to return true
         */
        if (lockDrawer()) {
            fullView.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
        }
        else {
            fullView.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
        FrameLayout activityContainer = (FrameLayout) fullView.findViewById(R.id.activity_content);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);
        super.setContentView(fullView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        /**
         * To disable toolbar in an Activity, override useToolbar() to return false
         */
        if (useToolbar()) {
            setSupportActionBar(toolbar);
            setTitle(R.string.activity_name_base); // TODO change dynamically with loaded activity
        }
        else {
            toolbar.setVisibility(View.GONE);
        }
    }

    protected boolean useToolbar() { return true; }
    protected boolean lockDrawer() { return false; }
}

