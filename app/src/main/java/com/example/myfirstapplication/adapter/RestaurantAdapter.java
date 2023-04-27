package com.example.myfirstapplication.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myfirstapplication.R;
import com.example.myfirstapplication.model.Restaurant;


import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {
    private List<Restaurant> restaurantList;
    private RestaurantListClickListener clickListener;

    public RestaurantAdapter(List<Restaurant> restaurantList, RestaurantListClickListener clickListener) {
        this.restaurantList = restaurantList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.display(restaurantList.get(position));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClicked(restaurantList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public interface RestaurantListClickListener {
        void onItemClicked(Restaurant restaurant);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView restImage;
        private TextView restName;
        private TextView restAddress;
        private TextView restHours;
        private CardView cardView;
        ViewHolder(View itemView) {
            super(itemView);
            restImage = itemView.findViewById(R.id.rest_img);
            restName = itemView.findViewById(R.id.rest_name);
            restAddress = itemView.findViewById(R.id.rest_address);
            restHours = itemView.findViewById(R.id.rest_hours);
            cardView = itemView.findViewById(R.id.restCardView);
        }

        void display(@NonNull Restaurant restaurant) {
            Glide.with(restImage)
                    .load(restaurant.getImage())
                    .into(restImage);
            restName.setText(restaurant.getName());
            restAddress.setText("Address: " + restaurant.getAddress());
            restHours.setText(" Today's Hours: " + restaurant.getHours().getTodaysHours());
        }
    }

}
