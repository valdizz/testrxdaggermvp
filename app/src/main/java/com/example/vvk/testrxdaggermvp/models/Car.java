package com.example.vvk.testrxdaggermvp.models;

import com.example.vvk.testrxdaggermvp.db.DBOpenHelper;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;


@StorIOSQLiteType(table = DBOpenHelper.TABLE)
public class Car {

    @StorIOSQLiteColumn(name = DBOpenHelper.ID, key = true)
    public Long id;
    @StorIOSQLiteColumn(name = DBOpenHelper.BRAND)
    public String brand;
    @StorIOSQLiteColumn(name = DBOpenHelper.MODEL)
    public String model;
    @StorIOSQLiteColumn(name = DBOpenHelper.ENGINE)
    public String engine;
    @StorIOSQLiteColumn(name = DBOpenHelper.COLOR)
    public String color;

    Car() {
    }

    public Car(Long id, String brand, String model, String engine, String color) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.engine = engine;
        this.color = color;
    }

    public Long id() {
        return id;
    }

    public String brand() {
        return brand;
    }

    public String model() {
        return model;
    }

    public String engine() {
        return engine;
    }

    public String color() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (id != null ? !id.equals(car.id) : car.id != null) return false;
        if (brand != null ? !brand.equals(car.brand) : car.brand != null) return false;
        if (model != null ? !model.equals(car.model) : car.model != null) return false;
        if (engine != null ? !engine.equals(car.engine) : car.engine != null) return false;
        return color != null ? color.equals(car.color) : car.color == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (engine != null ? engine.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", engine='" + engine + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
