package com.stephteltz.android_ble;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ScanActivity extends AppCompatActivity implements BleInterface {

    private static final String TAG = "ScanActivity";
    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;

    // TODO refine global variables
    /*
    Button connectNew;
    Button scanStart;
    ArrayList<String> scanArray;
    ArrayAdapter adapter;
    BluetoothAdapter btAdapter;
    BluetoothLeScanner btScanner;
    */

    /* TODO Ble Lifecycle methods */
    /*
    // Called only once when the app launches, it will never be cycled back to
    onCreate() {
        /* Initialize reusable BT elements
        BluetoothAdapter btAdapter = new BluetoothAdapter();
        BluetoothLeScanner btScanner = new BluetoothLeScanner();

        /* Validate BT on device
        // Does it support BT?                          Hardware check.
        // Does it support BTLE?                        Hardware check.
        // Does it have a valid adapter and scanner?    Software check.
        // Does it have permission?                     Permission check.
        // Enable permissions?                          Permission request.
    }

    // Called at initial launch after onCreate()
    // And called after onRestart() when app is fully hidden
    onStart() {}

    // Called at initial launch after onStart()
    // And called after onPause() when app is partially hidden
    onResume() {
        // was BT disabled? check. don't make it intrusive because
        // onResume() is also called as part of startup procedure
        // and wiould be redundant with onCreate() checks
        // if scanning was interrupted, notify the user they must restart scanning
    }

    // Called when app becomes partially hidden
    // ui still visible
    onPause() {
        // if scan in progress, stop scanning
        // if scan not in progress, don't do anything.
        // bt checks will be done onResume()
    }

    // Called after onPause() as part of shutdown procedure
    // Hides the ui
    onStop() {
        // if scanning, stop scanning (no scanning in background)
    }

    // restarts after its been hidden
    onRestart() {
        // check if BT was disabled while the app was closed
        // included in onRestart() and not onStart()
        // because onStart() may be called multiple times
        // onResume() is the equivalent of onCreate()
        // if scanning was interrupted notify the user they must restart scanning
    }

    // called when app is closed, AND WHEN ORIENTATION IS CHANGED
    onDestroy () {
        // stop scanning and destroy any memory using bt features
    }
    */


    /**
     * TODO LIFECYCLE METHODS
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        /* Check if device is BLE enabled and initialize BLE elements */
        if (hasHardware() && hasPermissions()) {
            btInit();
        } else {  // attempt to resolve the requirements and if not possible exit the app gracefully
        }

        /* Check that BLE was initialized properly */
        if (btEnabled()) {
            // Continue
        } else {
            // Request user to enable BT
            // Exit gracefully if error
        }
    }

    @Override
    protected void onStart() {}

    @Override
    protected void onResume() {}

    @Override
    protected void onRestart() {}

    @Override
    protected void onPause() {}

    @Override
    protected void onStop() {}

    @Override
    protected void onDestroy() {}

    /**
     * BLE INTERFACE METHODS
     */
    @Override
    protected boolean hasHardware() {
        // Check hardware requirements
        }

    @Override
    protected boolean hasPermissions() {}

    @Override
    protected void btInit() {

        // Initialize Bluetooth radio
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        btScanner = btAdapter.getBluetoothLeScanner();

        // Check if Bluetooth is supported and enabled on the device
        // If Bluetooth is not supported, notify the user and close the app.
        // If Bluetooth is supported but not enabled, prompt the user to enable Bluetooth.
        if (btAdapter == null) {
            Toast.makeText(getApplicationContext(), "No Bluetooth detected.", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            if (!btAdapter.isEnabled()) {
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(intent, 1);
            }
        }
    }

    @Override
    protected boolean btEnabled() {}

    @Override
    public boolean scan() {
        Log.d("ScanActivity", "scan(true)");
    }
}
