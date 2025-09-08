package com.example.onlineStore.model;

public class Admin extends User {
    private int idAdmin;
    private String accessCode;
    private Inventory adminInventory;

    // Constructor
    public Admin(String name, String firstLastName, String secondLastName,
                 int idAdmin, String accessCode, Inventory adminInventory) {
        super(name, firstLastName, secondLastName);
        this.idAdmin = idAdmin;
        this.accessCode = accessCode;
        this.adminInventory = adminInventory;
    }

    // Getters y Setters
    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public Inventory getAdminInventory() {
        return adminInventory;
    }

    public void setAdminInventory(Inventory adminInventory) {
        this.adminInventory = adminInventory;
    }
}
