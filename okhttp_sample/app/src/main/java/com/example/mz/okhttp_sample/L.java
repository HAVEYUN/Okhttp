package com.example.mz.okhttp_sample;

import android.util.Log;

/**
 * Created by MZ on 2017/3/8.
 */

public class L {

    private static final String TAG = "okhttp_sample";
    private static boolean debug = true;//debug


    public static void e(String msg) {
        if (debug)
            Log.e(TAG, msg);

    }
}
