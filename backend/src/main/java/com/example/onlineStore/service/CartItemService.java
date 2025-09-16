package com.example.onlineStore.service;
import com.example.onlineStore.Entity.CartItem;
import com.example.onlineStore.repository.CartItemRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartItemService {
    private final CartItemRepository repo;

    public CartItemService(CartItemRepository repo) {
        this.repo = repo;
    }

    public List<CartItem> getCart(Long cartId) {
        return repo.findByCartId(cartId);
    }

    public CartItem addItem(CartItem item) {
        return repo.save(item);
    }

    public void removeItem(Long id) {
        repo.deleteById(id);
    }

    public void clearCart(Long cartId) {
        List<CartItem> items = repo.findByCartId(cartId);
        repo.deleteAll(items);
    }
}
