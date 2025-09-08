package com.example.onlineStore.model;

import java.math.BigDecimal;

public class PayPalPayment implements IPaymentMethod {
    private String emailAccount;
    private String password;

    // Constructor
    public PayPalPayment(String emailAccount, String password) {
        this.emailAccount = emailAccount;
        this.password = password;
    }

    // Getters y Setters
    public String getEmailAccount() { return emailAccount; }
    public void setEmailAccount(String emailAccount) { this.emailAccount = emailAccount; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    // Implementacion de la interfaz
    @Override
    public boolean pay(BigDecimal amount) {
        // Logica simulada de pago con PayPal
        return true;
    }

    @Override
    public String getPaymentDetails() {
        return "PayPal account: " + emailAccount;
    }
}