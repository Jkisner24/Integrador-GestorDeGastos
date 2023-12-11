package main.entities;

public class SpecialExpense extends Expense{
    private String reason;

    public SpecialExpense() {
    }

    public SpecialExpense(Double amount, String category, String date, String reason) {
        super(amount, category, date);
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
