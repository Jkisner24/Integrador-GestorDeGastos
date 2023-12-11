package main.dao.impl;

import main.dao.ExpenseCategoryDao;
import main.dao.dto.ExpenseCategoryDto;
import main.entities.ExpenseCategory;
import main.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExpenseCategoryDaoImpl implements ExpenseCategoryDao {
    private static final String INSERT_INTO_EXPENSE_CATEGORY = "INSERT INTO expensecategory (name) VALUES (?)";
    private static final String GET_CATEGORY_BY_NAME = "SELECT * FROM expensecategory WHERE name = ?";
    private final Connection connection;
    public ExpenseCategoryDaoImpl(Connection connection){
        this.connection = connection;
    }
    @Override
    public void insert(ExpenseCategoryDto expenseCategoryDto) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_INTO_EXPENSE_CATEGORY)){
            ExpenseCategory expenseCategory = mapDtoToExpenseCategory(expenseCategoryDto);
            statement.setString(1, expenseCategory.getName());

            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0){
                throw new DAOException("Error trying insert, 0 rows has affected");
            }
        }catch (DAOException | SQLException e){
            assert e instanceof SQLException;
            throw new DAOException("Error trying insert an expense", (SQLException) e);
        }
    }

    @Override
    public ExpenseCategory getCategoryByName(String name) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(GET_CATEGORY_BY_NAME)){
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return new ExpenseCategory(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
            }
            return null;
        }catch(SQLException e){
            throw new DAOException("Error trying to get an expense for ID");
        }
    }

    private ExpenseCategory mapDtoToExpenseCategory(ExpenseCategoryDto expenseCategoryDto){
        ExpenseCategory expenseCategory = new ExpenseCategory();
        expenseCategory.setName(expenseCategoryDto.getName());
        return expenseCategory;
    }
}
