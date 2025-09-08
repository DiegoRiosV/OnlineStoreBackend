package com.example.onlineStore.model;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String nameCategory;
    private List<Product> listProducts;
    private String description;

    // Constructor
    public Category(String nameCategory, String description) {
        this.nameCategory = nameCategory;
        this.description = description;
        this.listProducts = new ArrayList<>();
    }

    // Getters y Setters
    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public List<Product> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<Product> listProducts) {
        this.listProducts = listProducts;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Funciones
    public void addProduct(Product product) {
        listProducts.add(product);
    }

    public void removeProduct(Product product) {
        listProducts.remove(product);
    }

    public void updateProduct(Product product) {
        for (int i = 0; i < listProducts.size(); i++) {
            if (listProducts.get(i).getNameProduct().equals(product.getNameProduct())) {
                listProducts.set(i, product);
                break;
            }
        }
    }

    public Product findProduct(String nameProduct) {
        for (Product product : listProducts) {
            if (product.getNameProduct().equals(nameProduct)) {
                return product;
            }
        }
        return null; // No se encontró el producto
    }
}
