package com.agoda.console.util;

public class InputValidator {
    public static boolean isValidEmail(String e) {
        return e != null && e.matches("^[\\w.+\\-]+@[\\w.\\-]+\\.[A-Za-z]{2,}$");
    }
    public static boolean isValidVisa(String c) {
        return c != null && c.matches("\\d{16}");
    }
    public static boolean isValidExpiry(String s) {
        return s != null && s.matches("^(0[1-9]|1[0-2])/\\d{2}$");
    }
    public static boolean isValidCVV(String c) {
        return c != null && c.matches("\\d{3}");
    }
}