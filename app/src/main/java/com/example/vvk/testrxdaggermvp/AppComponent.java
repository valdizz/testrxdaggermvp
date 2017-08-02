package com.example.vvk.testrxdaggermvp;

import com.example.vvk.testrxdaggermvp.db.DBModule;
import com.example.vvk.testrxdaggermvp.views.MainActivity;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {AppModule.class, DBModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);

}
