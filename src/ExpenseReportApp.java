import main.config.JdbcConfig;
import main.dao.ExpenseCategoryDao;
import main.dao.ExpenseDao;
import main.dao.dto.ExpenseCategoryDto;
import main.dao.dto.ExpenseDto;
import main.dao.impl.ExpenseCategoryDaoImpl;
import main.dao.impl.ExpenseDaoImpl;
import main.entities.ExpenseCategory;
import main.exception.DAOException;
import main.exception.InvalidExpenseException;
import main.interfaces.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ExpenseReportApp {

    public static void main(String[] args) throws InvalidExpenseException {

        Scanner scanner = new Scanner(System.in);
        try(Connection connection = JdbcConfig.getConnection()){
            ExpenseDao expenseDao = new ExpenseDaoImpl(connection);
            ExpenseCategoryDao expenseCategoryDao = new ExpenseCategoryDaoImpl(connection);

            ExpenseCalculator expenseCalculator = new ExpenseCalculatorImpl();
            ExpenseAmountValidator expenseAmountValidator = new ExpenseAmountValidatorImpl();

            int amount;
            String name;
            int quantityExpensesToin;
            System.out.println("How many expenses do you want to enter?: ");
            quantityExpensesToin = scanner.nextInt();

            for(int i = 0; i < quantityExpensesToin; i++){
                try{
                    ExpenseDto expenseDto = new ExpenseDto();
                    ExpenseCategoryDto expenseCategoryDto = new ExpenseCategoryDto();

                    System.out.println("Insert expense amount");
                    amount = scanner.nextInt();

                    if(!expenseAmountValidator.validateAmount(amount)){
                        System.out.println("The amount is ok");
                    }
                    scanner.nextLine();

                    System.out.println("Insert the expense category: ");
                    name = scanner.nextLine();
                    expenseCategoryDto.setName(name);

                    expenseCategoryDao.insert(expenseCategoryDto);

                    System.out.println("Insert to expense date: (yyyy-MM-DD): ");
                    String dateString = scanner.nextLine();

                    expenseDto.setAmount(amount);

                    ExpenseCategory expenseCategory = expenseCategoryDao.getCategoryByName(name);
                    expenseDto.setCategory(expenseCategory.getId());
                    expenseDto.setDate(dateString);

                    expenseDao.insert(expenseDto);

                }catch (DAOException exception){
                    System.out.println("Error trying insert the expense" + exception.getMessage());
                }catch (InvalidExpenseException e){
                    throw new RuntimeException(e);
                }
            }

            List<ExpenseDto> expenses = expenseDao.getAll();
            System.out.println("Details of expenses inserted");
            for (ExpenseDto expense : expenses){
                System.out.println(expense);
            }
            System.out.println("Total amount of de expense" + expenseCalculator.calculateTotal(expenses));

        }catch(SQLException | DAOException e){
            e.printStackTrace();
        }
    }
}



