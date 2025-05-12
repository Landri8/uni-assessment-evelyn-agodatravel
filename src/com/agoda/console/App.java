package com.agoda.console;

import com.agoda.console.service.ReservationSystem;

/**
 * App serves as the entry point for the Agoda Console application.
 * It initializes and launches the reservation system.
 */
public class App {
    /**
     * Main method - application entry point.
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Create an instance of ReservationSystem and start the reservation flow
        new ReservationSystem().start();
    }
}
