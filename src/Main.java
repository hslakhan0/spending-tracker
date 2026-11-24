// Spending tracker
// A command-line tool that will track purchases based off your category.

import java.time.LocalDate;

public class Main {

    // Program will start here and greet the user at the system.out.println
    public static void main(String[] args) {

        System.out.println("Welcome to HL's Spending Tracker!");

    }
}

// User enters a purchase.

class Purchase {

    // What the purchase will keep track of
    private double amount; 
    private String category;
    private LocalDate date;

    // New purchase is being based off the user's info
    public Purchase (double amount, String category, LocalDate date) {
        this.amount = amount;
        this.category = category;
        this.date = date;
    }
}