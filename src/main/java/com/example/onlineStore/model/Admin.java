package com.example.onlineStore.model;

import jakarta.persistence.*;

@Entity
@Table(name = "admins")
public class Admin extends User {

    @Column(nullable = false, unique = true, length = 50)
    private String accessCode;

    @OneToOne
    @JoinColumn(name = "inventory_id", referencedColumnName = "id")
    private Inventory adminInventory;

    // === Constructores ===

    // Constructor sin argumentos requerido por JPA
    protected Admin(String julian, String pantoja, String suarez, int i, String jps01, Inventory inventory) {}

    public Admin(String name, String firstLastName, String secondLastName,
                 String accessCode, Inventory adminInventory) {
        super(name, firstLastName, secondLastName);
        this.accessCode = accessCode;
        this.adminInventory = adminInventory;
    }

    // === Getters y Setters ===
    public String getAccessCode() { return accessCode; }
    public void setAccessCode(String accessCode) { this.accessCode = accessCode; }

    public Inventory getAdminInventory() { return adminInventory; }
    public void setAdminInventory(Inventory adminInventory) { this.adminInventory = adminInventory; }
}