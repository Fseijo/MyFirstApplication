package com.example.myfirstapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myfirstapplication.adapter.MenuAdapter;
import com.example.myfirstapplication.model.Menu;
import com.example.myfirstapplication.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class MenuMainActivity extends AppCompatActivity implements MenuAdapter.MenuListClickListener {

    private List<Menu> menuList;
    private MenuAdapter menuAdapter;
    private int totalItemInCart;
    private List<Menu> itemsInCartList;
    private RecyclerView recyclerView;
    private TextView checkoutBtn;

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
        recyclerView = findViewById(R.id.menuRecyclerView);
        //get menus
        menuList = restaurant.getMenus();
        //get adapter
        menuAdapter = new MenuAdapter(menuList, this);

        checkoutBtn = findViewById(R.id.checkout_btn);

        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerView.setAdapter(menuAdapter);
    }

    @Override
    public void onAddToCartClick(Menu menu) {
        if (itemsInCartList == null) {
            itemsInCartList = new ArrayList<>();
        }
        itemsInCartList.add(menu);
        totalItemInCart = 0;

        for (Menu m : itemsInCartList) {
            totalItemInCart = totalItemInCart + m.getTotalInCart();
        }
        checkoutBtn.setText("Checkout (" + totalItemInCart + ") items");
    }

    @Override
    public void onUpdateCartClick(Menu menu) {
        if (itemsInCartList.contains(menu)){
            int index = itemsInCartList.indexOf(menu);
            itemsInCartList.remove(index);
            itemsInCartList.add(index, menu);

            totalItemInCart = 0;

            for (Menu m : itemsInCartList) {
                totalItemInCart = totalItemInCart + m.getTotalInCart();
            }
            checkoutBtn.setText("Checkout (" + totalItemInCart + ") items");

        }
    }
}