import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExpenseTracker {

    // Enum to define categories of expenses
    enum Category {
        FOOD, TRANSPORT, ENTERTAINMENT, BILLS, OTHER
    }

    // Class to represent an expense
    static class Expense {
        String description;
        float amount;
        Category category;

        // Constructor to initialize an expense
        Expense(String description, float amount, Category category) {
            this.description = description;
            this.amount = amount;
            this.category = category;
        }

        // Method to display expense details
        void displayExpense() {
            System.out.printf("Description: %s | Category: %s | Amount: ₹%.2f\n",
                    description, category, amount);
        }
    }

    // Class to manage all expenses
    static class ExpenseManager {
        private ArrayList<Expense> expenses = new ArrayList<>();

        // Add an expense to the list
        void addExpense(String description, float amount, Category category) {
            expenses.add(new Expense(description, amount, category));
            System.out.println("Expense added successfully.");
        }

        // View all expenses
        void viewExpenses() {
            if (expenses.isEmpty()) {
                System.out.println("No expenses recorded.");
            } else {
                System.out.println("--- All Expenses ---");
                for (int i = 0; i < expenses.size(); i++) {
                    System.out.print((i + 1) + ". ");
                    expenses.get(i).displayExpense();
                }
            }
        }

        // Delete an expense by index
        void deleteExpense(int index) {
            if (index >= 0 && index < expenses.size()) {
                expenses.remove(index);
                System.out.println("Expense deleted successfully.");
            } else {
                System.out.println("Invalid index.");
            }
        }

        // Calculate and display the total expenses
        void calculateTotalExpenses() {
            if (expenses.isEmpty()) {
                System.out.println("No expenses recorded to calculate.");
            } else {
                float total = 0;
                for (Expense expense : expenses) {
                    total += expense.amount;
                }
                System.out.printf("Total Expenses: ₹%.2f\n", total);
            }
        }

        // View expenses by a specific category
        void viewExpensesByCategory(Category category) {
            boolean found = false;
            System.out.println("--- Expenses for Category: " + category + " ---");
            for (Expense expense : expenses) {
                if (expense.category == category) {
                    expense.displayExpense();
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No expenses recorded in this category.");
            }
        }

        // Calculate total expenses by category
        void calculateTotalByCategory(Category category) {
            float total = 0;
            boolean found = false;
            for (Expense expense : expenses) {
                if (expense.category == category) {
                    total += expense.amount;
                    found = true;
                }
            }
            if (found) {
                System.out.printf("Total Expenses for %s: ₹%.2f\n", category, total);
            } else {
                System.out.println("No expenses recorded in this category.");
            }
        }
    }

    // Main method to interact with the user
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseManager expenseManager = new ExpenseManager();
        int choice = 0, categoryChoice;
        String description;
        float amount;
        Category category;

        while (true) {
            System.out.println("\n--- Personal Expense Tracker ---");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Delete Expense");
            System.out.println("4. Calculate Total Expenses");
            System.out.println("5. View Expenses by Category");
            System.out.println("6. Calculate Total Expenses by Category");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next(); // Clear the invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    scanner.nextLine(); // Clear the newline
                    System.out.print("Enter Expense Description: ");
                    description = scanner.nextLine();
                    System.out.print("Enter Expense Amount (in ₹): ");
                    try {
                        amount = scanner.nextFloat();
                        if (amount <= 0) {
                            System.out.println("Amount must be positive.");
                            break;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input! Please enter a valid amount.");
                        scanner.next(); // Clear the invalid input
                        break;
                    }
                    System.out.println("Select Category: \n1. Food\n2. Transport\n3. Entertainment\n4. Bills\n5. Other");
                    System.out.print("Enter category number: ");
                    try {
                        categoryChoice = scanner.nextInt();
                        if (categoryChoice < 1 || categoryChoice > 5) {
                            System.out.println("Invalid category choice.");
                            break;
                        }
                        category = Category.values()[categoryChoice - 1]; // Adjust for 0-based index
                        expenseManager.addExpense(description, amount, category);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input! Please enter a number.");
                        scanner.next(); // Clear the invalid input
                    }
                    break;
                case 2:
                    expenseManager.viewExpenses();
                    break;
                case 3:
                    System.out.print("Enter the index of the expense to delete: ");
                    try {
                        int index = scanner.nextInt();
                        expenseManager.deleteExpense(index - 1); // Adjust for 0-based index
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input! Please enter a valid index.");
                        scanner.next(); // Clear the invalid input
                    }
                    break;
                case 4:
                    expenseManager.calculateTotalExpenses();
                    break;
                case 5:
                    System.out.println("Select Category to View: \n1. Food\n2. Transport\n3. Entertainment\n4. Bills\n5. Other");
                    System.out.print("Enter category number: ");
                    try {
                        categoryChoice = scanner.nextInt();
                        if (categoryChoice < 1 || categoryChoice > 5) {
                            System.out.println("Invalid category choice.");
                            break;
                        }
                        expenseManager.viewExpensesByCategory(Category.values()[categoryChoice - 1]);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input! Please enter a number.");
                        scanner.next(); // Clear the invalid input
                    }
                    break;
                case 6:
                    System.out.println("Select Category to Calculate Total: \n1. Food\n2. Transport\n3. Entertainment\n4. Bills\n5. Other");
                    System.out.print("Enter category number: ");
                    try {
                        categoryChoice = scanner.nextInt();
                        if (categoryChoice < 1 || categoryChoice > 5) {
                            System.out.println("Invalid category choice.");
                            break;
                        }
                        expenseManager.calculateTotalByCategory(Category.values()[categoryChoice - 1]);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input! Please enter a number.");
                        scanner.next(); // Clear the invalid input
                    }
                    break;
                case 7:
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
