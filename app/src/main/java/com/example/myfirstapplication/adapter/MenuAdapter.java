package com.example.myfirstapplication.adapter;

import android.text.Layout;
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

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    List<Menu> menuList;
    private MenuListClickListener clickListener;

    public MenuAdapter(List<Menu> menuList) {
        this.menuList = menuList;
    }

    @NonNull
    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_recycle_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.ViewHolder holder, int position) {
        holder.display(menuList.get(position));

    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public interface MenuListClickListener {
        void onItemClicked(Menu menu);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView menuImage, minus, plus;
        private TextView menuName, menuPrice, cart;
        private TextView menuBtn;
        private ImageView layout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            menuImage = itemView.findViewById(R.id.menu_img);
            menuName = itemView.findViewById(R.id.menu_name);
            menuPrice = itemView.findViewById(R.id.menu_price);
            menuBtn = itemView.findViewById(R.id.menu_btn);
            minus = itemView.findViewById(R.id.minus);
            plus = itemView.findViewById(R.id.plus);
            cart = itemView.findViewById(R.id.cart);
            layout = itemView.findViewById(R.id.addMoreLayout);
        }

        void display(Menu menu) {
            Glide.with(menuImage)
                    .load(menu.getUrl())
                    .into(menuImage);
            menuName.setText(menu.getName());
            menuPrice.setText("Price: " + menu.getPrice() + "€");
            menuBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Menu menu = menuList.get(getAdapterPosition());
                    menu.setTotalInCart(1);
                    clickListener.onItemClicked(menu);
                    layout.setVisibility(View.VISIBLE);
                    menuBtn.setVisibility(View.GONE);
                    cart.setText(menu.getTotalInCart() + "");
                }
            });
            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Menu menu = menuList.get(getAdapterPosition());
                    int total = menu.getTotalInCart();
                    total--;
                    if (total>0){
                        menu.setTotalInCart(total);
                        clickListener.onItemClicked(menu);
                        cart.setText(total + "");
                    }else{
                        layout.setVisibility(View.GONE);
                        menuBtn.setVisibility(View.VISIBLE);
                        menu.setTotalInCart(total);
                    }
                }
            });
        }
    }
}
