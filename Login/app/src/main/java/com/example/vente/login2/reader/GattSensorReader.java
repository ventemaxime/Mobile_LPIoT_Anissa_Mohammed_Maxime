package com.example.vente.login2.reader;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;

public class GattSensorReader implements ISensorReader {

    private BluetoothAdapter bluetoothAdapter;
    private Context context;
    private String[] data = new String[10];
    private final Object synchObj = new Object();

    public GattSensorReader(Context context, BluetoothAdapter bluetoothAdapter) {
        this.bluetoothAdapter = bluetoothAdapter;
        this.context = context;
    }

    @Override
    public String[] readRawData(String macAddress) {

        if (macAddress.isEmpty()) {
            throw new RuntimeException("Mac address missing.");
        }
        BluetoothDevice bluetoothDevice = bluetoothAdapter.getRemoteDevice(macAddress);
        bluetoothDevice.connectGatt(context, false, new BeeWiSmartBtCallback(data, synchObj));
        synchronized (synchObj) {
            try {
                // wait max 15 seconds for the callback to retrieve the data from the sensor
                synchObj.wait(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (data[0] == null) {
            throw new RuntimeException("Failed reading data.");
        }

        return data;
    }

}
