package com.example.myfirstapplication.model;


public class Menu {
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
}
