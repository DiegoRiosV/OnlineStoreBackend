package com.example.onlineStore.service;

import com.example.onlineStore.model.Category;
import com.example.onlineStore.model.Product;
import com.example.onlineStore.repository.CategoryRepository;
import com.example.onlineStore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        // Asegurarse de que la categoría exista antes de guardar el producto
        if (product.getCategory() != null && product.getCategory().getNameCategory() != null) {
            Optional<Category> existingCategory = categoryRepository.findByNameCategory(product.getCategory().getNameCategory());
            existingCategory.ifPresent(product::setCategory);
        }
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Product not found with id: " + id));

        // Actualizar detalles del producto
        product.setNameProduct(productDetails.getNameProduct());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setStock(productDetails.getStock());
        product.setDiscount(productDetails.getDiscount());

        // Actualizar la categoría si se proporciona una
        if (productDetails.getCategory() != null && productDetails.getCategory().getNameCategory() != null) {
            Optional<Category> existingCategory = categoryRepository.findByNameCategory(productDetails.getCategory().getNameCategory());
            existingCategory.ifPresent(product::setCategory);
        }

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        boolean exists = productRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Product with id " + id + " does not exist");
        }
        productRepository.deleteById(id);
    }
}