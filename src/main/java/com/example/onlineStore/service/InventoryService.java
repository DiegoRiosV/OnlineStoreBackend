package com.example.onlineStore.service;

import com.example.onlineStore.model.Category;
import com.example.onlineStore.model.Inventory;
import com.example.onlineStore.repository.CategoryRepository;
import com.example.onlineStore.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final CategoryRepository categoryRepository;

    public InventoryService(InventoryRepository inventoryRepository, CategoryRepository categoryRepository) {
        this.inventoryRepository = inventoryRepository;
        this.categoryRepository = categoryRepository;
    }

    // ===== CRUD básico =====
    public List<Inventory> getAll() {
        return inventoryRepository.findAll();
    }

    public Inventory getById(Long id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Inventory not found with id: " + id));
    }

    public Inventory create(Inventory inv) {
        if (inv.getListCategories() == null) {
            return inventoryRepository.save(new Inventory(List.of()));
        }
        inv.setLastUpdate(new Date());
        return inventoryRepository.save(inv);
    }

    public Inventory replaceCategories(Long inventoryId, List<Long> categoryIds) {
        Inventory inv = getById(inventoryId);

        // Cargar categorías válidas
        List<Category> categories = categoryRepository.findAllById(categoryIds);

        // Reemplazar lista
        inv.setListCategories(categories);
        inv.setLastUpdate(new Date());
        return inventoryRepository.save(inv);
    }

    public void delete(Long id) {
        Inventory inv = getById(id);
        inventoryRepository.delete(inv);
    }

    // ===== Operaciones de negocio =====
    public Inventory addCategory(Long inventoryId, Long categoryId) {
        Inventory inv = getById(inventoryId);
        Category cat = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalStateException("Category not found with id: " + categoryId));

        // Evitar duplicados
        boolean exists = inv.getListCategories().stream()
                .anyMatch(c -> c.getId().equals(cat.getId()));
        if (!exists) {
            inv.addCategory(cat);       // actualiza lastUpdate adentro
        }
        return inventoryRepository.save(inv);
    }

    public Inventory removeCategory(Long inventoryId, Long categoryId) {
        Inventory inv = getById(inventoryId);

        inv.getListCategories().removeIf(c -> c.getId().equals(categoryId));
        inv.setLastUpdate(new Date());

        return inventoryRepository.save(inv);
    }
}
