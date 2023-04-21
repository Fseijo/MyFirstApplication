package com.example.myfirstapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;


import com.example.myfirstapplication.model.Hour;
import com.example.myfirstapplication.model.Menu;
import com.example.myfirstapplication.model.Restaurant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            getRestaurantsList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @NonNull
    static String getJsonFromAssets(@NonNull Context context, String fileName) throws IOException {
        String jsonString;
        InputStream is = context.getAssets().open(fileName);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        jsonString = new String(buffer, "UTF-8");

        return jsonString;
    }

    public List<Restaurant> getRestaurantsList() throws IOException {
        String jsonFileString = MainActivity.getJsonFromAssets(getApplicationContext(), "restaurant.json");
        Gson gson = new Gson();
        Type listRestaurantsType = new TypeToken<List<Restaurant>>() {
        }.getType();

        List<Restaurant> restaurants = new ArrayList<>();
        List<Hour> hours = new ArrayList<>();
        List<Menu> menus = new ArrayList<>();

        List<Restaurant> restaurantsList = gson.fromJson(jsonFileString, listRestaurantsType);
        for (int i = 0; i< restaurantsList.size(); i++) {
            restaurants.add(restaurantsList.get(i));
        }
        return restaurants;
    }


}