package com.stephteltz.android_ble;

import android.Manifest;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

class MainActivity extends AppCompatActivity {
    public String TAG = "MainActivity";
    private final int PERMISSION_REQUEST_COARSE_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Initialize Fragments */
        setFragment(new ScanFragment(), R.id.fragment_container);
    }

    @Override
    public void setContentView(int layoutResID) {
        DrawerLayout fullView = (DrawerLayout) getLayoutInflater().inflate(layoutResID, null);
        super.setContentView(fullView);

        /* Layout options */
        /* To lock the navigation drawer open, override lockDrawer() to return true */
        if (lockDrawer()) {
            fullView.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
        }
        else {
            fullView.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
        /* To disable toolbar in an Activity, override useToolbar() to return false */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (enableToolbar()) {
            setSupportActionBar(toolbar);
            setTitle(R.string.activity_name_main); // TODO change dynamically with loaded activity
        }
        else {
            toolbar.setVisibility(View.GONE);
        }
    }

    protected boolean enableToolbar() { return true; }
    protected boolean lockDrawer() { return false; }

    /* Initialize Fragments */
    protected void setFragment(Fragment fragment, int container) {
        android.app.FragmentManager manager = this.getFragmentManager();
        android.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(container, fragment, "ScanFragment");
        transaction.commit();
    }
}



/**
 * TODO optimizeForDeviceType()
 * detect device model, is device phone or tablet?
 * if device = phone, optimize layout for phone (i.e., hide the drawer)
 * if device != phone, optimize layout for tablet (i.e, lock the drawer)
 */

/**
 * TODO remove if not needed in setContentView()
FrameLayout activityContainer = (FrameLayout) fullView.findViewById(R.id.activity_content);
getLayoutInflater().inflate(layoutResID, activityContainer, true);
 */
