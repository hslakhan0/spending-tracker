// Spending tracker
// A command-line tool that will track purchases based off your category.

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

public class Main {

    // Program will start here and greet the user at the system.out.println
    public static void main(String[] args) {


        // Holds any purchase that the user inputs
        ArrayList<Purchase> purchases = new ArrayList<>();
        ArrayList<String> categories = new ArrayList<>();

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
                // Ask for amount and make it be a double
                String amountText = IO.readln("Amount: ");
                double amount = Double.parseDouble(amountText);

                // Ask what the purchase was for
                String category = IO.readln("Category: ");

                // If it detects the purchase is being added to a new category, it will create a new category
                if (!categories.contains(category)) {
                    categories.add(category);
                }

                // Ask for the date and it needs to be MM-DD-YYYY.
                // Java defaults as YYYY-MM-DD so I added DateTimeFormatter
                String dateText = IO.readln("Date (MM-DD-YYYY): ");
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");
                LocalDate date = LocalDate.parse(dateText, dateFormat);

                //Purchases are being saved in the list
                Purchase purchase = new Purchase(amount, category, date);
                purchases.add(purchase);
                System.out.println("Purchase added! Total purchases: " + purchases.size());




            } else if (choice.equals("2")) {
                // Ask for the new category's name
                String newCategory = IO.readln("New category name: ");

                // Add new category only if it doesn't already exist.
                if (categories.contains(newCategory)) {
                    System.out.println(newCategory + " already exists.");
                } else {
                    categories.add(newCategory);
                    System.out.println("Added category: " + newCategory);
                }


            } else if (choice.equals("3")) {
                // Get today's date so it knows which month to get information on.
                LocalDate today = LocalDate.now();

                System.out.println("Spending this month:");

                // Go through every purchase in each category and add all of them up.
                for (String category : categories) {
                    double total = 0;

                    for (Purchase purchase : purchases) {
                        LocalDate date = purchase.getDate();

                        // Check if the puchase is from the month and year it is checking for.
                        boolean sameCategory = purchase.getCategory().equals(category);
                        boolean sameMonth = date.getMonthValue() == today.getMonthValue();
                        boolean sameYear = date.getYear() == today.getYear();

                        if (sameCategory && sameMonth && sameYear) {
                            total = total + purchase.getAmount();
                        }
                    }

                    // After checking purcahses, print the category's total.
                    System.out.println(category + ": $" + String.format("%.2f", total));
                }


            } else if (choice.equals("4")) {

                // Get today's date so we know what month it is right now
                LocalDate today = LocalDate.now();

                // Start with no top category and have all at $0, replace everytime a bigger total shows up.
                String topCategory = "";
                double topTotal = 0;

                // For each category, go through each purchase and add up all the purchases in it.
                for (String category : categories) {
                    double total = 0;

                // For each category, go through each purcahse
                    for (Purchase purchase : purchases) {
                        LocalDate date = purchase.getDate();

                        // Check if this purchase is in this category and from this month and year
                        boolean sameCategory = purchase.getCategory().equals(category);
                        boolean sameMonth = date.getMonthValue() == today.getMonthValue();
                        boolean sameYear = date.getYear() == today.getYear();

                        if (sameCategory && sameMonth && sameYear) {
                            total = total + purchase.getAmount();
                        }
                    }

                    // If this category spent more than the biggest one so far, this category becomes the new top
                    if (total > topTotal) {
                        topCategory = category;
                        topTotal = total;
                    }
                }

                // Show the top category, or a message if nothing was spent this month
                if (topCategory.equals("")) {
                    System.out.println("No spending this month yet.");
                } else {
                    System.out.println("Top category: " + topCategory + " ($" + String.format("%.2f", topTotal) + ")");
                }







            } else if (choice.equals("5")) {
            // Save every purchase to purchase.csv so they save until the next time.

            try {
                PrintWriter writer = new PrintWriter("purchases.csv");

                // Write one line for each purchase added including date, category, and price. Ex: (11-28-2026, Food, 12.50)
                for (Purchase purchase : purchases) {
                    writer.println(purchase.getDate() + ", " + purchase.getCategory() + ", " + purchase.getAmount());
                }

                writer.close();
                System.out.println("Saved " + purchases.size() + " purchases.");
            } catch (FileNotFoundException e) {
                // If the file can't be made, tell the user instead of completely crashing
                System.out.println("Couldn't save purchases.csv.");
            }


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

