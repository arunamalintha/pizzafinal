package com.example.pizzafinal;

public class Product {
    private  int id;
    private  String title,shortdesc;
    private  String image;
    private String getName, getDiscription;
    private double price;


    public Product(int id, String title, String shortdesc, double v, double price, String image) {
        this.id = id;
        this.title = title;
        this.shortdesc = shortdesc;
        this.price = this.price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getShortdesc() { return shortdesc; }

    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }
}
