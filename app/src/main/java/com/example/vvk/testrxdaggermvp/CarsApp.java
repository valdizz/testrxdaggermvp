package com.example.vvk.testrxdaggermvp;

import android.app.Application;
import android.content.Context;

import com.example.vvk.testrxdaggermvp.db.DBModule;


public class CarsApp extends Application {

    private volatile AppComponent appComponent;

    public static CarsApp get(Context context) {
        return (CarsApp) context.getApplicationContext();
    }

    public AppComponent appComponent() {
        if (appComponent == null) {
            synchronized (CarsApp.class) {
                if (appComponent == null) {
                    appComponent = createAppComponent();
                }
            }
        }

        return appComponent;
    }

    private AppComponent createAppComponent() {
        return DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .dBModule(new DBModule())
                .build();
    }

}
