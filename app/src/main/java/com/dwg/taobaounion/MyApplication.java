package com.dwg.taobaounion;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();
    }

    public Context getContext() {
        return context;
    }
}
