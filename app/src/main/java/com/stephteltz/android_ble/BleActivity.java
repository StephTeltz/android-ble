/* TODO Note: this class will not have a ui.
ScanFragment (and any other fragments req'g BLE functionality )will extend it and provide the ui elements.
 */

package com.stephteltz.android_ble;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class BleActivity {

    private final String TAG = "ScanActivity";
    private static final long SCAN_PERIOD = 10000; // BLE scan timeout = 10 seconds
    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;

    private Toolbar toolbar;
    private Button buttonScanStart;
    private ProgressBar spinner;
    private TextView textView;
    private ArrayAdapter resultsAdapter;
    private ArrayList<String> resultsData;
    private BluetoothAdapter btAdapter;
    private BluetoothLeScanner btScanner;
    private ScanCallback mScanCallback;
    private boolean mScanning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ble);

        checkPermissions();
        init();
    }

    // Check permissions
    private void checkPermissions() {
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
    }

    // Initialization
    private void init() {
        // Initialize Bluetooth elements
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        btScanner = btAdapter.getBluetoothLeScanner();

        // Initialize UI elements
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        spinner = (ProgressBar) findViewById(R.id.spinner);
        spinner.setVisibility(4); // By default spinner is invisible
        textView = (TextView) findViewById(R.id.text_view);
        textView.setText("Click 'Start Scan' to search for devices.");
        buttonScanStart = (Button) findViewById(R.id.bt_scan_start);

        // Set UI behaviour
        buttonScanStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!mScanning) { // if not already scanning (mScanning = false), start scanning
                    scan(true);
                    buttonScanStart.setText("Stop scan");
                    spinner.setVisibility(0); // Spinner is visible while scanning

                } else if (mScanning) { // if already scanning (mScanning = true), stop scanning
                    scan(false);
                    buttonScanStart.setText("Start scan");
                    //textView.setText("Click 'Start Scan' to search for devices.");
                    spinner.setVisibility(4); // Spinner is invisible when not scanning
                }

                Log.d(TAG, "Test");
            }
        });

        // Initialize device scan callback

        mScanCallback = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                textView.setText(result.toString());
            }
        };

    }

    // Scan for advertising devices

    private void scan(final boolean enable) {
        // if true, start scanning
        // unless its already scanning
        // in that case, prevent user from initiating second scan (button hide start)
        // if false, stop scanning
        // unless its already stopped
        // in that case, prevent user from stopping it again (button hide stop)
        if (enable) {
            btScanner.startScan(mScanCallback);
            mScanning = true;
        } else if (!enable) {
            btScanner.stopScan(mScanCallback);
            mScanning = false;
        } else
            // TODO error handling
            Log.d(TAG, "scan() Error");
    }

}

