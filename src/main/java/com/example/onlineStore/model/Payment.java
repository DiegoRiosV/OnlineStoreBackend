package com.example.onlineStore.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "payments")
@Inheritance(strategy = InheritanceType.JOINED) // cada subclase tendrá su propia tabla
public abstract class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    protected Payment() {}

    protected Payment(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getId() { return id; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    // Métodos abstractos → las subclases deben implementarlos
    public abstract boolean pay();
    public abstract String getPaymentDetails();
}
