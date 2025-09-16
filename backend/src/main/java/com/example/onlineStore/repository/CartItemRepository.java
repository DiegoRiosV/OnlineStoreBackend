package com.example.onlineStore.repository;

import com.example.onlineStore.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // Trae todos los ítems de un carrito por su id
    List<CartItem> findByCart_Id(Long cartId);

    // Busca una línea específica (carrito + producto) para poder sumar cantidades si ya existe
    Optional<CartItem> findByCart_IdAndProduct_Id(Long cartId, Long productId);

    // Útil para vaciar el carrito
    void deleteByCart_Id(Long cartId);
}
