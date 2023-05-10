package com.example.myfirstapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myfirstapplication.R;
import com.example.myfirstapplication.model.Menu;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private List<Menu> menuList;

    public CartAdapter(List<Menu> menuList) {
        this.menuList = menuList;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_recycle_row,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        holder.orderMenuName.setText(menuList.get(position).getName());
        holder.orderMenuPrice.setText("Price: €" + menuList.get(position).getPrice() * menuList.get(position).getTotalInCart());
        holder.orderMenuQuantity.setText("Qty: " + TextView.BufferType.valueOf(menuList.get(position).getTotalInCart().toString()));
        Glide.with(holder.orderMenuImg)
                .load(menuList.get(position).getUrl())
                .into(holder.orderMenuImg);
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView orderMenuImg;
        private TextView orderMenuName, orderMenuPrice, orderMenuQuantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderMenuImg = itemView.findViewById(R.id.cart_img);
            orderMenuName = itemView.findViewById(R.id.cart_name);
            orderMenuPrice = itemView.findViewById(R.id.cart_price);
            orderMenuQuantity = itemView.findViewById(R.id.cart_qty);
        }
    }
}
