package com.example.onlineStore.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "credit_card_payments")
public class CreditCardPayment extends Payment {

    @Column(nullable = false, length = 20)
    private String cardNumber;

    @Column(nullable = false, length = 100)
    private String cardHolder;

    @Column(nullable = false, length = 10)
    private String expirationDate;

    @Column(nullable = false, length = 5)
    private String cvv;

    protected CreditCardPayment() {
        super();
    }

    public CreditCardPayment(String cardNumber, String cardHolder,
                             String expirationDate, String cvv) {
        this(java.math.BigDecimal.ZERO, cardNumber, cardHolder, expirationDate, cvv);
    }

    public CreditCardPayment(BigDecimal zero, String cardNumber, String cardHolder, String expirationDate, String cvv) {
    }

    @Override
    public boolean pay() {
        // lógica simulada de pago con tarjeta
        return true;
    }

    @Override
    public String getPaymentDetails() {
        return "CreditCard ****" + cardNumber.substring(cardNumber.length() - 4);
    }

    // Getters/Setters
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public String getCardHolder() { return cardHolder; }
    public void setCardHolder(String cardHolder) { this.cardHolder = cardHolder; }

    public String getExpirationDate() { return expirationDate; }
    public void setExpirationDate(String expirationDate) { this.expirationDate = expirationDate; }

    public String getCvv() { return cvv; }
    public void setCvv(String cvv) { this.cvv = cvv; }
}
