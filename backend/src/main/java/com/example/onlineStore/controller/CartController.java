package com.example.onlineStore.controller;
import com.example.onlineStore.Entity.CartItem;
import com.example.onlineStore.service.CartItemService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:4200")
public class CartController {
    private final CartItemService service;

    public CartController(CartItemService service) {
        this.service = service;
    }

    @GetMapping("/{cartId}")
    public List<CartItem> getCart(@PathVariable Long cartId) {
        return service.getCart(cartId);
    }

    @PostMapping
    public CartItem addItem(@RequestBody CartItem item) {
        return service.addItem(item);
    }

    @DeleteMapping("/{id}")
    public void removeItem(@PathVariable Long id) {
        service.removeItem(id);
    }

    @DeleteMapping("/clear/{cartId}")
    public void clearCart(@PathVariable Long cartId) {
        service.clearCart(cartId);
    }
}
