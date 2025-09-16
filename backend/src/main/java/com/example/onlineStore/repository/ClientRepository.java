package com.example.onlineStore.repository;

import com.example.onlineStore.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    // Metodos básicos CRUD ya están incluidos por JpaRepository
}
