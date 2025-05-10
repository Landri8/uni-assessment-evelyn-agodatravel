package com.agoda.console.model;

public class Room {
    private String type;
    private double basePrice;
    private String[] facilities;
    private String[] options;
    private Double discount;

    public Room(String type, double basePrice, String[] facilities, String[] options, Double discount) {
        this.type = type;
        this.basePrice = basePrice;
        this.facilities = facilities;
        this.options = options;
        this.discount = discount;
    }

    public String getType() { return type; }
    public String[] getFacilities() { return facilities; }
    public String[] getOptions() { return options; }
    public double getPricePerNight() {
        return discount != null ? basePrice * (1 - discount) : basePrice;
    }
    public String getDiscountLabel() {
        return discount != null ? String.format("%.0f%% off", discount * 100) : "";
    }
}