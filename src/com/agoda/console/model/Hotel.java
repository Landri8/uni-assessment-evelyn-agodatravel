package com.agoda.console.model;

/**
 * Hotel represents a lodging entity with basic information
 * including its name, location, guest rating, and available rooms.
 */
public class Hotel {
    // The hotel's display name
    private String name;
    // Geographical location or address of the hotel
    private String location;
    // Average guest rating (e.g., 4.5 for ★4.5)
    private double rating;
    // Array of rooms that this hotel offers
    private Room[] rooms;

    /**
     * Constructs a new Hotel with the specified details.
     *
     * @param name     name of the hotel
     * @param location location or address of the hotel
     * @param rating   average guest rating for the hotel
     * @param rooms    array of Room objects available at the hotel
     */
    public Hotel(String name, String location, double rating, Room[] rooms) {
        this.name = name;
        this.location = location;
        this.rating = rating;
        this.rooms = rooms;
    }

    /**
     * Retrieves the hotel's name.
     *
     * @return the name of the hotel
     */
    public String getName() { return name; }

    /**
     * Retrieves the hotel's location.
     *
     * @return the location of the hotel
     */
    public String getLocation() { return location; }

    /**
     * Retrieves the hotel's average rating.
     *
     * @return the guest rating of the hotel
     */
    public double getRating() { return rating; }

    /**
     * Retrieves the array of rooms available at the hotel.
     *
     * @return array of Room objects
     */
    public Room[] getRooms() { return rooms; }
}
