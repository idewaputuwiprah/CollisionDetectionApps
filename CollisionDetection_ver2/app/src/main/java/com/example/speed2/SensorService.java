package com.example.speed2;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class SensorService extends Service implements SensorEventListener {

    private Sensor accSensor;
    private SensorManager sManager;
    private final IBinder sensorBinder = new SensorLocalBinder();

    String[] accelerometer;
    MainActivity activity;

    public SensorService() {

    }

    @Override
    public void onCreate() {
        sManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accSensor = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sManager.registerListener(this, accSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return sensorBinder;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        accelerometer = new String[]{event.values[0]+"", event.values[1]+"", event.values[2]+""};
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public class SensorLocalBinder extends Binder {
        SensorService getService() {
            return SensorService.this;
        }
    }
}