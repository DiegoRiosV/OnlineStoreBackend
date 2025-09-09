package com.example.onlineStore.model;

import org.junit.jupiter.api.Test;
import java.util.Date;
import java.math.BigDecimal; // Para manejar decimales con precisión
import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
    @Test
    void testInventoryCreation() {
        Inventory inventory = new Inventory();
        assertNotNull(inventory);
    }
    @Test
    void testInventoryWithCategoryAndProducts() {
        // Crear productos
        Product p1 = new Product("P001", "Laptop", "Gaming Laptop", new BigDecimal("1500.00"), 10);
        Product p2 = new Product("P002", "Mouse", "Wireless Mouse", new BigDecimal("50.00"), 20);
        Product p3 = new Product("P003", "Keyboard", "Mechanical Keyboard", new BigDecimal("120.00"), 15);

        // Crear categoría y agregar productos
        Category category = new Category("Electronics", "Tech gadgets");
        category.addProduct(p1);
        category.addProduct(p2);
        category.addProduct(p3);

        // Crear inventario y agregar categoría
        Inventory inventory = new Inventory();
        inventory.addCategory(category);
        inventory.setLastUpdate(new Date());

        // Validaciones
        assertNotNull(inventory);
        assertEquals(1, inventory.getListCategories().size());
        assertEquals("Electronics", inventory.getListCategories().get(0).getNameCategory());

        // Verificar que la categoría tiene 3 productos
        assertEquals(3, inventory.getListCategories().get(0).getListProducts().size());

        // Buscar producto por nombre
        Product found = inventory.searchProduct("Mouse");
        assertNotNull(found);
        assertEquals("Wireless Mouse", found.getDescription());
    }
}
