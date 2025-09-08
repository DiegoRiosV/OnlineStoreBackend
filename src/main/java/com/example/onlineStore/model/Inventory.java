package com.example.onlineStore.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Inventory {
    private List<Category> listCategories;
    private Date lastUpdate;

    // Constructor
    public Inventory() {
        this.listCategories = new ArrayList<>();
        this.lastUpdate = new Date(); // Inicializa con la fecha actual
    }

    // Getters y Setters
    public List<Category> getListCategories() {
        return listCategories;
    }

    public void setListCategories(List<Category> listCategories) {
        this.listCategories = listCategories;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    // Funciones
    public void addCategory(Category category) {
        listCategories.add(category);
        this.lastUpdate = new Date();
    }

    public void removeCategory(Category category) {
        listCategories.remove(category);
        this.lastUpdate = new Date();
    }

    public void updateCategory(Category category) {
        // Busca la categoría por id o nombre y reemplaza
        for (int i = 0; i < listCategories.size(); i++) {
            if (listCategories.get(i).getNameCategory().equals(category.getNameCategory())) {
                listCategories.set(i, category);
                this.lastUpdate = new Date();
                break;
            }
        }
    }

    public Product searchProduct(String nameProduct) {
        for (Category category : listCategories) {
            Product found = category.findProduct(nameProduct);
            if (found != null) {
                return found;
            }
        }
        return null; // No se encontró el producto
    }
}
