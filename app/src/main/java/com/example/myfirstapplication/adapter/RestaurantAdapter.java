package com.example.myfirstapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapplication.R;
import com.example.myfirstapplication.model.Restaurant;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {
    List<Restaurant> restaurantList;

    public RestaurantAdapter(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.restaurant_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.display(restaurantList.get(position));
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView restImage;
        private TextView restName;
        private TextView restAddress;
        private TextView restHours;

        ViewHolder(View itemView){
            super(itemView);
            restImage = itemView.findViewById(R.id.rest_img);
            restName = itemView.findViewById(R.id.rest_name);
            restAddress = itemView.findViewById(R.id.rest_address);
            restHours = itemView.findViewById(R.id.rest_hours);
        }
        void display(Restaurant restaurant){
            restName.setText(restaurant.getName());
            restAddress.setText(restaurant.getAddress());
        }
    }

}
