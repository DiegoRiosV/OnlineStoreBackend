package com.example.onlineStore.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {

    // PK autogenerada para Hibernate
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Código de producto de negocio (SKU) — único
    @Column(name = "id_product", unique = true, length = 50, nullable = false)
    private String idProduct;

    @Column(name = "name_product", nullable = false, length = 160)
    private String nameProduct;

    @Column(length = 500)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private int stock;

    // Muchas productos pertenecen a 1 categoría
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

    // Muchos productos pueden compartir un mismo descuento (p.ej., “BLACKFRIDAY”)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discount_id")
    private Discount discount;

    // ===== Constructores =====
    protected Product() { } // requerido por JPA

    public Product(String idProduct, String nameProduct, String description,
                   BigDecimal price, int stock) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    // ===== Getters/Setters =====
    public Long getId() { return id; }

    public String getIdProduct() { return idProduct; }
    public void setIdProduct(String idProduct) { this.idProduct = idProduct; }

    public String getNameProduct() { return nameProduct; }
    public void setNameProduct(String nameProduct) { this.nameProduct = nameProduct; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public Discount getDiscount() { return discount; }
    public void setDiscount(Discount discount) { this.discount = discount; }

    // ===== Lógica de negocio =====
    public BigDecimal getPriceWithDiscount() {
        if (discount != null && discount.isActive()) {
            return price.multiply(BigDecimal.ONE.subtract(discount.getPercentage()));
        }
        return price;
    }
}
