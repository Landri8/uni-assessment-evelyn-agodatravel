package com.agoda.console.service;

import com.agoda.console.util.InputValidator;
import java.util.Scanner;

public class PaymentService {
    public static String payWithVisa(Scanner sc) {
        String card, exp, cvv;
        while (true) {
            System.out.print("Enter 16-digit VISA or cancel/exit: ");
            card = sc.nextLine();
            if (card.equalsIgnoreCase("cancel")) return null;
            if (card.equalsIgnoreCase("exit")) System.exit(0);
            if (!InputValidator.isValidVisa(card)) {
                System.out.println("Invalid card number.");
                continue;
            }
            break;
        }
        while (true) {
            System.out.print("Enter expiry (MM/YY) or cancel/exit: ");
            exp = sc.nextLine();
            if (exp.equalsIgnoreCase("cancel")) return null;
            if (exp.equalsIgnoreCase("exit")) System.exit(0);
            if (!InputValidator.isValidExpiry(exp)) {
                System.out.println("Invalid expiry format.");
                continue;
            }
            break;
        }
        while (true) {
            System.out.print("Enter CVV (3 digits) or cancel/exit: ");
            cvv = sc.nextLine();
            if (cvv.equalsIgnoreCase("cancel")) return null;
            if (cvv.equalsIgnoreCase("exit")) System.exit(0);
            if (!InputValidator.isValidCVV(cvv)) {
                System.out.println("Invalid CVV.");
                continue;
            }
            break;
        }
        return "VISA";
    }

    public static String payWithPayPal(Scanner sc) {
        while (true) {
            System.out.print("Enter PayPal email or cancel/exit: ");
            String email = sc.nextLine();
            if (email.equalsIgnoreCase("cancel")) return null;
            if (email.equalsIgnoreCase("exit")) System.exit(0);
            if (InputValidator.isValidEmail(email)) return "PayPal";
            System.out.println("Invalid email.");
        }
    }

    public static String payWith7Eleven(Scanner sc) {
        while (true) {
            System.out.print("Enter 7-Eleven code or cancel/exit: ");
            String code = sc.nextLine();
            if (code.equalsIgnoreCase("cancel")) return null;
            if (code.equalsIgnoreCase("exit")) System.exit(0);
            if (!code.isBlank()) return "7-Eleven";
            System.out.println("Cannot be empty.");
        }
    }

    public static String payWithApplePay(Scanner sc) {
        while (true) {
            System.out.print("Enter Apple Pay ID or cancel/exit: ");
            String id = sc.nextLine();
            if (id.equalsIgnoreCase("cancel")) return null;
            if (id.equalsIgnoreCase("exit")) System.exit(0);
            if (!id.isBlank()) return "Apple Pay";
            System.out.println("Cannot be empty.");
        }
    }

    public static String payWithGooglePay(Scanner sc) {
        while (true) {
            System.out.print("Enter Google Pay ID or cancel/exit: ");
            String id = sc.nextLine();
            if (id.equalsIgnoreCase("cancel")) return null;
            if (id.equalsIgnoreCase("exit")) System.exit(0);
            if (!id.isBlank()) return "Google Pay";
            System.out.println("Cannot be empty.");
        }
    }
}
