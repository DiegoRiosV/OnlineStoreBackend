package com.example.onlineStore.model;

import java.math.BigDecimal;

public class CreditCardPayment implements IPaymentMethod {
    private String cardNumber;
    private String cardHolder;
    private String expirationDate;
    private String cvv;

    // Constructor
    public CreditCardPayment(String cardNumber, String cardHolder, String expirationDate, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }

    // Getters y Setters
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public String getCardHolder() { return cardHolder; }
    public void setCardHolder(String cardHolder) { this.cardHolder = cardHolder; }

    public String getExpirationDate() { return expirationDate; }
    public void setExpirationDate(String expirationDate) { this.expirationDate = expirationDate; }

    public String getCvv() { return cvv; }
    public void setCvv(String cvv) { this.cvv = cvv; }

    // Implementacion de la interfaz
    @Override
    public boolean pay(BigDecimal amount) {
        // Logica simulada de pago con tarjeta
        return true;
    }

    @Override
    public String getPaymentDetails() {
        return "CreditCard: " + cardNumber;
    }
}
