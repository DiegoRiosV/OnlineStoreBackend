package com.example.onlineStore.repository;

import com.example.onlineStore.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

    // Busca por la columna discount_code, sin depender del nombre del campo en la entidad
    @Query(value = "SELECT * FROM discounts WHERE discount_code = :code LIMIT 1", nativeQuery = true)
    Optional<Discount> findByCode(@Param("code") String code);
}
