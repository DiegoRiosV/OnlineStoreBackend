package com.example.onlineStore.service;

import com.example.onlineStore.model.*;
import com.example.onlineStore.repository.PaymentRepository;
import com.example.onlineStore.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, ClientRepository clientRepository) {
        this.paymentRepository = paymentRepository;
        this.clientRepository = clientRepository;
    }

    /**
     * Crea un pago para un cliente existente y lo asocia.
     */
    public Payment createPaymentForClient(Long clientId, String type, BigDecimal amount, String... params) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        Payment payment;

        switch (type.toLowerCase()) {
            case "paypal":
                if (!Validation.hasRequiredParams(params, 2)) throw new IllegalArgumentException("Faltan parámetros para PayPal");
                if (!Validation.isValidPayPalEmail(params[0]))throw new IllegalArgumentException("Email de PayPal inválido");
                payment = new CreatorPayPalPayment(amount, params[0], params[1]);
                break;
            case "creditcard":
                if (!Validation.hasRequiredParams(params, 4))throw new IllegalArgumentException("Faltan parámetros para CreditCard");
                payment = new CreatorCreditCardPayment(amount, params[0], params[1], params[2], params[3]);
                break;
            default:
                throw new IllegalArgumentException("Tipo de pago no soportado");
        }

        // Asociamos el pago al cliente
        client.setPaymentMethod(payment);

        // Guardamos en la base de datos
        paymentRepository.save(payment);

        return payment;
    }

    /**
     * Ejecuta el pago del cliente.
     */
    public boolean makePayment(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
        Payment payment = client.getPaymentMethod();
        if (payment == null) throw new IllegalStateException("No se ha asignado método de pago");
        return payment.pay();
    }

    /**
     * Devuelve los detalles del pago del cliente.
     */
    public String getPaymentDetails(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
        Payment payment = client.getPaymentMethod();
        if (payment == null) return "No se ha asignado método de pago";
        return payment.getPaymentDetails();
    }
}
