package com.elite.etl;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.elite.etl.base.BaseActivity;
import com.elite.etl.model.Result;
import com.elite.etl.receiver.NetWorkChangReceiver;
import com.elite.etl.viewmodel.MainVM;

import java.util.List;

public class MainActivity extends BaseActivity<MainActivity, MainVM> {
    private final String TAG = "MainActivity";
    @Override
    public int tellMeLayout() {
        return R.layout.activity_main;
    }

    @Override
    public Class<MainVM> getVMClass() {
        return MainVM.class;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        Button btn = findViewById(R.id.et);
        btn.setOnClickListener(v -> getViewModel().getNews());
//        btn.setOnClickListener(v -> handler.sendEmptyMessage(0));
//        btn.setOnClickListener(v -> {
//            EtLog.e(TAG, "isMobileConnected : " + NetWorkUtil.isMobileConnected(getApplicationContext()));
//            EtLog.e(TAG, "isWifiConnected : " + NetWorkUtil.isWifiConnected(getApplicationContext()));
//            EtLog.e(TAG, "isNetworkConnected : " + NetWorkUtil.isNetworkConnected(getApplicationContext()));
//            EtLog.e(TAG, "getAPNType : " + NetWorkUtil.getAPNType(getApplicationContext()));
//        });
//        registerNetWorkChangReceiver();
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //MediaPlayerUtil.play(VoiceEnum.SERVICE_START);
            //gps();
        }
    };

    public void showResult(Result r) {
        Toast.makeText(this, r.toString(), Toast.LENGTH_SHORT).show();
    }

    private void gps() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                Log.e("wesker", "checkSelfPermission:");
                return;
            }
        List<String> list = locationManager.getProviders(true);
        if (list != null) {
            for (String x : list) {
                Log.e("wesker", "name:" + x);
            }
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    Toast.makeText(MainActivity.this, "onLocationChanged:" + location.toString(),Toast.LENGTH_SHORT).show();
                    Log.e("wesker", "onLocationChanged:" + location.toString());
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                    Toast.makeText(MainActivity.this, "onStatusChanged:",Toast.LENGTH_SHORT).show();
                    Log.e("wesker", "onStatusChanged:");
                }

                @Override
                public void onProviderEnabled(String provider) {
                    Toast.makeText(MainActivity.this, "onProviderEnabled:" + provider,Toast.LENGTH_SHORT).show();
                    Log.e("wesker", "onProviderEnabled:" + provider);
                }

                @Override
                public void onProviderDisabled(String provider) {
                    Toast.makeText(MainActivity.this, "onProviderDisabled:" + provider,Toast.LENGTH_SHORT).show();
                    Log.e("wesker", "onProviderDisabled:" + provider);
                }
            });
    }
    private NetWorkChangReceiver netWorkChangReceiver;

    private void registerNetWorkChangReceiver() {
        netWorkChangReceiver = new NetWorkChangReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkChangReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(netWorkChangReceiver);
    }
}
