package com.agoda.console.model;

import java.util.ArrayList;
import java.util.List;

/**
 * User represents a customer of the Agoda console application,
 * storing personal credentials and tracking their bookings.
 */
public class User {
    // Full name of the user
    private String name;
    // Email address used for contact and login
    private String email;
    // Password for user authentication
    private String password;
    // List of bookings associated with this user
    private List<Booking> bookings = new ArrayList<>();

    /**
     * Constructs a new User with specified name, email, and password.
     *
     * @param name     the user's full name
     * @param email    the user's email address
     * @param password the user's login password
     */
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * Retrieves the user's full name.
     *
     * @return the name of the user
     */
    public String getName() { return name; }

    /**
     * Retrieves the user's email address.
     *
     * @return the email of the user
     */
    public String getEmail() { return email; }

    /**
     * Verifies if the provided password matches the stored password.
     *
     * @param pwd the password to validate
     * @return true if the password matches, false otherwise
     */
    public boolean checkPassword(String pwd) { return password.equals(pwd); }

    /**
     * Adds a booking to this user's booking list.
     *
     * @param b the booking to add
     */
    public void addBooking(Booking b) { bookings.add(b); }

    /**
     * Retrieves all bookings made by this user.
     *
     * @return a list of Booking objects for this user
     */
    public List<Booking> getBookings() { return bookings; }
}
