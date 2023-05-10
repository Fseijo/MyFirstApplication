package com.example.myfirstapplication.model;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myfirstapplication.R;
import com.example.myfirstapplication.adapter.CartAdapter;
import com.example.myfirstapplication.adapter.MenuAdapter;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    private TextInputLayout inputName, inputAddress, inputCity, inputState, inputZip, inputCard;
    private EditText inputExpiryDate, inputCVV;
    private TextView tvSubTotal,tvDeliveryChargeAmount,tvDeliveryCharge, tvTotalAmount, orderBtn;
    private SwitchCompat switchDelivery;
    private boolean isDeliveryOn;
    private List<Menu> menuList;
    private CartAdapter cartAdapter;
    private RecyclerView recyclerView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Restaurant restaurant = getIntent().getParcelableExtra("Restaurant");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(restaurant.getName());
        actionBar.setSubtitle(restaurant.getAddress());

        //Views
        inputName = findViewById(R.id.label_name);
        inputAddress = findViewById(R.id.label_address);
        inputCity = findViewById(R.id.label_city);
        inputState = findViewById(R.id.label_state);
        inputZip = findViewById(R.id.label_zip);
        inputCard = findViewById(R.id.label_card);
        inputExpiryDate = findViewById(R.id.expiryDate);
        inputCVV = findViewById(R.id.cv_number);
        tvSubTotal = findViewById(R.id.sub_total_currency);


        //get recycler view
        recyclerView = findViewById(R.id.cartRecyclerView);
        //get menus
        menuList = restaurant.getMenus();
        //get adapter
        cartAdapter = new CartAdapter(menuList);
        //disposition of recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(cartAdapter);
    }
}