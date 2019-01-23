package com.bruce.pc;

import android.app.Application;

import com.bruce.pc.core.AppComponent;
import com.bruce.pc.core.DaggerAppComponent;
import com.bruce.pc.data.AppModule;

public class MyApp extends Application {
    AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder().appModule(new AppModule()).build();
    }

    public AppComponent appComponent() {
        return component;
    }
}
