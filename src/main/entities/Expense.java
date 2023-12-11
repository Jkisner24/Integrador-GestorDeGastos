package main.entities;

public class Expense {
    private Integer id;
    private Double amount;
    private int category;
    private String date;

    public Expense() {
    }

    public Expense(Integer id, Double amount, int category, String date) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public Expense(Double amount, String category, String date) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", amount=" + amount +
                ", category=" + category +
                ", date='" + date + '\'' +
                '}';
    }
}
