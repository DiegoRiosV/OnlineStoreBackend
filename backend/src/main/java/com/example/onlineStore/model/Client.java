package com.example.onlineStore.model;

import jakarta.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "clients")
public class Client extends User {

    @Column(name = "id_client", unique = true, length = 50)
    private String idClient;

    @Column(nullable = false, unique = true, length = 120)
    private String email;

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
                  String idClient, String email, String password) {
        super(name, firstLastName, secondLastName);
        this.idClient = idClient;
        this.email = email;
        this.password = password;
    }

    // === Getters/Setters ===
    public String getIdClient() { return idClient; }
    public void setIdClient(String idClient) { this.idClient = idClient; }
    public ShoppingCart getCart() { return cart; }
    public void setCart(ShoppingCart cart) { this.cart = cart; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Payment getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(Payment paymentMethod) { this.paymentMethod = paymentMethod; }
    //String... params recibe 0 o mas parametros
    public void createPayment(String type, BigDecimal amount, String... params) {
        if (!Validation.isValidAmount(amount)) {
            throw new IllegalArgumentException("Monto inválido");
        }
        switch(type.toLowerCase()) {
            case "paypal":
                if (!Validation.hasRequiredParams(params, 2)) {
                    throw new IllegalArgumentException("Faltan parámetros para PayPal");
                }
                if (!Validation.isValidPayPalEmail(params[0])) {
                    throw new IllegalArgumentException("Email de PayPal inválido");
                }
                this.paymentMethod = new CreatorPayPalPayment(amount, params[0], params[1]);
                break;
            case "creditcard":
                if (!Validation.hasRequiredParams(params, 4)) {
                    throw new IllegalArgumentException("Faltan parámetros para CreditCard");
                }
                if (!Validation.isValidCreditCardNumber(params[0])) {
                    throw new IllegalArgumentException("Número de tarjeta inválido");
                }
                this.paymentMethod = new CreatorCreditCardPayment(amount, params[0], params[1], params[2], params[3]);
                break;
            default:
                throw new IllegalArgumentException("Tipo de pago no soportado");
        }
    }

    // Hacer pago
    public boolean makePayment() {
        if(paymentMethod == null) throw new IllegalStateException("No se ha asignado método de pago");
        return paymentMethod.pay();
    }

    public String getPaymentDetails() {
        if(paymentMethod == null) return "No se ha asignado método de pago";
        return paymentMethod.getPaymentDetails();
    }
}