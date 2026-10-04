package ExpenseTracker;
import java.time.LocalDate;

public class Expense {

    private int expenseId;
    private String description;
    private double amount;
    private ExpenseCategory category;
    private LocalDate date;

    public Expense(int expenseId, String description,
                   double amount, ExpenseCategory category,
                   LocalDate date) {

        this.expenseId = expenseId;
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public ExpenseCategory getCategory() {
        return category;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {

        return String.format(
                "ID: %-4d | %-20s | ₹%-8.2f | %-15s | %s",
                expenseId,
                description,
                amount,
                category,
                date
        );
    }
}
