package com.example.onlineStore.service;

import com.example.onlineStore.model.Discount;
import com.example.onlineStore.repository.DiscountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountService {
    private final DiscountRepository repo;

    public DiscountService(DiscountRepository repo) {
        this.repo = repo;
    }

    public List<Discount> getAllDiscounts() {
        return repo.findAll();
    }

    public Optional<Discount> getDiscountById(Long id) {
        return repo.findById(id);
    }

    public Optional<Discount> getDiscountByCode(String code) {
        return repo.findByIdDiscount(code);
    }

    public Discount createDiscount(Discount discount) {
        return repo.save(discount);
    }

    public Discount updateDiscount(Long id, Discount discountDetails) {
        return repo.findById(id).map(d -> {
            d.setIdDiscount(discountDetails.getIdDiscount());
            d.setNameDiscount(discountDetails.getNameDiscount());
            d.setPercentage(discountDetails.getPercentage());
            d.setStartDate(discountDetails.getStartDate());
            d.setEndDate(discountDetails.getEndDate());
            return repo.save(d);
        }).orElseThrow(() -> new IllegalStateException("Discount not found"));
    }

    public void deleteDiscount(Long id) {
        repo.deleteById(id);
    }
}
