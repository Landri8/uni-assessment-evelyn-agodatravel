package com.agoda.console.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Booking {
    private User user;
    private Hotel hotel;
    private Room room;
    private String[] chosenOptions;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String paymentMethod;

    public Booking(User user, Hotel hotel, Room room, String[] chosenOptions,
                   LocalDate checkIn, LocalDate checkOut, String paymentMethod) {
        this.user = user;
        this.hotel = hotel;
        this.room = room;
        this.chosenOptions = chosenOptions;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.paymentMethod = paymentMethod;
    }

    public double totalPrice() {
        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
        return nights * room.getPricePerNight();
    }

    @Override
    public String toString() {
        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
        return String.format(
            "%s\nLocation: %s\nRating: ★%.1f\n\n" +
            "%s\nPrice: $%.2f/night %s\nFacilities: %s\nOptions: %s\n\n" +
            "Stay: %s to %s (%d nights)\nPaid via: %s\nTotal: $%.2f",
            hotel.getName(), hotel.getLocation(), hotel.getRating(),
            room.getType(), room.getPricePerNight(),
            room.getDiscountLabel().isEmpty() ? "" : "(" + room.getDiscountLabel() + ")",
            String.join(", ", room.getFacilities()),
            chosenOptions.length == 0 ? "None" : String.join(", ", chosenOptions),
            checkIn, checkOut, nights,
            paymentMethod,
            totalPrice()
        );
    }
}