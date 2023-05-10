package com.example.myfirstapplication.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myfirstapplication.R;
import com.example.myfirstapplication.model.Menu;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private List<Menu> menuList;
    private MenuListClickListener clickListener;

    public MenuAdapter(List<Menu> menuList, MenuListClickListener clickListener) {
        this.menuList = menuList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_recycle_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        holder.display(menuList.get(position));
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public interface MenuListClickListener {
        void onAddToCartClick(Menu menu);

        void onUpdateCartClick(Menu menu);
        void onRemoveFromCartClick(Menu menu);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView menuImage, minusBtn, plusBtn;
        private TextView menuName, menuPrice, cartTxt, addToCartBtn;
        private LinearLayout addMoreLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            menuImage = itemView.findViewById(R.id.cart_menu_img);
            menuName = itemView.findViewById(R.id.cart_menu_name);
            menuPrice = itemView.findViewById(R.id.cart_menu_price);
            addToCartBtn = itemView.findViewById(R.id.addToCartBtn);
            minusBtn = itemView.findViewById(R.id.minus);
            plusBtn = itemView.findViewById(R.id.plus);
            cartTxt = itemView.findViewById(R.id.cartTxt);
            addMoreLayout = itemView.findViewById(R.id.addMoreLayout);
        }

        void display(Menu menu) {
            Glide.with(menuImage)
                    .load(menu.getUrl())
                    .into(menuImage);
            menuName.setText(menu.getName());
            menuPrice.setText("Price: €" + menu.getPrice());
            cartTxt.setText(String.valueOf(menu.getTotalInCart()));

            //hide button
            addToCartBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Menu menu = menuList.get(getAdapterPosition());
                    menu.setTotalInCart(1);
                    clickListener.onAddToCartClick(menu);
                    addToCartBtn.setVisibility(View.GONE);
                    addMoreLayout.setVisibility(View.VISIBLE);
                    cartTxt.setText(menu.getTotalInCart().toString());
                }
            });

            //action on click in button plus
            plusBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Menu menu = menuList.get(getAdapterPosition());
                    int cart = menu.getTotalInCart();
                    cart++;
                    menu.setTotalInCart(cart);
                    clickListener.onUpdateCartClick(menu);
                    cartTxt.setText(String.valueOf(cart));
                }
            });
            minusBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Menu menu = menuList.get(getAdapterPosition());
                    int cart = menu.getTotalInCart();
                    cart--;
                    if (cart > 0) {
                        menu.setTotalInCart(cart);
                        clickListener.onUpdateCartClick(menu);
                        cartTxt.setText(String.valueOf(cart));
                    } else {
                        addToCartBtn.setVisibility(View.VISIBLE);
                        addMoreLayout.setVisibility(View.GONE);
                        menu.setTotalInCart(cart);
                        clickListener.onRemoveFromCartClick(menu);
                    }
                }
            });
        }
    }
}
