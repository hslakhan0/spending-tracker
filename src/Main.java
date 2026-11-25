// Spending tracker
// A command-line tool that will track purchases based off your category.

import java.time.LocalDate;

public class Main {

    // Program will start here and greet the user at the system.out.println
    public static void main(String[] args) {

        System.out.println("Welcome to HL's Spending Tracker!");

        // The menu will be running until the user quits
        boolean running = true;

        while (running) {

            // Show the menu options after being welcomed
            System.out.println();
            System.out.println("1. Add a purchase");
            System.out.println("2. Add a category");
            System.out.println("3. View totals");
            System.out.println("4. View top category");
            System.out.println("5. Quit");


            // Get user's choice on what to do in the menu
            String choice = IO.readln("Choose and option: ");


            // Do what the user picked
            if (choice.equals("1")) {
                System.out.println("Adding purchases placeholder");
            } else if (choice.equals("2")) {
                System.out.println("Adding categories placeholder");
            } else if (choice.equals("3")) {
                System.out.println("Totals placeholder");
            } else if (choice.equals("4")) {
                System.out.println("Top category placeholder");
            } else if (choice.equals("5")) {
                System.out.println("Goodbye!");
                running = false;
            } else {
                System.out.println("Please pick 1, 2, 3, 4, or 5.");
            }
        }

    }
}

// User enters a purchase.

class Purchase {

    // What the purchase will keep track of
    private double amount; // this will show the amount spent.
    private String category; // this will show which category the amount spent goes into.
    private LocalDate date; // this will show what date the money was spent.

    // New purchase is being based off the user's info
    public Purchase (double amount, String category, LocalDate date) {
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    // Returns the amount that was spent
    public double getAmount() {
        return amount;
    }

    // Returns which category the purchase was for
    public String getCategory() {
        return category;
    }

    // Returns when the purchase happened
    public LocalDate getDate() {
        return date;
    }
}

