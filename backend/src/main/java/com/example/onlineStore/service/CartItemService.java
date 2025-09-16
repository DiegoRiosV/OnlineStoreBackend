package com.example.onlineStore.service;

import com.example.onlineStore.model.CartItem;
import com.example.onlineStore.model.Product;
import com.example.onlineStore.model.ShoppingCart;
import com.example.onlineStore.repository.CartItemRepository;
import com.example.onlineStore.repository.ProductRepository;
import com.example.onlineStore.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepo;
    private final ShoppingCartRepository cartRepo;
    private final ProductRepository productRepo;

    public CartItemService(CartItemRepository cartItemRepo,
                           ShoppingCartRepository cartRepo,
                           ProductRepository productRepo) {
        this.cartItemRepo = cartItemRepo;
        this.cartRepo = cartRepo;
        this.productRepo = productRepo;
    }

    @Transactional(readOnly = true)
    public List<CartItem> getCart(Long cartId) {
        return cartItemRepo.findByCart_Id(cartId);
    }

    @Transactional
    public CartItem addItem(Long cartId, Long productId, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("quantity must be > 0");

        ShoppingCart cart = ensureCartExists(cartId);
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new IllegalStateException("Product not found: " + productId));

        // si ya existe la línea, sumamos cantidad
        CartItem item = cartItemRepo.findByCart_IdAndProduct_Id(cart.getId(), productId)
                .map(existing -> {
                    existing.setQuantity(existing.getQuantity() + quantity);
                    return existing;
                })
                .orElseGet(() -> new CartItem(cart, product, quantity));

        return cartItemRepo.save(item);
    }

    @Transactional
    public CartItem updateQuantity(Long cartItemId, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("quantity must be > 0");

        CartItem item = cartItemRepo.findById(cartItemId)
                .orElseThrow(() -> new IllegalStateException("CartItem not found: " + cartItemId));

        item.setQuantity(quantity);
        return cartItemRepo.save(item);
    }

    /** Variante por cartId + productId (para el front actual) */
    @Transactional
    public void updateQuantityByProduct(Long cartId, Long productId, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("quantity must be > 0");
        CartItem item = cartItemRepo.findByCart_IdAndProduct_Id(cartId, productId)
                .orElseThrow(() -> new IllegalStateException("CartItem not found for cartId=" + cartId + " productId=" + productId));
        item.setQuantity(quantity);
        cartItemRepo.save(item);
    }

    @Transactional
    public void removeItem(Long id) {
        cartItemRepo.deleteById(id);
    }

    /** Variante por cartId + productId (para el front actual) */
    @Transactional
    public void removeByProduct(Long cartId, Long productId) {
        CartItem item = cartItemRepo.findByCart_IdAndProduct_Id(cartId, productId)
                .orElseThrow(() -> new IllegalStateException("CartItem not found for cartId=" + cartId + " productId=" + productId));
        cartItemRepo.deleteById(item.getId());
    }

    @Transactional
    public void clearCart(Long cartId) {
        cartItemRepo.deleteByCart_Id(cartId);
    }

    /** Crea el carrito si no existe (PK auto-incremental) */
    private ShoppingCart ensureCartExists(Long cartId) {
        return cartRepo.findById(cartId)
                .orElseGet(() -> cartRepo.save(ShoppingCart.create()));  // ✅ antes: new ShoppingCart()
    }
}
