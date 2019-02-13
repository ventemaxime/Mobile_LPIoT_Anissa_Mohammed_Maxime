package com.example.vente.login2.common;

/**
 * Created by vente on 30/01/2019.
 */

public class TemperatureSensorData {
    public double temperature;
    public int humidity;
    public int power;

    public static TemperatureSensorData parseData(String [] bytes){
        TemperatureSensorData data = new TemperatureSensorData();
        data.temperature = Integer.valueOf(bytes[2] + bytes[1], 16).shortValue() / 10.0;
        data.humidity = Integer.parseInt(bytes[4], 16);
        data.power = Integer.parseInt(bytes[9], 16);
        return data;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
