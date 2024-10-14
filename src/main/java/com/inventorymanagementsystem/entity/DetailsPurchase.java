package com.inventorymanagementsystem.entity;

public class DetailsPurchase {
    private int idProd;
    private String prodName;
    private Double price;
    private int qtyP;
    private Double totalP;

    // Constructor, getters y setters
    public DetailsPurchase(int idProd, String prodName, Double price, int qtyP, Double totalP) {
        this.idProd = idProd;
        this.prodName = prodName;
        this.price = price;
        this.qtyP = qtyP;
        this.totalP = totalP;
    }

    public Integer getIdProd() {
        return idProd;
    }

    public String getProdName() {
        return prodName;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQtyP() {
        return qtyP;
    }

    public void setQtyP(Integer qtyP) {
        this.qtyP = qtyP;
    }

    public Double getTotalP() {
        return totalP;
    }
}
