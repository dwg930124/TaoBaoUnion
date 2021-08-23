package com.dwg.taobaounion.utils;

import android.util.Log;

public class LogUtils {

    private static final int CURRENT_LEVEL = 4;
    private static final int DEBUG_LEVEL = 4;
    private static final int INFO_LEVEL = 3;
    private static final int WARNING_LEVEL = 2;
    private static final int ERROR_LEVEL = 1;

    public static void d(Object object, String msg) {
        if (CURRENT_LEVEL >= DEBUG_LEVEL) {
            Log.d(object.getClass().getSimpleName(), msg);
        }
    }

    public static void i(Object object, String msg) {
        if (CURRENT_LEVEL >= INFO_LEVEL) {
            Log.i(object.getClass().getSimpleName(), msg);
        }
    }

    public static void w(Object object, String msg) {
        if (CURRENT_LEVEL >= WARNING_LEVEL) {
            Log.w(object.getClass().getSimpleName(), msg);
        }
    }

    public static void e(Object object, String msg) {
        if (CURRENT_LEVEL >= ERROR_LEVEL) {
            Log.e(object.getClass().getSimpleName(), msg);
        }
    }
}
