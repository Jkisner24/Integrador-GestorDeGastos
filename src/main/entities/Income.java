package main.entities;

public class Income {
    private Double amount;

    public Income(){
    }

    public Income(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
