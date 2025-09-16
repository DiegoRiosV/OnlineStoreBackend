package com.example.onlineStore.service;

import com.example.onlineStore.model.Product;
import com.example.onlineStore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Product not found with id: " + id));

        product.setNameProduct(productDetails.getNameProduct());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setStock(productDetails.getStock());
        product.setDiscount(productDetails.getDiscount());

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        boolean exists = productRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Product with id " + id + " does not exist");
        }
        productRepository.deleteById(id);
    }
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        System.out.println("Productos encontrados: " + products.size());
        return products;
    }

}
