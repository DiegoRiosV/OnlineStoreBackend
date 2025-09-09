package com.example.onlineStore.model;

import com.example.onlineStore.model.Client;
import com.example.onlineStore.model.ShoppingCart;
import com.example.onlineStore.model.CreditCardPayment;
import com.example.onlineStore.model.Payment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void testClientCreation() {
        // 1. Crear el objeto Client usando el constructor compatible con JPA
        Client client = new Client(
                "Diego",
                "Rios",
                "Valverde",
                "C003",
                "mail@example.com",
                "password123"
        );

        // 2. Crear las entidades relacionadas (ShoppingCart)
        ShoppingCart cart = new ShoppingCart();
        // NOTA: Con un repositorio, harías:
        // ShoppingCart savedCart = shoppingCartRepository.save(cart);

        // 3. Establecer la relación usando el setter
        client.setCart(cart);

        // Validaciones
        assertNotNull(client);
        assertNotNull(client.getCart());
        assertEquals("mail@example.com", client.getMail());
    }

    @Test
    void testClientWithPaymentMethod() {
        // 1. Crear el objeto Client
        Client client = new Client(
                "Diego",
                "Rios",
                "Valverde",
                "C003",
                "mail@example.com",
                "password123"
        );

        // 2. Crear el método de pago (entidad concreta)
        // NOTA: Usamos CreditCardPayment, que es una entidad, no IPaymentMethod que es una interfaz.
        CreditCardPayment creditCardPayment = new CreditCardPayment(
                new BigDecimal("100.00"), // monto simulado
                "1234-5678-9012-3456",
                "Diego Rios",
                "12/25",
                "123"
        );

        // 3. Establecer la relación usando el setter
        client.setPaymentMethod(creditCardPayment);

        // Validaciones
        assertNotNull(client);
        assertEquals("mail@example.com", client.getMail());
        assertNotNull(client.getPaymentMethod());

        // La lógica de negocio del pago es un método, no una llamada directa en el test de entidad.
        assertTrue(client.getPaymentMethod().pay());
    }
}