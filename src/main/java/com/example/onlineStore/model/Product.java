package com.example.onlineStore.model;

import java.math.BigDecimal; // Para manejar decimales con precisión

public class Product {
    private String idProduct;
    private String nameProduct;
    private String description;
    private BigDecimal price;
    private int stock;        // Cantidad disponible en inventario
    private Discount discount;


    // Constructor
    public Product(String idProduct, String nameProduct, String description, BigDecimal price, int stock) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    // Getters y Setters
    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    public BigDecimal getPriceWithDiscount() {
        if (discount != null && discount.isActive()) {
            return price.multiply(BigDecimal.ONE.subtract(discount.getPercentage()));
        }
        return price;
    }

}