package com.agoda.console.service;

import com.agoda.console.model.*;
import com.agoda.console.util.DataGenerator;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class ReservationSystem {
    private List<User> users;
    private Hotel[] hotels;
    private User currentUser;
    private Scanner sc = new Scanner(System.in);

    public ReservationSystem() {
        users = new ArrayList<>(Arrays.asList(DataGenerator.createUsers()));
        hotels = DataGenerator.createHotels();
    }

    public void start() {
        System.out.println("=== Welcome to Agoda Console ===");
        loginOrRegister();
        mainMenu();
    }

    private void loginOrRegister() {
        while (true) {
            System.out.println("1) Login");
            System.out.println("2) Register");
            System.out.println("3) Exit");
            System.out.print("Choose: ");
            String in = sc.nextLine();
            if (in.equals("1")) {
                login();
                if (currentUser != null) break;
            } else if (in.equals("2")) {
                register();
                break;
            } else if (in.equals("3") || in.equalsIgnoreCase("exit")) {
                System.exit(0);
            } else {
                System.out.println("Invalid.");
            }
        }
    }

    private void login() {
        System.out.print("Email: "); String email = sc.nextLine();
        System.out.print("Password: "); String pwd = sc.nextLine();
        for (User u : users) {
            if (u.getEmail().equals(email) && u.checkPassword(pwd)) {
                currentUser = u; return;
            }
        }
        System.out.println("Invalid credentials.");
    }

    private void register() {
        System.out.print("Name: "); String name = sc.nextLine();
        System.out.print("Email: "); String email = sc.nextLine();
        System.out.print("Password: "); String pwd = sc.nextLine();
        currentUser = new User(name, email, pwd);
        users.add(currentUser);
        System.out.println("Registration complete.");
    }

    private void mainMenu() {
        while (true) {
            System.out.println("\n1) Search Hotels");
            System.out.println("2) View Bookings");
            System.out.println("3) Logout");
            System.out.print("Choose: ");
            String in = sc.nextLine();
            if (in.equals("1")) searchHotels();
            else if (in.equals("2")) viewBookings();
            else if (in.equals("3") || in.equalsIgnoreCase("cancel")) {
                currentUser = null; start(); return;
            }
            else if (in.equalsIgnoreCase("exit")) System.exit(0);
            else System.out.println("Invalid.");
        }
    }

    private void searchHotels() {
        System.out.print("Destination (city) or cancel/exit: ");
        String dest = sc.nextLine(); if (dest.equalsIgnoreCase("cancel")) return; if (dest.equalsIgnoreCase("exit")) System.exit(0);
        LocalDate cin, cout;
        try {
            System.out.print("Check-in (YYYY-MM-DD) or cancel/exit: ");
            String in = sc.nextLine(); if (in.equalsIgnoreCase("cancel")) return; if (in.equalsIgnoreCase("exit")) System.exit(0);
            cin = LocalDate.parse(in);
            System.out.print("Check-out (YYYY-MM-DD): "); in = sc.nextLine(); if (in.equalsIgnoreCase("cancel")) return; if (in.equalsIgnoreCase("exit")) System.exit(0);
            cout = LocalDate.parse(in);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date."); return;
        }
        System.out.print("Guests or cancel/exit: "); String gi = sc.nextLine(); if (gi.equalsIgnoreCase("cancel")) return; if (gi.equalsIgnoreCase("exit")) System.exit(0);
        int guests; try { guests = Integer.parseInt(gi); } catch (Exception e) { System.out.println("Invalid."); return; }
        List<Hotel> found = new ArrayList<>(); for (Hotel h : hotels) if (h.getLocation().equalsIgnoreCase(dest)) found.add(h);
        if (found.isEmpty()) { System.out.println("No hotels in " + dest); return; }
        for (int i = 0; i < found.size(); i++) {
            Hotel h = found.get(i);
            System.out.printf("%d) %s%nLocation: %s%nRating: ★%.1f %n%n", i+1, h.getName(), h.getLocation(), h.getRating());
        }
        System.out.print("Choose hotel # or cancel/exit: "); String hi = sc.nextLine(); if (hi.equalsIgnoreCase("cancel")) return; if (hi.equalsIgnoreCase("exit")) System.exit(0);
        int idx; try { idx = Integer.parseInt(hi)-1; } catch (NumberFormatException e) { System.out.println("Invalid."); return; }
        if (idx < 0 || idx >= found.size()) { System.out.println("Invalid."); return; }
        bookRoom(found.get(idx), cin, cout);
    }

    private void bookRoom(Hotel h, LocalDate cin, LocalDate cout) {
        Room[] rooms = h.getRooms();
        for (int i = 0; i < rooms.length; i++) {
            Room r = rooms[i];
            System.out.printf("%d) %s%nPrice: $%.2f/night %s%nFacilities: %s%nOptions: %s%n%n", i+1,
                r.getType(), r.getPricePerNight(),
                r.getDiscountLabel().isEmpty() ? "" : "(" + r.getDiscountLabel() + ")",
                String.join(", ", r.getFacilities()),
                String.join(", ", r.getOptions())
            );
        }
        System.out.print("Choose room # or cancel/exit: "); String ri = sc.nextLine(); if (ri.equalsIgnoreCase("cancel")) return; if (ri.equalsIgnoreCase("exit")) System.exit(0);
        int rid; try { rid = Integer.parseInt(ri)-1; } catch (NumberFormatException e) { System.out.println("Invalid."); return; }
        Room r = rooms[rid];
        System.out.println("Select options (comma-separated numbers) or press ENTER for none:");
        for (int i = 0; i < r.getOptions().length; i++) System.out.printf("%d) %s%n", i+1, r.getOptions()[i]);
        String oi = sc.nextLine(); if (oi.equalsIgnoreCase("cancel")) return; if (oi.equalsIgnoreCase("exit")) System.exit(0);
        String[] chosen;
        if (oi.isBlank()) chosen = new String[0];
        else {
            List<String> sel = new ArrayList<>();
            for (String p : oi.split(",")) {
                try { sel.add(r.getOptions()[Integer.parseInt(p.trim())-1]); } catch (Exception ex) { }
            }
            chosen = sel.toArray(new String[0]);
        }
        System.out.print("\nPayment Methods:\n1) VISA\n2) PayPal\n3) 7-Eleven\n4) Apple Pay\n5) Google Pay\nChoose or cancel/exit: ");
        String pi = sc.nextLine();
        String pay = switch (pi) {
            case "1" -> PaymentService.payWithVisa(sc);
            case "2" -> PaymentService.payWithPayPal(sc);
            case "3" -> PaymentService.payWith7Eleven(sc);
            case "4" -> PaymentService.payWithApplePay(sc);
            case "5" -> PaymentService.payWithGooglePay(sc);
            case "cancel" -> null;
            case "exit" -> { System.exit(0); yield null; }
            default -> { System.out.println("Invalid."); yield null; }
        };
        if (pay == null) return;
        Booking b = new Booking(currentUser, h, r, chosen, cin, cout, pay);
        currentUser.addBooking(b);
        System.out.println("\n✔ Booking Confirmed:\n" + b + "\n");
    }

    private void viewBookings() {
        List<Booking> bk = currentUser.getBookings();
        if (bk.isEmpty()) System.out.println("No bookings yet.");
        else bk.forEach(b -> System.out.println("\n" + b + "\n"));
    }
}