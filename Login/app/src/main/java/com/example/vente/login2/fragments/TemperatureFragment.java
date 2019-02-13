package com.example.vente.login2.fragments;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vente.login2.R;
import com.example.vente.login2.common.RetrieveDataTask;
import com.example.vente.login2.common.TemperatureSensorData;

/**
 * Created by vente on 28/01/2019.
 */

public class TemperatureFragment extends Fragment {

    public TemperatureFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sensor_temperature, container, false);
        BluetoothManager bm = (BluetoothManager) getActivity().getSystemService(Activity.BLUETOOTH_SERVICE);
        BluetoothAdapter bt = bm.getAdapter();
        if(bt == null){
            Log.e("Bluetooth", "Bluetooth Adapter not available" );
        }
        if(!bt.isEnabled()){
            Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(i, 99);
        } else {
            new RetrieveDataTask(getActivity(), bt).execute();
        }
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent i){
        if(requestCode == 99 && resultCode == Activity.RESULT_OK){
            Log.i("Bluetooth", "Bluetooth is enabled ...");
        } else {
            Log.i("Bluetooth", "Bluetooth must be enabled.");
        }
    }
}
