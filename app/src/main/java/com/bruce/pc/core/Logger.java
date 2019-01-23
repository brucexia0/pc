package com.bruce.pc.core;

import android.util.Log;

public class Logger {
    public void debug(String message, Throwable throwable) {
        Log.d("flickrDebug", message, throwable);
    }
}
