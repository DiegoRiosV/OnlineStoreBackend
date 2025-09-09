package com.example.onlineStore.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    @Test
    void testClientCreation() {
        ShoppingCart cart = new ShoppingCart();
        Client client = new Client("Diego", "Rios", "Valverde","C003",cart,"mail@example.com", "password123", null);
        assertNotNull(client);
    }
    @Test
    void testClientWithPaymentMethod() {
        // Crear carrito vacío
        ShoppingCart cart = new ShoppingCart();

        // Crear metodo de pago (tarjeta de crédito)
        IPaymentMethod paymentMethod = new CreditCardPayment(
                "1234-5678-9012-3456",
                "Diego Rios",
                "12/25",
                "123"
        );

        // Crear cliente con metodo de pago
        Client client = new Client(
                "Diego",
                "Rios",
                "Valverde",
                "C003",
                cart,
                "mail@example.com",
                "password123",
                paymentMethod
        );

        // Validaciones
        assertNotNull(client);
        assertEquals("mail@example.com", client.getMail());
        assertEquals("Diego", client.getName());
        assertNotNull(client.getPaymentMethod());

        // Simular un pago de 100.00
        client.getPaymentMethod().pay(new java.math.BigDecimal("100.00"));
    }
}
