package com.example.vente.login2.common;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.vente.login2.R;
import com.example.vente.login2.reader.GattSensorReader;
import com.example.vente.login2.reader.ISensorReader;


/**
 * Created by vente on 30/01/2019.
 */

public class RetrieveDataTask extends AsyncTask<Void, Void, TemperatureSensorData> {

    private Activity activity;
    private BluetoothAdapter btAdapter;
    ProgressDialog pDialog;

    public RetrieveDataTask(Activity activity, BluetoothAdapter btAdapter){
        this.activity = activity;
        this.btAdapter = btAdapter;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        pDialog = new ProgressDialog(activity);
        pDialog.setMessage("Getting data sensor...");
        pDialog.setIndeterminate(false);
        pDialog.show();
    }
    @Override
    protected TemperatureSensorData doInBackground(Void... voids) {
        try{
            String sensorMacAdress = "F0:C7:7F:85:35:0C";
            ISensorReader reader = new GattSensorReader(activity, btAdapter);
            String[] rawData = reader.readRawData(sensorMacAdress);
            TemperatureSensorData data = TemperatureSensorData.parseData(rawData);
            return data;
        } catch(RuntimeException e){
            Log.e("test", e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(TemperatureSensorData result){
        if(result == null){
            return;
        } else {
            TextView temp = activity.findViewById(R.id.textTemp);
            TextView humi = activity.findViewById(R.id.textHumidity);
            TextView power = activity.findViewById(R.id.textPower);
            temp.setText(String.valueOf(result.getTemperature()) + "°C");
            humi.setText(String.valueOf(result.getHumidity()) + "%");
            power.setText(String.valueOf(result.getPower()) + "%");
        }


    }
}
