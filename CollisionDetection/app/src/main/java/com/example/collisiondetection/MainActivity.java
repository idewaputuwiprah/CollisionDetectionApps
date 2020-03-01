package com.example.collisiondetection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.example.collisiondetection.Database.DatabaseHelper;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private TextView textX, textY, textZ;
    private Sensor accSensor;
    private SensorManager sManager;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accSensor = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sManager.registerListener(this, accSensor,SensorManager.SENSOR_DELAY_NORMAL);

        textX = findViewById(R.id.X);
        textY = findViewById(R.id.Y);
        textZ = findViewById(R.id.Z);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        textX.setText("X: " + event.values[0]);
        textY.setText("Y: " + event.values[1]);
        textZ.setText("Z: " + event.values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
