package com.example.myfirstapplication.model;


import java.util.List;

public class Restaurant {
    private String name;
    private String address;
    private Float delivery_charge;
    private String image;
    private List<Hour> hours;
    private List<Menu> menus;


    public Restaurant() {
    }

    public Restaurant(String name, String address, Float delivery_charge, String image, List<Hour> hours, List<Menu> menus) {
        this.name = name;
        this.address = address;
        this.delivery_charge = delivery_charge;
        this.image = image;
        this.hours = hours;
        this.menus = menus;
    }

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

    public List<Hour> getHours() {
        return hours;
    }

    public void setHours(List<Hour> hours) {
        this.hours = hours;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
