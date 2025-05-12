package com.agoda.console.model;

/**
 * Room represents the accommodation offering within a hotel,
 * including pricing, facilities, optional add-ons, and discounts.
 */
public class Room {
    // Type or category of the room (e.g., Deluxe, Suite)
    private String type;
    // Base price per night before any discount
    private double basePrice;
    // List of facilities included in the room (e.g., Wi-Fi, TV)
    private String[] facilities;
    // Optional add-on services available for the room (e.g., breakfast, airport shuttle)
    private String[] options;
    // Discount rate as a fraction (e.g., 0.10 for 10% off), null if no discount
    private Double discount;

    /**
     * Constructs a Room with specified details.
     *
     * @param type       the name or category of the room
     * @param basePrice  the nightly base rate for the room
     * @param facilities the array of included facilities
     * @param options    the array of additional options offered
     * @param discount   the discount fraction (null if none)
     */
    public Room(String type, double basePrice, String[] facilities, String[] options, Double discount) {
        this.type = type;
        this.basePrice = basePrice;
        this.facilities = facilities;
        this.options = options;
        this.discount = discount;
    }

    /**
     * Retrieves the room type.
     *
     * @return the type or category of the room
     */
    public String getType() { return type; }

    /**
     * Retrieves the facilities included with the room.
     *
     * @return array of facility names
     */
    public String[] getFacilities() { return facilities; }

    /**
     * Retrieves the optional add-on services for the room.
     *
     * @return array of option names
     */
    public String[] getOptions() { return options; }

    /**
     * Calculates the effective price per night after applying any discount.
     *
     * @return discounted price if discount exists, otherwise base price
     */
    public double getPricePerNight() {
        return discount != null ? basePrice * (1 - discount) : basePrice;
    }

    /**
     * Generates a human-readable label for the discount.
     *
     * @return formatted discount (e.g., "10% off"), or empty string if no discount
     */
    public String getDiscountLabel() {
        return discount != null ? String.format("%.0f%% off", discount * 100) : "";
    }
}
