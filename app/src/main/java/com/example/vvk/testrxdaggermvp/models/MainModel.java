package com.example.vvk.testrxdaggermvp.models;

import com.example.vvk.testrxdaggermvp.db.DBOpenHelper;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.queries.RawQuery;

import java.util.List;

import rx.Subscription;
import rx.functions.Action1;

import static rx.android.schedulers.AndroidSchedulers.mainThread;


public class MainModel {

    private StorIOSQLite storIOSQLite;

    public MainModel(StorIOSQLite storIOSQLite) {
        this.storIOSQLite = storIOSQLite;
    }

    public Subscription getCars(final GetCarsListCallback callback, String searchText, Boolean sortAB){

        StringBuilder query = new StringBuilder("SELECT * FROM " + DBOpenHelper.TABLE);
        if (searchText!=null && !searchText.isEmpty()) {
            query.append(" WHERE " + DBOpenHelper.BRAND + " LIKE '%"+searchText+"%'");
        }
        if (sortAB) {
            query.append(" ORDER BY " + DBOpenHelper.BRAND);
        }

        return storIOSQLite
                .get()
                .listOfObjects(Car.class)
                .withQuery(RawQuery.builder().query(query.toString()).build())
                .prepare()
                .asRxObservable()
                .observeOn(mainThread())
                .subscribe(new Action1<List<Car>>() {
                    @Override
                    public void call(List<Car> cars) {
                        callback.onSuccess(cars);
                    }
                });
    }

    public interface GetCarsListCallback{
        void onSuccess(List<Car> cars);
    }
}
