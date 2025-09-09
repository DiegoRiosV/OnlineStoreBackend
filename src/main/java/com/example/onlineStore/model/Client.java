package com.example.onlineStore.model;

import jakarta.persistence.*;

@Entity
@Table(name = "clients")
public class Client extends User {

    @Column(name = "id_client", unique = true, length = 50)
    private String idClient;

    @Column(nullable = false, unique = true, length = 120)
    private String mail;

    @Column(nullable = false, length = 120)
    private String password;

    // Relaciones mapeadas
    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private ShoppingCart cart;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_method_id")
    private Payment paymentMethod;

    // === Constructores ===
    protected Client() { }

    public Client(String name, String firstLastName, String secondLastName,
                  String idClient, String mail, String password) {
        super(name, firstLastName, secondLastName);
        this.idClient = idClient;
        this.mail = mail;
        this.password = password;
    }

    // === Getters/Setters ===
    public String getIdClient() { return idClient; }
    public void setIdClient(String idClient) { this.idClient = idClient; }
    public ShoppingCart getCart() { return cart; }
    public void setCart(ShoppingCart cart) { this.cart = cart; }
    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Payment getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(Payment paymentMethod) { this.paymentMethod = paymentMethod; }
}