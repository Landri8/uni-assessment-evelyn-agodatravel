package com.agoda.console.util;

/**
 * InputValidator provides static methods to validate various types of user input
 * such as email addresses and payment details.
 */
public class InputValidator {
    /**
     * Validates an email address using a regex pattern.
     *
     * @param e the email string to validate
     * @return true if the email matches the pattern, false otherwise
     */
    public static boolean isValidEmail(String e) {
        return e != null && e.matches("^[\\w.+\\-]+@[\\w.\\-]+\\.[A-Za-z]{2,}$");
    }

    /**
     * Validates a VISA credit card number (16 digits).
     *
     * @param c the card number string to validate
     * @return true if the string contains exactly 16 digits, false otherwise
     */
    public static boolean isValidVisa(String c) {
        return c != null && c.matches("\\d{16}");
    }

    /**
     * Validates a card expiry date in MM/YY format.
     * Ensures month is between 01 and 12 and year is two digits.
     *
     * @param s the expiry date string to validate
     * @return true if the string matches the MM/YY format, false otherwise
     */
    public static boolean isValidExpiry(String s) {
        return s != null && s.matches("^(0[1-9]|1[0-2])/\\d{2}$");
    }

    /**
     * Validates a CVV code (3 digits).
     *
     * @param c the CVV string to validate
     * @return true if the string contains exactly 3 digits, false otherwise
     */
    public static boolean isValidCVV(String c) {
        return c != null && c.matches("\\d{3}");
    }
}