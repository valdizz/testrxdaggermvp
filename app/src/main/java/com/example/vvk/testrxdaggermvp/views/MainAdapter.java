package com.example.vvk.testrxdaggermvp.views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vvk.testrxdaggermvp.R;
import com.example.vvk.testrxdaggermvp.models.Car;

import java.util.List;


public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<Car> cars;

    public void setCars(List<Car> cars) {
        this.cars = cars;
        this.notifyDataSetChanged();
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder holder, int position) {
        final Car car = cars.get(position);
        holder.brandTV.setText(car.brand());
        holder.modelTV.setText(car.model());
        holder.engineTV.setText(car.engine());
        holder.colorTV.setText(car.color());
    }

    @Override
    public int getItemCount() {
        return cars == null ? 0 : cars.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView brandTV, modelTV, engineTV, colorTV;
        ViewHolder(View view){
            super(view);
            brandTV = (TextView) view.findViewById(R.id.brand);
            modelTV = (TextView) view.findViewById(R.id.model);
            engineTV = (TextView) view.findViewById(R.id.engine);
            colorTV = (TextView) view.findViewById(R.id.color);
        }
    }
}
