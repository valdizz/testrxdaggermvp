package com.example.vvk.testrxdaggermvp.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.vvk.testrxdaggermvp.CarsApp;
import com.example.vvk.testrxdaggermvp.R;
import com.example.vvk.testrxdaggermvp.models.Car;
import com.example.vvk.testrxdaggermvp.models.MainModel;
import com.example.vvk.testrxdaggermvp.presenters.MainPresenter;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements CarsView {

    static final String SEARCHSTATE = "searchState";
    static final String SORTSTATE = "sortState";

    @Inject
    StorIOSQLite storIOSQLite;
    RecyclerView recyclerView;
    MainPresenter presenter;
    MainAdapter mainAdapter;
    Boolean isSort = false;
    String searchText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CarsApp.get(MainActivity.this).appComponent().inject(this);
        setContentView(R.layout.activity_main);

        mainAdapter = new MainAdapter();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mainAdapter);

        presenter = new MainPresenter(new MainModel(storIOSQLite), this);
        searchCars(searchText, isSort);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        MenuItem search_item = menu.findItem(R.id.menu_search);
        MenuItem sort_item = menu.findItem(R.id.menu_sort);
        SearchView searchView = (SearchView) search_item.getActionView();
        if (searchText!=null && searchText.length()>0){
            search_item.expandActionView();
            searchView.setQuery(searchText, true);
            searchView.clearFocus();
        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchText = newText;
                searchCars(searchText, isSort);
                return true;
            }
        });

        sort_item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                isSort = !isSort;
                searchCars(searchText, isSort);
                return true;
            }
        });

        return true;
    }

    @Override
    public void searchCars(String searchText, Boolean isSort) {
        presenter.getCityList(searchText, isSort);
    }

    @Override
    public void showCars(List<Car> cars) {
        mainAdapter.setCars(cars);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SEARCHSTATE, searchText);
        outState.putBoolean(SORTSTATE, isSort);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState!=null){
            searchText = savedInstanceState.getString(SEARCHSTATE);
            isSort = savedInstanceState.getBoolean(SORTSTATE);
            searchCars(searchText, isSort);
        }
    }
}
