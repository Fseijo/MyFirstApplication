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

    List<Menu> menuList;

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
        holder.display(menuList.get(position));
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView menuImg;
        private TextView menuName, menuPrice, menuQuantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            menuImg = itemView.findViewById(R.id.cart_img);
            menuName = itemView.findViewById(R.id.cart_name);
            menuPrice = itemView.findViewById(R.id.cart_price);
            menuQuantity = itemView.findViewById(R.id.cart_qty);
        }

        void display(Menu menu){
            Glide.with(menuImg)
                    .load(menu.getUrl())
                    .into(menuImg);
            menuName.setText(menu.getName());
            menuPrice.setText("Price: €" + menu.getPrice().toString());
            menuQuantity.setText(menu.getTotalInCart().toString());
        }
    }
}
