package com.example.onlineStore.model;
import java.util.Date;
import java.math.BigDecimal;
import java.time.DateTimeException;

public class Validation {

    public static boolean isValidUsername(String username) {
        if (username == null) return false;
        return username.length() > 4;
    }

    public static boolean isValidPassword(String password) {
        if (password == null) return false;

        boolean hasLetter = password.matches(".*[a-zA-Z].*");
        boolean hasDigit = password.matches(".*\\d.*");

        return password.length() > 4 && hasLetter && hasDigit;
    }

    public static boolean isTimeGone(Date date) {
        if (date == null) return false;
        Date today = new Date();
        return date.before(today); // true si es hoy o una fecha futura
    }

    public static boolean isNotTimeGone(Date date) {
        return !isTimeGone(date);
    }

    public static boolean isValidAmount(BigDecimal amount) {
        return amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
    }
    // Validar número de tarjeta (16 dígitos)
    public static boolean isValidCreditCardNumber(String number) {
        if (number == null) return false;
        return number.matches("\\d{16}");
    }

    public static boolean isValidPayPalEmail(String email) {
        if (email == null) return false;
        return email.contains("@") && email.contains(".");
    }
    public static boolean hasRequiredParams(String[] params, int expectedLength) {
        return params != null && params.length >= expectedLength;
    }

}