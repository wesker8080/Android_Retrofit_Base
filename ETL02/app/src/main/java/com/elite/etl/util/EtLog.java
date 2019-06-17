package com.elite.etl.util;

import android.util.Log;

/**
 * @Author: Wesker
 * @Date: 2019-05-25 10:45
 * @Version: 1.0
 */
public class EtLog {
    public static final String TAG = "ETL02--->";
    private static final boolean DEBUG = true;
    public static void d(String className, String info) {
        if (DEBUG) {
            Log.d(TAG + className, info);
        }
    }
    public static void e(String className, String info) {
        if (DEBUG) {
            Log.e(TAG + className, info);
        }
    }
    public static void i(String className, String info) {
        if (DEBUG) {
            Log.i(TAG + className, info);
        }
    }
    public static void w(String className, String info) {
        if (DEBUG) {
            Log.w(TAG + className, info);
        }
    }
    public static void v(String className, String info) {
        if (DEBUG) {
            Log.v(TAG + className, info);
        }
    }
}
