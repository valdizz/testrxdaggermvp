package com.example.vvk.testrxdaggermvp;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {

    private final CarsApp app;

    AppModule(CarsApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return app;
    }
}
