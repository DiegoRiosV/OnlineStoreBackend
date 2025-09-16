package com.example.onlineStore.controller;

import com.example.onlineStore.dto.CartItemDTO;
import com.example.onlineStore.model.CartItem;
import com.example.onlineStore.model.Product;
import com.example.onlineStore.service.CartItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:4200")
public class CartController {

    private final CartItemService service;

    public CartController(CartItemService service) {
        this.service = service;
    }

    // ====== DTOs de request ======
    public static class AddItemRequest {
        public Long cartId;
        public Long productId;
        public int quantity;
    }
    public static class UpdateQuantityRequest {
        public int quantity;
    }

    // ====== Endpoints ======

    @GetMapping("/{cartId}")
    public List<CartItemDTO> getCart(@PathVariable Long cartId) {
        return service.getCart(cartId).stream()
                .map(CartController::toDto)
                .toList();
    }

    @PostMapping("/items")
    public ResponseEntity<CartItemDTO> addItem(@RequestBody AddItemRequest req) {
        CartItem created = service.addItem(req.cartId, req.productId, req.quantity);
        return ResponseEntity
                .created(URI.create("/api/cart/" + req.cartId))
                .body(toDto(created));
    }

    @PatchMapping("/items/{cartItemId}")
    public ResponseEntity<CartItemDTO> updateQuantity(@PathVariable Long cartItemId,
                                                      @RequestBody UpdateQuantityRequest req) {
        CartItem updated = service.updateQuantity(cartItemId, req.quantity);
        return ResponseEntity.ok(toDto(updated));
    }

    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<Void> removeItem(@PathVariable Long cartItemId) {
        service.removeItem(cartItemId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable Long cartId) {
        service.clearCart(cartId);
        return ResponseEntity.noContent().build();
    }

    // ====== Mapper ======
    private static CartItemDTO toDto(CartItem entity) {
        Product p = entity.getProduct();
        return new CartItemDTO(
                p.getId(),
                p.getNameProduct(),
                p.getPrice(),
                entity.getQuantity()
        );
    }
}
