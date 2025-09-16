package com.example.onlineStore.repository;

import com.example.onlineStore.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    // útil para “traer el carrito del cliente X”
    Optional<ShoppingCart> findByClient_Id(Long clientId);

    // si manejas un estado (ACTIVE/CHECKED_OUT), puedes extender con:
    // Optional<ShoppingCart> findByClient_IdAndStatus(Long clientId, CartStatus status);
}
