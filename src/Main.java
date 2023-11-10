import entities.Expense;
import entities.ExpenseCategory;
import exception.InvalidExpenseException;
import interfaces.ExpenseAmountValidator;
import interfaces.ExpenseAmountValidatorImpl;
import interfaces.ExpenseCalculator;
import interfaces.ExpenseCalculatorImpl;

import java.util.Scanner;

public class Main {

    public static int counter = 1;
    public static void main(String[] args) throws InvalidExpenseException {

        Scanner scanner = new Scanner(System.in);
        int index = 0;
        Double amount = null;
        int cantidadGastosAIngresar = 0;

        ExpenseCalculator expenseCalculator = new ExpenseCalculatorImpl();

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
                scanner.nextLine(); // Limpiar el buffer de entrada
                cantidadGastosAIngresar = 0; // Reiniciar la cantidad para volver a solicitarla
            }
        } while (cantidadGastosAIngresar <= 0);

        Expense[] expenses = new Expense[cantidadGastosAIngresar];

        while (index < cantidadGastosAIngresar) {
            Expense expense = new Expense();
            ExpenseCategory category = new ExpenseCategory();

            boolean isAmountValid = false;

            while(!isAmountValid){
                System.out.print("Ingrese el monto del gasto: " + (index + 1) + ": ");
                try{
                    amount = scanner.nextDouble();

                    if (amount < 0) {
                    System.out.println("El monto no es válido. Debe ingresar un valor positivo.");
                    } else {
                    isAmountValid = true;
                    }
                }catch (Exception e){
                    System.out.println("Error al leer el monto. Asegúrese de ingresar un número válido.");
                    scanner.nextLine();
                    amount = null;
                }
            }
            scanner.nextLine();

            System.out.print("Ingrese la categoria del gasto: ");
            String name = scanner.nextLine().toLowerCase().trim();
            category.setName(name);

            System.out.print("Ingrese la fecha del gasto: (dd/MM/yyyy): ");
            String date = scanner.nextLine();

            expense.setId(counter);
            expense.setAmount(amount);
            expense.setCategory(category);
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



