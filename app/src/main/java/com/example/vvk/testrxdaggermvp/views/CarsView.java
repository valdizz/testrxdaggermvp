package com.example.vvk.testrxdaggermvp.views;

import com.example.vvk.testrxdaggermvp.models.Car;

import java.util.List;


public interface CarsView {

    void searchCars(String searchText, Boolean isSort);

    void showCars(List<Car> cars);

}
