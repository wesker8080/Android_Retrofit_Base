package com.elite.etl.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

import com.elite.etl.model.NetWorkEnum;
import com.elite.etl.util.EtLog;

/**
 * 该类用于监听网络状态，包括wifi连接与断开，数据网络的连接与断开
 * 使用时需要动态注册该广播
 *         filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
 *         filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
 *         filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
 * AndroidManifest里配置权限
 *         <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
 *         <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
 * @Author: Wesker
 * @Date: 2019-05-28 10:01
 * @Version: 1.0
 */
public class NetWorkChangReceiver extends BroadcastReceiver {
    private static final String TAG = "NetWorkChangReceiver";
    /**
     * 获取连接类型
     *
     * @param type
     * @return
     */
    private NetWorkEnum getConnectionType(int type) {
        NetWorkEnum connectType = NetWorkEnum.NO_NETWORK;
        if (type == ConnectivityManager.TYPE_MOBILE) {
            connectType = NetWorkEnum.MOBILE;
        } else if (type == ConnectivityManager.TYPE_WIFI) {
            connectType = NetWorkEnum.WIFI;
        }
        return connectType;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // 监听wifi的打开与关闭，与wifi的连接无关
        if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {
            int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
            EtLog.e(TAG, "wifiState:" + wifiState);
            switch (wifiState) {
                case WifiManager.WIFI_STATE_DISABLED:
                    break;
                case WifiManager.WIFI_STATE_DISABLING:
                    break;
                    default:break;
            }
        }
        // 监听网络连接，包括wifi和移动数据的打开和关闭,以及连接上可用的连接都会接到监听
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            //获取联网状态的NetworkInfo对象
            NetworkInfo info = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            if (info != null) {
                //如果当前的网络连接成功并且网络连接可用
                if (NetworkInfo.State.CONNECTED == info.getState() && info.isAvailable()) {
                    if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                        EtLog.i(TAG, getConnectionType(info.getType()) + "连上");
                        // TODO wifi网络连接时，判断是否是工作站wifi，然后执行上传数据到云
                    }
                } else {
                    EtLog.i(TAG, getConnectionType(info.getType()) + "断开");
                    // TODO 网络断开
                }
            }
        }
    }
}
