package com.elite.etl.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

import java.util.Optional;

/**
 * @Author: Wesker
 * @Date: 2019-05-28 10:55
 * @Version: 1.0
 */
public class NetWorkUtil {
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return Optional.ofNullable(connectivityManager)
                .map(ConnectivityManager::getActiveNetworkInfo)
                .map(x -> x.getType() == ConnectivityManager.TYPE_WIFI)
                .orElse(false);
    }
    public static boolean isMobileConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return Optional.ofNullable(connectivityManager)
                .map(ConnectivityManager::getActiveNetworkInfo)
                .map(x -> x.getType() == ConnectivityManager.TYPE_MOBILE)
                .orElse(false);
    }
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Network[] networks = Optional.ofNullable(connectivityManager)
                .map(ConnectivityManager::getAllNetworks)
                .orElse(null);
        if (networks == null) {
            return false;
        }
        NetworkInfo networkInfo;
        for (Network mNetwork : networks) {
            networkInfo = connectivityManager.getNetworkInfo(mNetwork);
            if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param context Context
     * @return -1 无网络 0 数据网络 1 wifi网络
     */
    public static int getAPNType(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return Optional.ofNullable(connectivityManager)
                .map(ConnectivityManager::getActiveNetworkInfo)
                .map(NetworkInfo::getType)
                .orElse(-1);
    }
}
