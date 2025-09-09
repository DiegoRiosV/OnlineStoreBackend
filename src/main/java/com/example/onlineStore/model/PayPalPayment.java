package com.example.onlineStore.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "paypal_payments")
public class PayPalPayment extends Payment {

    @Column(nullable = false, length = 120)
    private String emailAccount;

    @Column(nullable = false, length = 120)
    private String password;

    protected PayPalPayment() {}

    public PayPalPayment(BigDecimal amount, String emailAccount, String password) {
        super(amount);
        this.emailAccount = emailAccount;
        this.password = password;
    }

    @Override
    public boolean pay() {
        // lógica simulada de pago con PayPal
        return true;
    }

    @Override
    public String getPaymentDetails() {
        return "PayPal account: " + emailAccount;
    }

    // Getters/Setters
    public String getEmailAccount() { return emailAccount; }
    public void setEmailAccount(String emailAccount) { this.emailAccount = emailAccount; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
