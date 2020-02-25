package com.example.speed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationRequest;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private LocationManager lm;
    private TextView txt;

    int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.speed);

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
        else {
            addLoc();
        }
    }


    @Override
    public void onLocationChanged(Location location) {

        showToast("updating speed");

        if(location == null){
            txt.setText("_._ m/s");
        }
        else {
            float nCurrentSpeed = location.getSpeed();
            txt.setText(nCurrentSpeed + " m/s" );
        }

//        lm.removeUpdates(this);

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @SuppressLint("MissingPermission")
    private void addLoc() {
        lm = (LocationManager) this.getSystemService(this.LOCATION_SERVICE);

        if (lm != null){
            showToast("getting location...");
//            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,this);
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10 * 1000, (float) 10.0, this);
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 90 * 1000, (float) 10.0, this);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSION_ALL) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                addLoc();
            }
            else {
                showToast("permission not granted");
            }
        }
    }

    public void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
