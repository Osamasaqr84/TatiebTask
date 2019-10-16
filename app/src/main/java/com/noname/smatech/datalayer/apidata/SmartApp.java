package com.noname.smatech.datalayer.apidata;

import android.content.Context;
import android.support.multidex.MultiDexApplication;



public class SmartApp extends MultiDexApplication {
    private static SmartApp instance;
    private ServerGateway apiInterface;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ApplicationComponent applicationComponent = DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
        apiInterface = applicationComponent.getApplicationService();
    }

    public static Context getAppContext() {
        return instance;
    }

    public static SmartApp getAppInstance() {
        return instance;
    }

    public ServerGateway getApiInterface() {
        return apiInterface;
    }




}
