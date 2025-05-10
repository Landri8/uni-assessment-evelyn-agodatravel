package com.agoda.console.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String email;
    private String password;
    private List<Booking> bookings = new ArrayList<>();

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public boolean checkPassword(String pwd) { return password.equals(pwd); }
    public void addBooking(Booking b) { bookings.add(b); }
    public List<Booking> getBookings() { return bookings; }
}