package com.example.onlineStore.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void testClientCreation() {
        Client client = new Client(
                "Diego",
                "Rios",
                "Valverde",
                "C003",
                "mail@example.com",
                "password123"
        );

        ShoppingCart cart = new ShoppingCart();
        client.setCart(cart);

        assertNotNull(client);
        assertNotNull(client.getCart());
        assertEquals("mail@example.com", client.getEmail());
    }

    @Test
    void testClientWithPayPalPayment() {
        Client client = new Client(
                "Diego",
                "Rios",
                "Valverde",
                "C003",
                "mail@example.com",
                "password123"
        );

        // Crear Payment usando la subclase CreatorPayPalPayment
        Payment payment = new CreatorPayPalPayment(
                new BigDecimal("100.00"),
                "correo@ejemplo.com",
                "password123"
        );

        client.setPaymentMethod(payment);

        assertNotNull(client.getPaymentMethod());
        assertTrue(client.getPaymentMethod().pay());
        assertEquals("PayPal account: correo@ejemplo.com", client.getPaymentMethod().getPaymentDetails());
    }

    @Test
    void testClientWithCreditCardPayment() {
        Client client = new Client(
                "Diego",
                "Rios",
                "Valverde",
                "C003",
                "mail@example.com",
                "password123"
        );

        // Crear Payment usando la subclase CreatorCreditCardPayment
        Payment payment = new CreatorCreditCardPayment(
                new BigDecimal("200.00"),
                "4444555566667777",
                "Diego Rios",
                "12/25",
                "123"
        );

        client.setPaymentMethod(payment);

        assertNotNull(client.getPaymentMethod());
        assertTrue(client.getPaymentMethod().pay());
        assertEquals("CreditCard ****7777", client.getPaymentMethod().getPaymentDetails());
    }

    @Test
    void testInvalidCreditCardThrowsException() {
        Client client = new Client("Diego", "Rios", "Valverde", "C003", "mail@example.com", "password123");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            client.createPayment("creditcard", new BigDecimal("200.00"), "123", "Diego Rios", "12/25", "123");
        });

        assertTrue(exception.getMessage().contains("Número de tarjeta inválido"));
    }

    @Test
    void testInvalidPayPalEmailThrowsException() {
        Client client = new Client("Diego", "Rios", "Valverde", "C003", "mail@example.com", "password123");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            client.createPayment("paypal", new BigDecimal("100.00"), "invalid-email", "password123");
        });

        assertTrue(exception.getMessage().contains("Email de PayPal inválido"));
    }

    @Test
    void testInvalidAmountThrowsException() {
        Client client = new Client("Diego", "Rios", "Valverde", "C003", "mail@example.com", "password123");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            client.createPayment("creditcard", new BigDecimal("-50.00"), "4444555566667777", "Diego Rios", "12/25", "123");
        });

        assertTrue(exception.getMessage().contains("Monto inválido"));
    }
}
