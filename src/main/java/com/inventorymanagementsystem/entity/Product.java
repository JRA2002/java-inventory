package com.inventorymanagementsystem.entity;

import java.time.LocalDate;

public class Product {
    private int id;
    private String name;
    private String unit;
    private int quantity;
    private double price;
    private LocalDate exp_date;

    public Product(int id, String name, String CatId, int quantity, double price, LocalDate exp_date) {
        this.id = id;
        this.name = name;
        this.unit = CatId;
        this.quantity = quantity;
        this.price = price;
        this.exp_date = exp_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getExp_date() {
        return exp_date;
    }

    public void setExp_date(LocalDate exp_date) {
        this.exp_date = exp_date;
    }
}
