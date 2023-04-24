package com.example.myfirstapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.example.myfirstapplication.model.Restaurant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle("Restaurant List");

        try {
           List<Restaurant> restaurantList = getRestaurantsList();
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
        Type listRestaurantsType = new TypeToken<Restaurant[]>() {}.getType();

        Restaurant[] restaurantsList = gson.fromJson(jsonFileString, listRestaurantsType);
        List<Restaurant> restaurants = Arrays.asList(restaurantsList);
        return restaurants;
    }
}