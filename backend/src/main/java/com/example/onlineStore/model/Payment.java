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

    // Cada Payment tiene su IPaymentMethod (transient porque no se persiste directamente)
    @Transient
    protected IPaymentMethod method;

    protected Payment() {}

    protected Payment(BigDecimal amount) {
        this.amount = amount;
        this.method = createPaymentMethod(); // Factory method
    }

    public Long getId() { return id; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    // Métodos abstractos → cada subclase define su método de pago
    protected abstract IPaymentMethod createPaymentMethod();

    public boolean pay() {
        if(method == null) throw new IllegalStateException("No se ha asignado método de pago");
        return method.pay(amount);
    }

    public String getPaymentDetails() {
        if(method == null) return "No se ha asignado método de pago";
        return method.getPaymentDetails();
    }
}
