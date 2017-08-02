package com.example.vvk.testrxdaggermvp.presenters;

import com.example.vvk.testrxdaggermvp.models.Car;
import com.example.vvk.testrxdaggermvp.models.MainModel;
import com.example.vvk.testrxdaggermvp.views.CarsView;

import java.util.List;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;


public class MainPresenter implements CarsPresenter {

    private final MainModel model;
    private final CarsView view;
    private CompositeSubscription subscriptions;


    public MainPresenter(MainModel model, CarsView view) {
        this.model = model;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getCityList(String searchString, Boolean isSort) {

        Subscription subscription = model.getCars(new MainModel.GetCarsListCallback() {
            @Override
            public void onSuccess(List<Car> cars) {
                setCars(cars);
            }
        }, searchString, isSort);

        subscriptions.add(subscription);
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }

    @Override
    public void setCars(List<Car> cars) {
        view.showCars(cars);
    }
}
