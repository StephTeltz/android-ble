package com.stephteltz.android_ble;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
public class BleWrapper extends Activity {
    private static String TAG = "BleWrapper";
    private static final long SCAN_PERIOD = 10000; // BLE scan timeout = 10 seconds
    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;

    private boolean mScanning = false;
    private Context mParent = null;
    private BluetoothManager btManager = null;
    private BluetoothAdapter btAdapter = null;
    private BluetoothLeScanner btScanner = null;
    private ScanCallback btScanCallback = null;
    private ArrayList<ScanResult> scanResultsArray = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    };

    /* Creates BleWrapper object, sets it parent activity and callback object */
    public BleWrapper(Context parent) {
        this.mParent = parent;
    }

    public BluetoothManager         getManager()    { return btManager; }
    public BluetoothAdapter         getAdapter()    { return btAdapter; }
    public BluetoothLeScanner       getScanner()    { return btScanner; }
    public ArrayList<ScanResult>    getDataArray()  { return scanResultsArray; }

    /* Initialize BLE and get BT Manager, Adapter & BLE Scanner */
    public boolean initialize() {
        if (btManager == null) {
            btManager =
                    (BluetoothManager) mParent.getSystemService(Context.BLUETOOTH_SERVICE);
            if (btManager == null) {
                return false;
            }
        }
        if (btAdapter == null) btAdapter = btManager.getAdapter();
        if (btAdapter == null) {
            return false;
        }
        if (btScanner == null) btScanner = btAdapter.getBluetoothLeScanner();
        if (btScanner == null) {
            return false;
        }
        if (btScanCallback == null) {
            btScanCallback = new ScanCallback() {
                @Override
                public void onScanResult(int callbackType, ScanResult result) {
                    super.onScanResult(callbackType, result);
                    scanResultsArray.add(result);
                }
            };
        }
        if (btScanCallback == null) {
            return false;
        }
        scanResultsArray = new ArrayList<ScanResult>();
        if (scanResultsArray == null) {
            return false;
        }
        return true;
    }

    /* Validation methods */
    public boolean hasBleHardware() {
        /* Check general Bluetooth hardware */
        final BluetoothManager manager =
                (BluetoothManager) mParent.getSystemService(Context.BLUETOOTH_SERVICE);
        if (manager == null) return false;
        final BluetoothAdapter adapter = manager.getAdapter();
        if (adapter == null) return false;
        /* Then check BLE support */
        boolean hasBle =
                mParent.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE);
        return hasBle;
    }

    /* Before any action check if BT is enabled */
    /* Call this in onResume() to be sure BT is enabled if your app is hidden */
    public boolean isBtEnabled() {
        /* TODO implement check algorithm */
        final BluetoothManager manager =
                (BluetoothManager) mParent.getSystemService(Context.BLUETOOTH_SERVICE);
        if (manager == null) return false;

        final BluetoothAdapter adapter = manager.getAdapter();
        if (adapter == null) return false;
        /* If BT isn't enabled, request the user to do so */
        if (!adapter.isEnabled()) {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, 1);
        }
        return btAdapter.isEnabled();
    }

    /* Check for location access enabled, if not enabled request user to take action */
    public void hasPermissions() {
        /* ANDROID M PERMISSIONS
        *
        * Apps targeting Android M (API level 23) must be granted location permissions at runtime
        * to allow background Bluetooth scanning.
        * This is in addition to permission declarations in Manifest.xml.
        *
        */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Android M Permission check
            if (mParent.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(this.getApplicationContext(), R.style.AppTheme);
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

    /* Scan for advertising BLE devices */
    public void scan(final boolean enable) {

        if (enable) {
            btScanner.startScan(btScanCallback);
            mScanning = true;
        }
        else if (!enable) {
            btScanner.stopScan(btScanCallback);
            mScanning = false;
        }
        return;
    }

    public void getDevices() {}
}
