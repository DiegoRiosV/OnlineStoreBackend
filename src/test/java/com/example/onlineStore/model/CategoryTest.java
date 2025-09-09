package com.example.onlineStore.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void testAddAndFindProduct() {
        // Crear categoría
        Category category = new Category("Electronics", "Tech gadgets");

        // Crear productos
        Product p1 = new Product("P001", "Laptop", "Gaming Laptop", new BigDecimal("1500.00"), 10);
        Product p2 = new Product("P002", "Mouse", "Wireless Mouse", new BigDecimal("50.00"), 20);

        // Agregar productos a la categoría
        category.addProduct(p1);
        category.addProduct(p2);

        // Validaciones
        assertEquals(2, category.getListProducts().size());
        assertEquals(p1, category.findProduct("Laptop"));
        assertEquals(p2, category.findProduct("Mouse"));
        assertNull(category.findProduct("Keyboard")); // producto no existente
    }

    @Test
    void testUpdateAndRemoveProduct() {
        // Crear categoría
        Category category = new Category("Electronics", "Tech gadgets");

        // Crear producto
        Product p = new Product("P001", "Laptop", "Gaming Laptop", new BigDecimal("1500.00"), 10);
        category.addProduct(p);

        // Actualizar producto
        Product updatedProduct = new Product("P001", "Laptop", "Updated Laptop Description", new BigDecimal("1600.00"), 12);
        category.updateProduct(updatedProduct);

        // Validaciones de actualización
        Product found = category.findProduct("Laptop");
        assertNotNull(found);
        assertEquals("Updated Laptop Description", found.getDescription());
        assertEquals(new BigDecimal("1600.00"), found.getPrice());
        assertEquals(12, found.getStock());

        // Eliminar producto
        category.removeProduct(updatedProduct);
        assertNull(category.findProduct("Laptop"));
        assertEquals(0, category.getListProducts().size());
    }
}
