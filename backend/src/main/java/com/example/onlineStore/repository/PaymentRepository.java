package com.example.onlineStore.repository;
import com.example.onlineStore.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Esta interfaz extiende JpaRepository, lo que ya nos proporciona
    // todos los metodos CRUD basicos (save, findById, findAll, delete, etc.).
    // Por eso no necesitamos implementar nada aqui a menos que sea necesario
}
