package com.example.onlineStore.service;

import com.example.onlineStore.model.Discount;
import com.example.onlineStore.model.Product;

import java.math.BigDecimal;
import java.util.Optional;

public interface DiscountService {
    /** Devuelve el descuento si el código es válido para el producto y está vigente. */
    Optional<Discount> validateForProduct(String code, Product product);

    /** Calcula precio con porcentaje (0..100). */
    BigDecimal applyPct(BigDecimal price, BigDecimal pct);
}
