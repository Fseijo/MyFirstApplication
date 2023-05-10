package com.example.myfirstapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirstapplication.adapter.MenuAdapter;
import com.example.myfirstapplication.model.CartActivity;
import com.example.myfirstapplication.model.Menu;
import com.example.myfirstapplication.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class MenuMainActivity extends AppCompatActivity implements MenuAdapter.MenuListClickListener {

    private List<Menu> menuList;
    private MenuAdapter menuAdapter;
    private int totalItemInCart = 0;
    private List<Menu> itemsInCartList = new ArrayList<>();
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
        //disposition of recycler view on grid
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        //set adapter
        recyclerView.setAdapter(menuAdapter);
        //get button checkout
        checkoutBtn = findViewById(R.id.checkout_btn);
        //action of checkout button
        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemsInCartList != null && itemsInCartList.size() == 0){
                    Toast.makeText(MenuMainActivity.this, "Your cart is empty! Please add some items ;)", Toast.LENGTH_SHORT).show();
                    return;
                }
                restaurant.setMenus(itemsInCartList);
                Intent intent = new Intent(MenuMainActivity.this, CartActivity.class);
                intent.putExtra("Restaurant", restaurant);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("SetTextI18n")
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

    @SuppressLint("SetTextI18n")
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

    @Override
    public void onRemoveFromCartClick(Menu menu) {
        if (itemsInCartList.contains(menu)){
            itemsInCartList.remove(menu);
            totalItemInCart = 0;

            for (Menu m : itemsInCartList){
                totalItemInCart = totalItemInCart + m.getTotalInCart();
            }
            if(totalItemInCart > 0){
            checkoutBtn.setText("Checkout (" + totalItemInCart + ") items");
            }else{
            checkoutBtn.setText("Checkout");
            }
        }
    }
}