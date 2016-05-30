/**
 * TODO Note: this class will have ui only, functionality will br provided by BleActivity.class
 */

package com.stephteltz.android_ble;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by brlstelt on 2016-05-27.
 */
class ScanFragment extends ListFragment {
    public final String TAG = "ScanFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BleWrapper bleWrapper = new BleWrapper();
        /* TODO remove debug only */
        Toast.makeText(this.getContext(), bleWrapper.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.scan_fragment_layout, container, false);
    }

    /* TODO remove dummy array */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter adapter = null;
        setListAdapter(adapter);
    }
}
