package com.example.myfirstapplication.model;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class Restaurant implements Parcelable {
    private String name;
    private String address;
    private Float delivery_charge;
    private String image;
    private Hour hours;
    private List<Menu> menus;


    public Restaurant() {
    }

    public Restaurant(String name, String address, Float delivery_charge, String image, Hour hours, List<Menu> menus) {
        this.name = name;
        this.address = address;
        this.delivery_charge = delivery_charge;
        this.image = image;
        this.hours = hours;
        this.menus = menus;
    }

    protected Restaurant(Parcel in) {
        name = in.readString();
        address = in.readString();
        if (in.readByte() == 0) {
            delivery_charge = null;
        } else {
            delivery_charge = in.readFloat();
        }
        image = in.readString();
        menus = in.createTypedArrayList(Menu.CREATOR);
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getDelivery_charge() {
        return delivery_charge;
    }

    public void setDelivery_charge(Float delivery_charge) {
        this.delivery_charge = delivery_charge;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Hour getHours() {
        return hours;
    }

    public void setHours(Hour hours) {
        this.hours = hours;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        if (delivery_charge == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(delivery_charge);
        }
        dest.writeString(image);
        dest.writeTypedList(menus);
    }
}
