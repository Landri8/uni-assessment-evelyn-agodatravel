package com.agoda.console.util;

import com.agoda.console.model.*;
import java.util.*;

public class DataGenerator {
    private static final Random rand = new Random();

    public static User[] createUsers() {
        return new User[] {
            new User("Alice","alice@example.com","pass123"),
            new User("Bob","bob@example.com","pass456")
        };
    }

    public static Hotel[] createHotels() {
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
        for (String e : entries) {
            String[] p = e.split(", ");
            double rating = Math.round((3.5 + rand.nextDouble()*1.5) * 10) / 10.0;
            list.add(new Hotel(p[0], p[1], rating, generateRooms()));
        }
        return list.toArray(new Hotel[0]);
    }

    private static Room[] generateRooms() {
        return new Room[] {
            new Room("Single Room", 50,
                new String[]{"Wi-Fi","TV","Gym Access","Breakfast"},
                new String[]{"Larger bed","City view","Late checkout","Extra pillows"},
                0.10),
            new Room("Double Room", 80,
                new String[]{"Wi-Fi","Mini-bar","Pool access"},
                new String[]{"Balcony","High floor","Airport pickup"},
                null),
            new Room("Suite", 150,
                new String[]{"Wi-Fi","Kitchenette","Spa access"},
                new String[]{"Ocean view","Jacuzzi","Private lounge"},
                0.15),
            new Room("Penthouse", 300,
                new String[]{"Wi-Fi","Private pool","Butler service"},
                new String[]{"Exclusive view","Helipad access","Complimentary minibar"},
                0.20)
        };
    }
}