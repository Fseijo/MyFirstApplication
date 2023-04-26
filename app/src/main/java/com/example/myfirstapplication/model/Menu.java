package com.example.myfirstapplication.model;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Menu implements Parcelable {
    private String name;
    private Float price;
    private String url;
    private Integer totalInCart;


    public Menu() {
    }

    public Menu(String name, Float price, String url, Integer totalInCart) {
        this.name = name;
        this.price = price;
        this.url = url;
        this.totalInCart = totalInCart;
    }

    protected Menu(Parcel in) {
        name = in.readString();
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readFloat();
        }
        url = in.readString();
        if (in.readByte() == 0) {
            totalInCart = null;
        } else {
            totalInCart = in.readInt();
        }
    }

    public static final Creator<Menu> CREATOR = new Creator<Menu>() {
        @Override
        public Menu createFromParcel(Parcel in) {
            return new Menu(in);
        }

        @Override
        public Menu[] newArray(int size) {
            return new Menu[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getTotalInCart() {
        return totalInCart;
    }
    public void setTotalInCart(Integer totalInCart){
        this.totalInCart = totalInCart;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        if (price == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(price);
        }
        dest.writeString(url);
        if (totalInCart == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(totalInCart);
        }
    }
}
