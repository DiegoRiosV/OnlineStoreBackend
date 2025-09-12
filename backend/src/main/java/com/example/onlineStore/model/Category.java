package com.example.onlineStore.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String nameCategory;

    @Column(length = 255)
    private String description;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            orphanRemoval = false
    )
    @JoinColumn(name = "category_id") // crea/usa FK category_id en products
    private List<Product> listProducts = new ArrayList<>();


    // === Constructores ===
    protected Category() {}

    public Category(String nameCategory, String description) {
        this.nameCategory = nameCategory;
        this.description = description;
    }

    // === Getters y Setters ===
    public Long getId() { return id; }
    public String getNameCategory() { return nameCategory; }
    public void setNameCategory(String nameCategory) { this.nameCategory = nameCategory; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public List<Product> getListProducts() { return listProducts; }
    public void setListProducts(List<Product> listProducts) { this.listProducts = listProducts; }
}