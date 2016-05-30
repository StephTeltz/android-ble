package com.stephteltz.android_ble;

/**
 * Created by brlstelt on 2016-05-30.
 */
class BleWrapper implements BleInterface {
    public String TAG = "BleWrapper";

    /* Validation methods */
    public boolean hasHardware() {
        /* TODO implement hardware check algorithm **/
        return true;
        //Log.d(TAG, "hasHardware() returned true");
    }

    public boolean hasPermissions() {
        /* TODO implement check algorithm */
        return true;
        //Log.d(TAG, "hasPermissions() returned true");
    }

    public boolean btEnabled() {
        /* TODO implement check algorithm */
        return true;
        //Log.d(TAG, "btEnabled() returned true");
    }

    /* Initialization methods*/
    public void btInit() {
        /* TODO implement initialization algorithm */
        //Log.d(TAG, "btInit() called");
    }

    /* Connectivity methods */
    public void scan(boolean enable) {
        /* TODO implement scanning algorithm */
        enable = true;
        //Log.d(TAG, "scan(true) called");
    }
}
