package com.example.myfirstapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.myfirstapplication.adapter.MenuAdapter;
import com.example.myfirstapplication.model.Menu;
import com.example.myfirstapplication.model.Restaurant;

import java.util.List;

public class RestaurantMenuMainActivity extends AppCompatActivity {

    private List<Menu> menuList;
    private MenuAdapter menuAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_menu);
        Restaurant restaurant = getIntent().getParcelableExtra("Restaurant");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(restaurant.getName());
        actionBar.setSubtitle(restaurant.getAddress());

        //get recycler view
        RecyclerView recyclerView =findViewById(R.id.menuRecyclerView);
        //get menus
        menuList = restaurant.getMenus();
        //get adapter
        menuAdapter = new MenuAdapter(menuList);

        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerView.setAdapter(menuAdapter);
    }
}