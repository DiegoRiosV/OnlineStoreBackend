package com.example.onlineStore.model;

public class Client extends User {
    private String idClient;
    private ShoppingCart cart;
    private String mail;
    private String password;
    private IPaymentMethod paymentMethod;

    // Constructor
    public Client(String name, String firstLastName, String secondLastName,
                  String idClient, ShoppingCart cart, String mail, String password,
                  IPaymentMethod paymentMethod) {
        super(name, firstLastName, secondLastName);
        this.idClient = idClient;
        this.cart = cart;
        this.mail = mail;
        this.password = password;
        this.paymentMethod = paymentMethod;
    }

    // Getters y Setters
    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public IPaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(IPaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
