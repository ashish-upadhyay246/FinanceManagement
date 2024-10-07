package src.entity;

import java.util.Date;

public class Expense {
    private int expenseId;
    private int userId;
    private double amount;
    private int categoryId;
    private Date date;
    private String description;

    public Expense() {}

    public Expense(int userId, int expenseId, double amount, int categoryId, Date date, String description) {
    	this.userId = userId;
        this.expenseId = expenseId;
        this.amount = amount;
        this.categoryId = categoryId;
        this.date = date;
        this.description = description;
    }

    //setters and getters
    public int getExpenseId() { return expenseId; }
    public void setExpenseId(int expenseId) { this.expenseId = expenseId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
