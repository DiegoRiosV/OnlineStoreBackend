package com.example.onlineStore.model;
import java.util.Date;
import java.time.DateTimeException;

public class Validation {

    // Valida que el username tenga más de 4 caracteres
    public static boolean isValidUsername(String username) {
        if (username == null) return false;
        return username.length() > 4;
    }

    // Valida que el password tenga más de 4 caracteres y contenga letras y números
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

    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        return email.matches("@");
    }
}