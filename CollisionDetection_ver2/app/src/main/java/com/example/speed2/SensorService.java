package com.example.speed2;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import java.io.IOException;

public class SensorService extends Service implements SensorEventListener {

    private Sensor accSensor;
    private SensorManager sManager;
    private final IBinder sensorBinder = new SensorLocalBinder();
    private String spd;
    WriteFile fileCtr = new WriteFile();

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
        spd = intent.getStringExtra("SPEED");
        return sensorBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        String data = (event.values[0] + " " + event.values[1] + " " + event.values[2] + " " + spd + "\n");
//        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
//        sendMessageToActivity(data);
        try {
                fileCtr.writeToFile(data);
        }
        catch (IOException e) {
                e.printStackTrace();
        }
//        if (Double.parseDouble(accelerometer[1]) <= 5.0f) {
//            onCall();
//        }
    }

    @SuppressLint("MissingPermission")
    public void onCall() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:081284343425"));
        startActivity(intent);
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
