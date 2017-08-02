package com.example.vvk.testrxdaggermvp.db;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.vvk.testrxdaggermvp.models.Car;
import com.example.vvk.testrxdaggermvp.models.CarSQLiteTypeMapping;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.impl.DefaultStorIOSQLite;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class DBModule {

    @Provides
    @Singleton
    public StorIOSQLite provideStorIOSQLite(@NonNull SQLiteOpenHelper sqLiteOpenHelper) {
        return DefaultStorIOSQLite.builder()
                .sqliteOpenHelper(sqLiteOpenHelper)
                .addTypeMapping(Car.class, new CarSQLiteTypeMapping())
                .build();
    }

    @Provides
    @Singleton
    public SQLiteOpenHelper provideSQLiteOpenHelper(Context context) {
        return new DBOpenHelper(context);
    }

}
