package ExpenseTracker;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ExpenseTrackerPgm {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<Expense> expenses = new ArrayList<>();

    static int nextId = 1001;

    public static void main(String[] args) {

        while (true) {

            displayMenu();

            int choice = readInt("Enter your choice: ");

            switch (choice) {

            case 1:
                addExpense();
                break;

            case 2:
                displayAllExpenses();
                break;

            case 3:
                deleteExpense();
                break;

            case 4:
                calculateTotalExpense();
                break;

            case 5:
                findHighestExpense();
                break;

            case 6:
                categoryWiseExpense();
                break;

            case 7:
                searchByCategory();
                break;

            case 8:
                searchByDate();
                break;

            case 9:
                monthlySummary();
                break;

            case 10:
                System.out.println("\nThank you for using Personal Expense Tracker!");
                sc.close();
                return;

            default:
                System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }


    // ================= MENU =================

    public static void displayMenu() {

        System.out.println("\n==========================================");
        System.out.println("       PERSONAL EXPENSE TRACKER");
        System.out.println("==========================================");

        System.out.println("1. Add Expense");
        System.out.println("2. Display All Expenses");
        System.out.println("3. Delete Expense");
        System.out.println("4. Calculate Total Expense");
        System.out.println("5. Find Highest Expense");
        System.out.println("6. Category-wise Summary");
        System.out.println("7. Search by Category");
        System.out.println("8. Search by Date");
        System.out.println("9. Monthly Summary");
        System.out.println("10. Exit");

        System.out.println("==========================================");
    }


    // ================= ADD EXPENSE =================

    public static void addExpense() {

        System.out.println("\n---------- ADD EXPENSE ----------");

        System.out.print("Enter description: ");
        String description = sc.nextLine();

        while (description.trim().isEmpty()) {

            System.out.print("Description cannot be empty. Enter again: ");
            description = sc.nextLine();
        }

        double amount;

        while (true) {

            amount = readDouble("Enter amount: ₹");

            if (amount > 0) {
                break;
            }

            System.out.println("Amount must be greater than 0.");
        }

        ExpenseCategory category = selectCategory();

        LocalDate date = readDate();

        Expense expense = new Expense(
                nextId++,
                description,
                amount,
                category,
                date
        );

        expenses.add(expense);

        System.out.println(
                "\nExpense added successfully!"
        );

        System.out.println(expense);
    }


    // ================= SELECT CATEGORY =================

    public static ExpenseCategory selectCategory() {

        System.out.println("\nSelect Category:");

        ExpenseCategory[] categories =
                ExpenseCategory.values();

        for (int i = 0; i < categories.length; i++) {

            System.out.println(
                    (i + 1) + ". " + categories[i]
            );
        }

        while (true) {

            int choice = readInt("Enter category number: ");

            if (choice >= 1 && choice <= categories.length) {

                return categories[choice - 1];
            }

            System.out.println("Invalid category.");
        }
    }


    // ================= DATE =================

    public static LocalDate readDate() {

        System.out.println("\nEnter expense date");

        System.out.print("Year: ");
        int year = sc.nextInt();

        System.out.print("Month: ");
        int month = sc.nextInt();

        System.out.print("Day: ");
        int day = sc.nextInt();

        sc.nextLine();

        try {

            return LocalDate.of(year, month, day);

        } catch (Exception e) {

            System.out.println(
                    "Invalid date. Using today's date."
            );

            return LocalDate.now();
        }
    }


    // ================= DISPLAY =================

    public static void displayAllExpenses() {

        System.out.println("\n---------- ALL EXPENSES ----------");

        if (expenses.isEmpty()) {

            System.out.println("No expenses found.");

            return;
        }

        System.out.println(
                "ID   | Description          | Amount    | Category        | Date"
        );

        System.out.println(
                "---------------------------------------------------------------------"
        );

        for (Expense expense : expenses) {

            System.out.println(expense);
        }
    }


    // ================= DELETE =================

    public static void deleteExpense() {

        if (expenses.isEmpty()) {

            System.out.println("No expenses available.");

            return;
        }

        int id = readInt("Enter Expense ID to delete: ");

        boolean removed = expenses.removeIf(
                expense -> expense.getExpenseId() == id
        );

        if (removed) {

            System.out.println(
                    "Expense " + id + " deleted successfully."
            );

        } else {

            System.out.println(
                    "Expense ID " + id + " not found."
            );
        }
    }


    // ================= TOTAL =================

    public static void calculateTotalExpense() {

        double total = expenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();

        System.out.println(
                "\nTotal Expense: ₹" + total
        );
    }


    // ================= HIGHEST =================

    public static void findHighestExpense() {

        if (expenses.isEmpty()) {

            System.out.println("No expenses available.");

            return;
        }

        Expense highest = expenses.stream()
                .max((e1, e2) ->
                        Double.compare(
                                e1.getAmount(),
                                e2.getAmount()
                        ))
                .orElse(null);

        System.out.println("\n---------- HIGHEST EXPENSE ----------");

        System.out.println(highest);
    }


    // ================= CATEGORY SUMMARY =================

    public static void categoryWiseExpense() {

        if (expenses.isEmpty()) {

            System.out.println("No expenses available.");

            return;
        }

        Map<ExpenseCategory, Double> categorySummary =
                expenses.stream()
                        .collect(Collectors.groupingBy(
                                Expense::getCategory,
                                Collectors.summingDouble(
                                        Expense::getAmount
                                )
                        ));

        System.out.println(
                "\n---------- CATEGORY-WISE SUMMARY ----------"
        );

        for (Map.Entry<ExpenseCategory, Double> entry
                : categorySummary.entrySet()) {

            System.out.printf(
                    "%-15s : ₹%.2f%n",
                    entry.getKey(),
                    entry.getValue()
            );
        }
    }


    // ================= SEARCH CATEGORY =================

    public static void searchByCategory() {

        ExpenseCategory category = selectCategory();

        System.out.println(
                "\n---------- " + category + " EXPENSES ----------"
        );

        boolean found = false;

        for (Expense expense : expenses) {

            if (expense.getCategory() == category) {

                System.out.println(expense);

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "No expenses found for " + category
            );
        }
    }


    // ================= SEARCH DATE =================

    public static void searchByDate() {

        LocalDate date = readDate();

        System.out.println(
                "\n---------- EXPENSES ON " + date + " ----------"
        );

        var result = expenses.stream()
                .filter(expense ->
                        expense.getDate().equals(date))
                .collect(Collectors.toList());

        if (result.isEmpty()) {

            System.out.println(
                    "No expenses found on " + date
            );

        } else {

            result.forEach(System.out::println);
        }
    }


    // ================= MONTHLY SUMMARY =================

    public static void monthlySummary() {

        System.out.println("\n---------- MONTHLY SUMMARY ----------");

        int year = readInt("Enter year: ");

        int month = readInt("Enter month (1-12): ");

        try {

            YearMonth selectedMonth =
                    YearMonth.of(year, month);

            double total = expenses.stream()
                    .filter(expense ->
                            YearMonth.from(
                                    expense.getDate()
                            ).equals(selectedMonth))
                    .mapToDouble(Expense::getAmount)
                    .sum();

            System.out.println(
                    "Month: " + selectedMonth
            );

            System.out.println(
                    "Total Expense: ₹" + total
            );

        } catch (Exception e) {

            System.out.println("Invalid year or month.");
        }
    }


    // ================= INPUT METHODS =================

    public static int readInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                int value = sc.nextInt();

                sc.nextLine();

                return value;

            } catch (Exception e) {

                System.out.println(
                        "Please enter a valid number."
                );

                sc.nextLine();
            }
        }
    }


    public static double readDouble(String message) {

        while (true) {

            try {

                System.out.print(message);

                double value = sc.nextDouble();

                sc.nextLine();

                return value;

            } catch (Exception e) {

                System.out.println(
                        "Please enter a valid amount."
                );

                sc.nextLine();
            }
        }
    }
}