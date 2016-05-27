package com.stephteltz.android_ble;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScanFragment fragment = new ScanFragment();

        android.app.FragmentManager fragmentManager = getFragmentManager();
        android.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment_container, fragment, "ScanFragment");
        transaction.commit();

        //uiInit();
    }

    private void uiInit(View view) {
        // Initialize your UI elements here
        Intent intent = new Intent(this, BleActivity.class);
        startActivity(intent);

    }
}

/**
 * TODO optimizeForDeviceType()
 * detect device model, is device phone or tablet?
 * if device = phone, optimize layout for phone (i.e., hide the drawer)
 * if device != phone, optimize layout for tablet (i.e, lock the drawer)
 */
