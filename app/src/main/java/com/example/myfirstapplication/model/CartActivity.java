package com.example.myfirstapplication.model;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
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
    private TextView tvSubTotalAmount, tvDeliveryChargeAmount, tvDeliveryCharge, tvTotalAmount, orderBtn;
    private SwitchCompat switchDelivery;
    private boolean isDeliveryOn;
    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Restaurant restaurant = getIntent().getParcelableExtra("Restaurant");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(restaurant.getName());
        actionBar.setSubtitle(restaurant.getAddress());

        //get Views
        inputName = findViewById(R.id.label_name);
        inputAddress = findViewById(R.id.label_address);
        inputCity = findViewById(R.id.label_city);
        inputState = findViewById(R.id.label_state);
        inputZip = findViewById(R.id.label_zip);
        inputCard = findViewById(R.id.label_card);
        inputExpiryDate = findViewById(R.id.expiryDate);
        inputCVV = findViewById(R.id.cv_number);
        tvSubTotalAmount = findViewById(R.id.sub_total_currency);
        tvDeliveryChargeAmount = findViewById(R.id.delivery_currency);
        tvDeliveryCharge = findViewById(R.id.delivery_charge);
        tvTotalAmount = findViewById(R.id.total_currency);
        orderBtn = findViewById(R.id.cart_btn);
        switchDelivery = findViewById(R.id.switchCompat);

        //get recycler view
        recyclerView = findViewById(R.id.cartRecyclerView);
        //button event
        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOrderBtnClick(restaurant);
            }
        });
        //switch event

        switchDelivery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    inputAddress.setVisibility(View.VISIBLE);
                    inputCity.setVisibility(View.VISIBLE);
                    inputState.setVisibility(View.VISIBLE);
                    inputZip.setVisibility(View.VISIBLE);
                    tvDeliveryChargeAmount.setVisibility(View.VISIBLE);
                    tvDeliveryCharge.setVisibility(View.VISIBLE);
                    isDeliveryOn = true;
                    calculateTotalAmount(restaurant);
                } else {
                    inputAddress.setVisibility(View.GONE);
                    inputCity.setVisibility(View.GONE);
                    inputState.setVisibility(View.GONE);
                    inputZip.setVisibility(View.GONE);
                    tvDeliveryChargeAmount.setVisibility(View.GONE);
                    tvDeliveryCharge.setVisibility(View.GONE);
                    isDeliveryOn = false;
                    calculateTotalAmount(restaurant);
                }
            }
        });
        initRecyclerView(restaurant);
        calculateTotalAmount(restaurant);
    }

    private void initRecyclerView(Restaurant restaurant) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        cartAdapter = new CartAdapter(restaurant.getMenus());
        recyclerView.setAdapter(cartAdapter);
    }

    @SuppressLint("DefaultLocale")
    private void calculateTotalAmount(Restaurant restaurant) {
        float subTotalAmount = 0f;

        for (Menu m : restaurant.getMenus()){
            subTotalAmount += m.getPrice() * m.getTotalInCart();
        }
        tvSubTotalAmount.setText("€"+ String.format("%.2f", subTotalAmount));
        if (isDeliveryOn){
            tvDeliveryChargeAmount.setText("€", TextView.BufferType.valueOf(String.format("%.2f", restaurant.getDelivery_charge())));
            subTotalAmount += restaurant.getDelivery_charge();
        }
        tvTotalAmount.setText("€" + subTotalAmount);
    }


    private void onOrderBtnClick(Restaurant restaurant) {

    }
}