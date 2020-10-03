package com.example.foodieapp.model;

public class Pizza {
    private String key;
    private String name;
    private String price;
    private String size;
    private String url;

    public Pizza(String name, String price, String size, String url) {
        this.name = name;
        this.price = price;
        this.size = size;
        this.url = url;
    }

    public Pizza() { }

    public Pizza(String trim, String toString, String toString1) {
        this.name = trim;
        this.price = toString;
        this.size = toString1;
    }

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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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
                ", size='" + size + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
