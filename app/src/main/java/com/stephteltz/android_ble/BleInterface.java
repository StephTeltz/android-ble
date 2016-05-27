package com.stephteltz.android_ble;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;

/**
 * Created by brlstelt on 2016-05-27.
 */
protected interface BleInterface {
/* TODO implement a generic ble data passing interface for cross-communication between ble activities and their fragments */
    protected BluetoothAdapter btAdapter = null;
    protected BluetoothLeScanner btScanner = null;

    protected void btInit() {};

    protected void scan() {};

    /* TODO etc... */
}
