package com.example.onlineStore.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED) // permite que Admin y Client hereden
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // clave primaria

    @Column(nullable = false, length = 80)
    private String name;

    @Column(nullable = false, length = 80)
    private String firstLastName;

    @Column(nullable = false, length = 80)
    private String secondLastName;

    // === Constructores ===

    // Requerido por JPA (sin parámetros)
    protected User() {}

    public User(String name, String firstLastName, String secondLastName) {
        this.name = name;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
    }

    // === Getters y Setters ===
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getFirstLastName() {
        return firstLastName;
    }
    public void setFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }
    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    @Override
    public String toString() {
        return name + " " + firstLastName + " " + secondLastName;
    }
}
