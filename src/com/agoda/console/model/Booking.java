package com.agoda.console.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Booking represents a hotel reservation made by a user, including
 * details such as room, stay dates, selected options, and payment method.
 */
public class Booking {
    // User who made the booking
    private User user;
    // Hotel associated with this booking
    private Hotel hotel;
    // Room reserved by the user
    private Room room;
    // Additional options chosen by the user (e.g., breakfast, airport pickup)
    private String[] chosenOptions;
    // Check-in date for the reservation
    private LocalDate checkIn;
    // Check-out date for the reservation
    private LocalDate checkOut;
    // Payment method used for the booking (e.g., credit card, PayPal)
    private String paymentMethod;

    /**
     * Constructs a new Booking with the specified details.
     *
     * @param user the user making the booking
     * @param hotel the hotel being booked
     * @param room the specific room reserved
     * @param chosenOptions additional options selected by the user
     * @param checkIn the start date of the stay
     * @param checkOut the end date of the stay
     * @param paymentMethod the payment method used
     */
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

    /**
     * Calculates the total price for the stay by multiplying
     * the number of nights between check-in and check-out
     * with the room's price per night.
     *
     * @return the total cost of the booking
     */
    public double totalPrice() {
        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
        return nights * room.getPricePerNight();
    }

    /**
     * Generates a formatted string summarizing the booking details,
     * including hotel information, room details, stay duration, and total cost.
     *
     * @return a detailed multiline booking summary
     */
    @Override
    public String toString() {
        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
        return String.format(
            "%s\nLocation: %s\nRating: ★%.1f\n\n" +
            "%s\nPrice: %.2fBaht/night %s\nFacilities: %s\nOptions: %s\n\n" +
            "Stay: %s to %s (%d nights)\nPaid via: %s\nTotal: %.2fBaht",
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
