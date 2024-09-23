package com.inventorymanagementsystem.entity;

public class Product {
    private int id;
    private String itemNumber;
    private String CatId;
    private int quantity;
    private double price;

    public Product(int id, String itemNumber, String CatId, int quantity, double price) {
        this.id = id;
        this.itemNumber = itemNumber;
        this.CatId = CatId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getCatId() {
        return CatId;
    }

    public void setCatId(String catId) {
        CatId = catId;
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
}
