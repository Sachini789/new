package com.example.foodieapp.model;

public class Burger {
    private String key;
    private String name;
    private String price;
    private String url;

    public Burger(String name, String price, String url) {
        this.name = name;
        this.price = price;
        this.url = url;
    }

    public Burger() { }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
