package com.example.onlineStore.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    // Clave = producto, Valor = cantidad
    private Map<Product, Integer> items;

    // Constructor
    public ShoppingCart() {
        this.items = new HashMap<>();
    }

    // Getters y Setters
    public Map<Product, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Product, Integer> items) {
        this.items = items;
    }

    // Funciones

    // Agrega un producto al carrito (si ya existe suma la cantidad)
    public void addProduct(Product product, int quantity) {
        if (product != null && quantity > 0) {
            items.put(product, items.getOrDefault(product, 0) + quantity);
        }
    }

    // Elimina un producto completamente del carrito
    public void removeProduct(Product product) {
        items.remove(product);
    }

    // Actualiza la cantidad de un producto
    public void updateProduct(Product product, int quantity) {
        if (items.containsKey(product)) {
            if (quantity > 0) {
                items.put(product, quantity);
            } else {
                items.remove(product); // Si la cantidad es 0 o menor, lo elimina
            }
        }
    }

    // Calcula el total del carrito
    public BigDecimal calculateTotal() {
        BigDecimal total = BigDecimal.ZERO;
        int quantity = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product p = entry.getKey();
            quantity = entry.getValue();
            total = total.add(p.getPriceWithDiscount().multiply(BigDecimal.valueOf(quantity)));

        }
        return total;
    }
}
