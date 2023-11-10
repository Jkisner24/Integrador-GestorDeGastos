import entities.Expense;
import entities.ExpenseCategory;
import exception.InvalidCategoryException;
import exception.InvalidExpenseException;
import interfaces.*;

import java.util.Scanner;

public class Main {

    public static int counter = 1;
    public static void main(String[] args) throws InvalidExpenseException {

        Scanner scanner = new Scanner(System.in);
        int index = 0;
        Double amount = null;
        int cantidadGastosAIngresar = 0;

        ExpenseCalculator expenseCalculator = new ExpenseCalculatorImpl();
        ExpenseAmountValidator expenseAmountValidator = new ExpenseAmountValidatorImpl();
        ExpenseCategoryValidator expenseCategoryValidator = new ExpenseCategoryValidatorImpl();

        do {
            System.out.print("Ingrese la cantidad de gastos a registrar: ");
            try {
                while (!scanner.hasNextInt()) {
                    System.out.println("Dato Erróneo. Debe ingresar un número entero.");
                    scanner.next();
                }
                cantidadGastosAIngresar = scanner.nextInt();
                scanner.nextLine();
            }catch (Exception e) {
                System.out.println("Error al leer la entrada. Asegúrese de ingresar un número entero válido.");
                scanner.nextLine();
                cantidadGastosAIngresar = 0;
            }
        } while (cantidadGastosAIngresar <= 0);

        Expense[] expenses = new Expense[cantidadGastosAIngresar];

        while (index < cantidadGastosAIngresar) {
            Expense expense = new Expense();
            ExpenseCategory category = new ExpenseCategory();

            boolean isAmountValid = false;

            while(!isAmountValid) {
                try {
                    System.out.print("Ingrese el monto del gasto: " + (index + 1) + ": ");
                    amount = scanner.nextDouble();

                    expenseAmountValidator.validateAmount(amount);
                    isAmountValid = true;

                } catch (InvalidExpenseException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println("Error al leer el monto. Asegúrese de ingresar un número válido.");
                    scanner.nextLine();
                    amount = null;
                }
            }
                scanner.nextLine();

            String categoryName = null;
            boolean isCategoryValid = false;

            while (!isCategoryValid) {
                try {
                    System.out.print("Ingrese la categoría del gasto " + (index + 1) + ": ");
                    categoryName = expenseCategoryValidator.validateCategory(scanner.nextLine().toLowerCase().trim());
                    isCategoryValid = true;
                } catch (InvalidCategoryException e) {
                    System.out.println(e.getMessage());
                }
            }
            category.setName(categoryName);

            System.out.print("Ingme = rese la fecha del gasto: (dd/MM/yyyy): ");
            String date = scanner.nextLine();

            expense.setId(counter);
            expense.setAmount(amount);
            expense.setCategory(categoryName);
            expense.setDate(date);

            expenses[index] = expense;

            counter++;

            index++;
        }

        System.out.println("Total gastos ingresados: " + expenseCalculator.calculateTotal(expenses));

        System.out.println("Detalle de gastos ingresados");
        for(int i = 0 ; i< expenses.length; i++){
            System.out.println(expenses[i]);
        }
    }
}



