package com.example.speed2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.speed2.database.DatabaseHelper;
import com.google.android.material.tabs.TabLayout;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    Button requestLocation, removeLocation, input;
    Context context = this;
    boolean klik = false;

    MyBackgroundService mServices = null;
    boolean mBound = false;

    SensorService sensorService = null;
    boolean sBound = false;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private ServiceToActivity locReceiver;
    private String spd;
    private final ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            MyBackgroundService.LocalBinder binder = (MyBackgroundService.LocalBinder) iBinder;

            mServices = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mServices = null;
            mBound = false;
        }
    };
    private final ServiceConnection sensorServiceConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            SensorService.SensorLocalBinder binder = (SensorService.SensorLocalBinder) iBinder;

            sensorService = binder.getService();
            sBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            sBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tablayout_id);
        viewPager = findViewById(R.id.viewpager_id);
        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager());

        adapter.addFragment(new FragmentPairing(), "Pairing");
        adapter.addFragment(new FragmentEditData(this), "Edit Data");
        adapter.addFragment(new FragmentNomorPenting(), "Nomor Penting");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        Dexter.withActivity(this)
                .withPermissions(Arrays.asList(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                ))
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        requestLocation = findViewById(R.id.request_location_updates_button);
                        removeLocation = findViewById(R.id.remove_location_update_button);
                        input = findViewById(R.id.push_data);

                        requestLocation.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mServices.requestLocationUpdates();
                            }
                        });

                        removeLocation.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mServices.removeLocationUpdate();
                            }
                        });

                        setButtonState(Common.requestingLocationUpdate(MainActivity.this));
                        bindService(new Intent(MainActivity.this, MyBackgroundService.class),
                                mServiceConnection,
                                Context.BIND_AUTO_CREATE);
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                    }
                }).check();

        locReceiver = new ServiceToActivity();
        IntentFilter intentFilterLoc = new IntentFilter("LocToActivityAction");
        registerReceiver(locReceiver, intentFilterLoc);
    }

    @Override
    protected void onStart() {
        super.onStart();
        PreferenceManager.getDefaultSharedPreferences(this)
                .registerOnSharedPreferenceChangeListener(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        if(mBound) {
            unbindService(mServiceConnection);
            mBound = false;
        }
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
        EventBus.getDefault().unregister(this);
        super.onStop();

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if (s.equals(Common.KEY_REQUESTING_LOCATION_UPDATES)) {
            setButtonState(sharedPreferences.getBoolean(Common.KEY_REQUESTING_LOCATION_UPDATES, false));
        }
    }

    private void setButtonState(boolean isRequestEnable) {
        if (isRequestEnable) {
            requestLocation.setEnabled(false);
            removeLocation.setEnabled(true);
            input.setEnabled(true);

        }
        else {
            requestLocation.setEnabled(true);
            removeLocation.setEnabled(false);
            input.setEnabled(false);
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onListenLocation(SendLocationToActivity event) {
        if (event != null) {
            String data = new StringBuilder()
                    .append(event.getLocation().getLatitude())
                    .append("/")
                    .append(event.getLocation().getLongitude())
                    .append("/")
                    .append(event.getLocation().getSpeed())
                    .toString();
//            Toast.makeText(mServices, data, Toast.LENGTH_SHORT).show();
        }
    }

    public void onClick(View v) {
        Intent intent = new Intent(this, SensorService.class);
//        intent.putExtra("SPEED", spd);
        bindService(intent, sensorServiceConn, Context.BIND_AUTO_CREATE);
        klik = true;
    }

    public void stop(View v) {
        unbindService(sensorServiceConn);
        klik = false;
    }

    public class ServiceToActivity extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            Bundle notificationData = intent.getExtras();
            String speed = notificationData.getString("LocToActivityKey");
//            spd = speed;
//            Toast.makeText(context, speed, Toast.LENGTH_SHORT).show();
            if (klik) {
                sendSpeedToActivity(speed);
            }
        }
    }

    private void sendSpeedToActivity(String newData){
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction("SPEED");
        broadcastIntent.putExtra("SPEED", newData);
        sendBroadcast(broadcastIntent);
    }
}
