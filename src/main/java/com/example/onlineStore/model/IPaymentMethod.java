package com.example.onlineStore.model;

import java.math.BigDecimal;

public interface IPaymentMethod {
    boolean pay(BigDecimal amount); // intenta realizar el pago de la cantidad indicada
    String getPaymentDetails();      // devuelve información relevante del metodo de pago
}
