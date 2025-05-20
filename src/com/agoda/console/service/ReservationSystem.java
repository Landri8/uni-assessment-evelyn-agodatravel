package com.agoda.console.service;

import com.agoda.console.model.*;
import com.agoda.console.util.DataGenerator;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

/*
  ReservationSystem manages the console-based user flow for:
  - User authentication (login/register)
  - Searching hotels by location and dates
  - Selecting rooms and options
  - Processing payment
  - Confirming and viewing bookings
*/
public class ReservationSystem {
    // List of registered users loaded from data generator
    private List<User> users;
    // Array of hotels available for booking
    private Hotel[] hotels;
    // Currently authenticated user
    private User currentUser;
    // Scanner for reading console input
    private Scanner sc = new Scanner(System.in);

   // Initializes the reservation system with sample users and hotels.
    public ReservationSystem() {
        // Load users and hotels with test data
        users = new ArrayList<>(Arrays.asList(DataGenerator.createUsers()));
        hotels = DataGenerator.createHotels();
    }

    //Starts the application: shows welcome message, handles login/registration, then shows main menu.
    public void start() {
        System.out.println("=== Welcome to Agoda ===");
        loginOrRegister();
        while (currentUser == null) {
        	loginOrRegister();
        }
        mainMenu();
    }

    /*
      Prompts the user to either login, register, or exit.
      Loops until a user is authenticated or registers.
     */
    private void loginOrRegister() {
        while (true) {
	    System.out.println();
            System.out.println("1) Login");
            System.out.println("2) Register");
            System.out.println("3) Exit");
            System.out.print("Choose: ");
            String in = sc.nextLine();
            if (in.equals("1")) {
                login();
                if (currentUser != null) break; // successful login
            } else if (in.equals("2")) {
                register();
                break; // registered user becomes current
            } else if (in.equals("3") || in.equalsIgnoreCase("exit")) {
                System.exit(0); // terminate application
            } else {
                System.out.println("Invalid.");
            }
        }
    }

    /*
      Attempts to authenticate a user by email and password.
      Sets currentUser if successful; otherwise, shows error.
     */
    private void login() {
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String pwd = sc.nextLine();
        for (User u : users) {
            if (u.getEmail().equals(email) && u.checkPassword(pwd)) {
                currentUser = u;
                return;
            }
        }
        System.out.println("Invalid credentials.");
    }

  //    Registers a new user by collecting name, email, and password, adds them to the users list, and sets them as currentUser.
    private void register() {
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        
        Boolean emailExisted = false;
        
        for (User user : users) {
        	if (user.getEmail().equals(email)) {
        		emailExisted = true;
        	}
        }
        
        System.out.print("Password: ");
        String pwd = sc.nextLine();
        
        if (emailExisted) {
        	System.out.println("User with this email already existed. Please try again!");
        	
        	loginOrRegister();
        	return;
        }
        
        User newUser = new User(name, email, pwd);
        users.add(newUser);
        System.out.println("Registration complete.");
    }

    
    private void mainMenu() { //main menu for authenticated user
        while (true) {
            System.out.println("\n1) Search Hotels");
            System.out.println("2) View Bookings");
            System.out.println("3) Logout");
            System.out.print("Choose: ");
            String in = sc.nextLine();
            if (in.equals("1")) {
                searchHotels();
            } else if (in.equals("2")) {
                viewBookings();
            } else if (in.equals("3") || in.equalsIgnoreCase("cancel")) {
                // Log out and restart flow
                currentUser = null;
                start();
                return;
            } else if (in.equalsIgnoreCase("exit")) {
                System.exit(0);
            } else {
                System.out.println("Invalid.");
            }
        }
    }
    /*
      Handles hotel search by destination, dates, and number of guests.
      Displays matching hotels, then prompts to select one for booking.
     */
    private void searchHotels() {
        System.out.print("Destination (city) or cancel/exit: ");
        String dest = sc.nextLine();
        if (dest.equalsIgnoreCase("cancel")) return;
        if (dest.equalsIgnoreCase("exit")) System.exit(0);

        LocalDate cin, cout;
        try {
            System.out.print("Check-in (YYYY-MM-DD) or cancel/exit: ");
            String in = sc.nextLine();
            if (in.equalsIgnoreCase("cancel")) return;
            if (in.equalsIgnoreCase("exit")) System.exit(0);
            cin = LocalDate.parse(in);

            System.out.print("Check-out (YYYY-MM-DD): ");
            in = sc.nextLine();
            if (in.equalsIgnoreCase("cancel")) return;
            if (in.equalsIgnoreCase("exit")) System.exit(0);
            cout = LocalDate.parse(in);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date.");
            return;
        }

        System.out.print("Guests or cancel/exit: ");
        String gi = sc.nextLine();
        if (gi.equalsIgnoreCase("cancel")) return;
        if (gi.equalsIgnoreCase("exit")) System.exit(0);

        int guests;
        try {
            guests = Integer.parseInt(gi);
        } catch (Exception e) {
            System.out.println("Invalid.");
            return;
        }

        // Filter hotels by matching location
        List<Hotel> found = new ArrayList<>();
        for (Hotel h : hotels) {
            if (h.getLocation().equalsIgnoreCase(dest)) {
                found.add(h);
            }
        }
        if (found.isEmpty()) {
            System.out.println("No hotels in " + dest);
            return;
        }

        // Display available hotels
        for (int i = 0; i < found.size(); i++) {
            Hotel h = found.get(i);
            System.out.printf("%d) %s%nLocation: %s%nRating: ★%.1f %n%n",
                i+1, h.getName(), h.getLocation(), h.getRating());
        }

        // Prompt to choose a hotel
        System.out.print("Choose hotel # or cancel/exit: ");
        String hi = sc.nextLine();
        if (hi.equalsIgnoreCase("cancel")) return;
        if (hi.equalsIgnoreCase("exit")) System.exit(0);

        int idx;
        try {
            idx = Integer.parseInt(hi) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid.");
            return;
        }
        if (idx < 0 || idx >= found.size()) {
            System.out.println("Invalid.");
            return;
        }
        // Proceed to booking a room in the selected hotel
        bookRoom(found.get(idx), cin, cout);
    }

    /**
     * Displays room options for the given hotel and dates,
     * allows selection of room and additional options,
     * processes payment, and confirms the booking.
      */
    private void bookRoom(Hotel h, LocalDate cin, LocalDate cout) {
        Room[] rooms = h.getRooms();
        // List available rooms
        for (int i = 0; i < rooms.length; i++) {
            Room r = rooms[i];
            System.out.printf("%d) %s%nPrice: %.2fBaht/night %s%nFacilities: %s%nOptions: %s%n%n",
                i+1,
                r.getType(),
                r.getPricePerNight(),
                r.getDiscountLabel().isEmpty() ? "" : "(" + r.getDiscountLabel() + ")",
                String.join(", ", r.getFacilities()),
                String.join(", ", r.getOptions())
            );
        }
        System.out.print("Choose room # or cancel/exit: ");
        String ri = sc.nextLine();
        if (ri.equalsIgnoreCase("cancel")) return;
        if (ri.equalsIgnoreCase("exit")) System.exit(0);

        int rid;
        try {
            rid = Integer.parseInt(ri) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid.");
            return;
        }
        Room r = rooms[rid];

        // Select optional add-ons
        System.out.println("Select options (comma-separated numbers) or press ENTER for none:");
        for (int i = 0; i < r.getOptions().length; i++) {
            System.out.printf("%d) %s%n", i+1, r.getOptions()[i]);
        }
        String oi = sc.nextLine();
        if (oi.equalsIgnoreCase("cancel")) return;
        if (oi.equalsIgnoreCase("exit")) System.exit(0);

        String[] chosen;
        if (oi.isBlank()) {
            chosen = new String[0];
        } else {
            List<String> sel = new ArrayList<>();
            for (String p : oi.split(",")) {
                try {
                    sel.add(r.getOptions()[Integer.parseInt(p.trim()) - 1]);
                } catch (Exception ex) {
                    // ignore invalid selections
                }
            }
            chosen = sel.toArray(new String[0]);
        }

        // Prompt for payment method
        System.out.print("\nPayment Methods:\n1) VISA\n2) 7-Eleven\n3) Apple Pay\n4) Google Pay\nChoose or cancel/exit: ");
        String pi = sc.nextLine();
        String pay = switch (pi) {
            case "1" -> PaymentService.payWithVisa(sc);
            case "2" -> PaymentService.payWith7Eleven(sc);
            case "3" -> PaymentService.payWithApplePay(sc);
            case "4" -> PaymentService.payWithGooglePay(sc);
            case "cancel" -> null;
            case "exit" -> { System.exit(0); yield null; }
            default -> { System.out.println("Invalid."); yield null; }
        };
        if (pay == null) return;

        // Create and store the booking
        Booking b = new Booking(currentUser, h, r, chosen, cin, cout, pay);
        currentUser.addBooking(b);
        System.out.println("\n✔ Booking Confirmed:\n" + b + "\n");
        
        // Ask user to make another booking. If no, exit the program
        System.out.print("Would you like to make another booking? (yes/no): ");
        String again = sc.nextLine().trim().toLowerCase();
        if (again.equals("yes") || again.equals("y")) {
            // go back to main menu
            mainMenu();
        } else {
            System.out.println("Thank you for using Agoda Console! Goodbye.");
            System.exit(0);
        }
    }
    // Displays all bookings for the current user, or a message if none exist.
    private void viewBookings() {
        List<Booking> bk = currentUser.getBookings();
        if (bk.isEmpty()) {
            System.out.println("No bookings yet.");
        } else {
            bk.forEach(b -> System.out.println("\n" + b + "\n"));
        }
    }
}
