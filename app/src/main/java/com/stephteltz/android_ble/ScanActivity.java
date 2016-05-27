package com.stephteltz.android_ble;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ScanActivity extends BaseActivity {

    private static final String TAG = "ScanActivity";
    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;

    // TODO refine global variables
    ListView scanList;
    TextView scanTitle;
    Button connectNew;
    Button scanStart;
    ArrayList<String> scanArray;
    ArrayAdapter adapter;
    BluetoothAdapter btAdapter;
    BluetoothLeScanner btScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        uiInit();

        // TODO remove test ArrayList
        for (int i = 1; i <= 5; i++) {
            String item = "Test item " + i;
            scanArray.add(item);
        }
    }

    private void uiInit() {
        // Initialize UI
        scanTitle = (TextView) findViewById(R.id.scan_title);
        scanList = (ListView) findViewById(R.id.scan_list);
        scanArray = new ArrayList<String>();
        adapter = new ArrayAdapter(ScanActivity.this, android.R.layout.simple_list_item_1, scanArray);
        scanList.setAdapter(adapter);
    }

    private void btInit() {

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
}
