package com.stephteltz.android_ble;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by brlstelt on 2016-05-30.
 */
public class BleWrapper extends AppCompatActivity implements BleInterface {
    public String TAG = "BleWrapper";
    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;

    private BluetoothManager btManager = null;
    private BluetoothAdapter btAdapter = null;
    private BluetoothLeScanner btScanner = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* Check and set permissions */
        /* ANDROID M PERMISSIONS
        *
        * Apps targeting Android M (API level 23) must be granted location permissions at runtime
        * to allow background Bluetooth scanning.
        * This is in addition to permission declarations in Manifest.xml.
        *
        */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Android M Permission check
            if (this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("This app needs location access");
                builder.setMessage("Please grant location access so this app can detect Bluetooth LE devices.");
                builder.setPositiveButton(android.R.string.ok, null);
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_COARSE_LOCATION);
                    }
                });
                builder.show();
            }
        }

        /* Initialize Bluetooth */
        btManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        btAdapter = btManager.getAdapter();
        btAdapter.getBluetoothLeScanner();
    }

    /* Validation methods */
    public boolean hasHardware() {
        /* TODO implement hardware check algorithm **/
        return true;
        //Log.d(TAG, "hasHardware() returned true");
    }

    public boolean btEnabled() {
        /* TODO implement check algorithm */
        return true;
        //Log.d(TAG, "btEnabled() returned true");
    }

    /* Connectivity methods */
    public void scan(boolean enable) {
        /* TODO implement scanning algorithm */
        enable = true;
        //Log.d(TAG, "scan(true) called");
    }

    public void getDevices() {}
}
