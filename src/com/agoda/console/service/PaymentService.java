package com.agoda.console.service;

import com.agoda.console.util.InputValidator;
import java.util.Scanner;

/**
 * PaymentService handles console-based payment flows for various methods.
 * It prompts the user for required payment details, validates them,
 * and returns the chosen payment method or null if cancelled.
 */
public class PaymentService {

    /**
     * Prompts the user to enter VISA card details and validates each input.
     * Loops until valid input is provided, 'cancel' is entered (returns null),
     * or 'exit' is entered (terminates the application).
     *
     * @param sc Scanner object for reading console input
     * @return the string "VISA" if payment details are successfully entered, or null if cancelled
     */
    public static String payWithVisa(Scanner sc) {
        String card, exp, cvv;
        // Loop until a valid 16-digit VISA card number is entered or user cancels/exits
        while (true) {
            System.out.print("Enter 16-digit VISA or cancel/exit: ");
            card = sc.nextLine();
            if (card.equalsIgnoreCase("cancel")) {
                // User chose to cancel payment
                return null;
            }
            if (card.equalsIgnoreCase("exit")) {
                // User chose to exit the application
                System.exit(0);
            }
            if (!InputValidator.isValidVisa(card)) {
                // Invalid format, prompt again
                System.out.println("Invalid card number.");
                continue;
            }
            break; // Valid card number entered
        }
        // Loop until a valid expiry date (MM/YY) is entered or user cancels/exits
        while (true) {
            System.out.print("Enter expiry (MM/YY) or cancel/exit: ");
            exp = sc.nextLine();
            if (exp.equalsIgnoreCase("cancel")) {
                return null;
            }
            if (exp.equalsIgnoreCase("exit")) {
                System.exit(0);
            }
            if (!InputValidator.isValidExpiry(exp)) {
                System.out.println("Invalid expiry format.");
                continue;
            }
            break; // Valid expiry entered
        }
        // Loop until a valid 3-digit CVV is entered or user cancels/exits
        while (true) {
            System.out.print("Enter CVV (3 digits) or cancel/exit: ");
            cvv = sc.nextLine();
            if (cvv.equalsIgnoreCase("cancel")) {
                return null;
            }
            if (cvv.equalsIgnoreCase("exit")) {
                System.exit(0);
            }
            if (!InputValidator.isValidCVV(cvv)) {
                System.out.println("Invalid CVV.");
                continue;
            }
            break; // Valid CVV entered
        }
        // Return the chosen payment method identifier
        return "VISA";
    }

    /**
     * Prompts the user to enter a 7-Eleven payment code.
     * Ensures the input is non-empty; supports cancel/exit commands.
     *
     * @param sc Scanner object for reading console input
     * @return the string "7-Eleven" if code entered, or null if cancelled
     */
    public static String payWith7Eleven(Scanner sc) {
        while (true) {
            System.out.print("Enter 7-Eleven code or cancel/exit: ");
            String code = sc.nextLine();
            if (code.equalsIgnoreCase("cancel")) {
                return null;
            }
            if (code.equalsIgnoreCase("exit")) {
                System.exit(0);
            }
            if (!code.isBlank()) {
                // Valid code entered
                return "7-Eleven";
            }
            // Prompt again if input was empty
            System.out.println("Cannot be empty.");
        }
    }

    /**
     * Prompts the user for an Apple Pay ID. Ensures input is non-empty,
     * supports cancel/exit commands, and returns the payment method.
     *
     * @param sc Scanner object for reading console input
     * @return the string "Apple Pay" if ID entered, or null if cancelled
     */
    public static String payWithApplePay(Scanner sc) {
        while (true) {
            System.out.print("Enter Apple Pay ID or cancel/exit: ");
            String id = sc.nextLine();
            if (id.equalsIgnoreCase("cancel")) {
                return null;
            }
            if (id.equalsIgnoreCase("exit")) {
                System.exit(0);
            }
            if (!id.isBlank()) {
                // Valid Apple Pay identifier entered
                return "Apple Pay";
            }
            System.out.println("Cannot be empty.");
        }
    }

    /**
     * Prompts the user for a Google Pay ID. Ensures input is non-empty,
     * supports cancel/exit commands, and returns the payment method.
     *
     * @param sc Scanner object for reading console input
     * @return the string "Google Pay" if ID entered, or null if cancelled
     */
    public static String payWithGooglePay(Scanner sc) {
        while (true) {
            System.out.print("Enter Google Pay ID or cancel/exit: ");
            String id = sc.nextLine();
            if (id.equalsIgnoreCase("cancel")) {
                return null;
            }
            if (id.equalsIgnoreCase("exit")) {
                System.exit(0);
            }
            if (!id.isBlank()) {
                // Valid Google Pay identifier entered
                return "Google Pay";
            }
            System.out.println("Cannot be empty.");
        }
    }
}
