package com.agoda.console.util;

import com.agoda.console.model.*;
import java.util.*;

/**
 * DataGenerator provides sample data for the application, including users and hotels,
 * and generates randomized ratings and room offerings.
 */
public class DataGenerator {
    // Shared Random instance for generating pseudo-random values
    private static final Random rand = new Random();

    /**
     * Creates an array of sample users for testing and demonstration purposes.
     *
     * @return array of User objects with preset credentials
     */
    public static User[] createUsers() {
        return new User[] {
            new User("Alice","alice@example.com","pass123"),
            new User("Bob","bob@example.com","pass456")
        };
    }

    /**
     * Generates a list of sample hotels with randomized ratings and predefined locations,
     * then returns them as an array.
     *
     * @return array of Hotel objects populated with sample data
     */
    public static Hotel[] createHotels() {
        // Predefined hotel entries in "Name, City" format
        String[] entries = {
            "The Peninsula, Bangkok", "Mandarin Oriental, Bangkok", "Siam Kempinski, Bangkok",
            "JW Marriott, Phuket", "Four Seasons, Chiang Mai", "Anantara Riverside, Bangkok",
            "Le Meridien, Chiang Rai", "Sofitel Sukhumvit, Bangkok", "Holiday Inn, Pattaya",
            "Amari Watergate, Bangkok", "U Sathorn, Bangkok", "Okura Prestige, Bangkok",
            "Sheraton Grande, Bangkok", "Hyatt Regency, Hua Hin", "Dusit Thani, Krabi",
            "Avani Riverside, Bangkok", "The Athenee, Bangkok", "The Standard, Bangkok",
            "Rosewood, Bangkok", "Kimpton Maa-Lai, Bangkok", "137 Pillars Suites, Bangkok",
            "COMO Metropolitan, Bangkok", "Banyan Tree, Bangkok", "Park Hyatt, Bangkok",
            "SO Sofitel, Bangkok", "Akyra Thonglor, Bangkok", "Sindhorn Midtown, Bangkok",
            "The Sukhothai, Bangkok", "VIE Hotel, Bangkok", "The Landmark, Bangkok",
            "Waldorf Astoria, Bangkok", "Conrad, Manila", "Capella, Hanoi",
            "InterContinental, Kuala Lumpur", "Hotel Mulia, Jakarta", "Raffles Hotel, Singapore",
            "Marina Bay Sands, Singapore", "Shangri-La, Yangon", "Anantara Siam, Bangkok",
            "Grand Hyatt, Bangkok", "Holiday Inn, Odessa", "Travelodge, Chiang Mai"
        };
        List<Hotel> list = new ArrayList<>();
        // Convert each entry into a Hotel object with random rating and generated rooms
        for (String e : entries) {
            String[] parts = e.split(", ");
            // Generate a rating between 3.5 and 5.0 (rounded to one decimal)
            double rating = Math.round((3.5 + rand.nextDouble() * 1.5) * 10) / 10.0;
            list.add(new Hotel(parts[0], parts[1], rating, generateRooms()));
        }
        // Convert List to array and return
        return list.toArray(new Hotel[0]);
    }

    /**
     * Generates an array of Room objects with preset types, base prices,
     * facilities, options, and optional discounts.
     *
     * @return array of Room offerings for a hotel
     */
    private static Room[] generateRooms() {
        return new Room[] {
            new Room(
                "Single Room",
                1750,
                new String[]{"Wi-Fi","TV","Gym Access","Breakfast"},
                new String[]{"Larger bed","City view","Late checkout","Extra pillows"},
                0.10 // 10% discount
            ),
            new Room(
                "Double Room",
                2800,
                new String[]{"Wi-Fi","Mini-bar","Pool access"},
                new String[]{"Balcony","High floor","Airport pickup"},
                null // no discount
            ),
            new Room(
                "Suite",
                5250,
                new String[]{"Wi-Fi","Kitchenette","Spa access"},
                new String[]{"Ocean view","Jacuzzi","Private lounge"},
                0.15 // 15% discount
            ),
            new Room(
                "Penthouse",
                10500,
                new String[]{"Wi-Fi","Private pool","Butler service"},
                new String[]{"Exclusive view","Helipad access","Complimentary minibar"},
                0.20 // 20% discount
            )
        };
    }
}
