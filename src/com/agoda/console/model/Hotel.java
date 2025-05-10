package com.agoda.console.model;

public class Hotel {
    private String name;
    private String location;
    private double rating;
    private Room[] rooms;

    public Hotel(String name, String location, double rating, Room[] rooms) {
        this.name = name;
        this.location = location;
        this.rating = rating;
        this.rooms = rooms;
    }

    public String getName() { return name; }
    public String getLocation() { return location; }
    public double getRating() { return rating; }
    public Room[] getRooms() { return rooms; }
}
