package main.interfaces;

import main.dao.dto.ExpenseDto;
import main.entities.Expense;

import java.util.List;

public class ExpenseCalculatorImpl implements ExpenseCalculator {
    @Override
    public double calculateExpense(Expense expense) {
        return expense.getAmount();
    }

    @Override
    public double calculateTotal(List<ExpenseDto> expenses) {
        return expenses.stream()
                .mapToDouble(ExpenseDto::getAmount)
                .sum();
    }

}
