package com.stephteltz.android_ble;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;

/**
 * Created by brlstelt on 2016-05-27.
 */
interface BleInterface {
/* TODO implement a generic ble data passing interface for cross-communication between ble activities and their fragments */
    public final String TAG = "BleInterface";

    public BluetoothAdapter btAdapter = null;
    public BluetoothLeScanner btScanner = null;

    /* Validation methods */
    public boolean hasHardware();
    public boolean hasPermissions();
    public boolean btEnabled();

    /* Initialization methods*/
    public void btInit();

    /* Connectivity methods */
    public void scan(boolean enable);

    /* TODO etc... */
}
