package com.elite.etl.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.os.RemoteException;

import com.elite.etl.receiver.NetWorkChangReceiver;

/**
 *
 * @author wesker
 * @date 2018/6/14 11:34
 */

public class MainService extends Service {

    private final String PROTECT_SERVICE = "com.elite.etl.service.ProtectService";
    private NetWorkChangReceiver netWorkChangReceiver;
    @Override
    public void onCreate() {
        startProtectService();
        registerNetWorkChangReceiver();
    }
    private void registerNetWorkChangReceiver() {
        netWorkChangReceiver = new NetWorkChangReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkChangReceiver, filter);
    }
    /**
     * 在内存紧张的时候，系统回收内存时，会回调OnTrimMemory
     * 重写onTrimMemory当系统清理内存时从新启动ProtectionService
     */
    @Override
    public void onTrimMemory(int level) {
        startProtectService();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        startProtectService();
        unregisterReceiver(netWorkChangReceiver);
    }

    private void startProtectService() {
        boolean isRun = ServiceUtil.isServiceWork(MainService.this,
                PROTECT_SERVICE);
        if (!isRun) {
            try {
                protectionService.startService();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return (IBinder) protectionService;
    }
    private ProtectionService protectionService = new ProtectionService.Stub() {
        @Override
        public void stopService() {
            Intent intent = new Intent(getBaseContext(), ProtectService.class);
            getBaseContext().stopService(intent);
        }

        @Override
        public void startService() {
            Intent intent = new Intent(getBaseContext(), ProtectService.class);
            getBaseContext().startService(intent);
        }
    };
}
