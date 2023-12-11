package main.dao.dto;

public class ExpenseDto {
    private double amount;
    private int category;
    private String date;

    public ExpenseDto() {
    }

    public ExpenseDto(double amount, int categoryId, String date) {
        this.amount = amount;
        this.category = categoryId;
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getCategoryId() {
        return category;
    }

    public void setCategory(int categoryId) {
        this.category = categoryId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ExpenseDto{" +
                "amount=" + amount +
                ", categoryId=" + category +
                ", date='" + date + '\'' +
                '}';
    }
}
