package main.dao.impl;

import main.dao.ExpenseDao;
import main.dao.dto.ExpenseDto;
import main.entities.Expense;
import main.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDaoImpl implements ExpenseDao {

    private static final String GET_EXPENSE_BY_ID = "SELECT * FROM expense WHERE id=?";
    private static final String GET_ALL = "SELECT * FROM expense";
    private static final String INSERT_INTO_EXPENSE = "INSERT INTO expense (amount, category_id, date) VALUES (?, ?, ?)";
    private static final String UPDATE_EXPENSE = "UPDATE expense SET amount = ?, category_id = ?, date = ? WHERE id= ?";
    private static final String DELETE_EXPENSE = "DELETE FROM expense WHERE id = ?";

    private final Connection connection;

    public ExpenseDaoImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public ExpenseDto getById(int id) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(GET_EXPENSE_BY_ID)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                return mapResultSetToExpenseDto(resultSet);
            }
            return null;
        }catch (SQLException e){
            throw new DAOException("Error in getById", e);
        }
    }

    @Override
    public List<ExpenseDto> getAll() throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            List<ExpenseDto> expenses = new ArrayList<>();
            while (resultSet.next()){
                expenses.add(mapResultSetToExpenseDto(resultSet));
            }
            return expenses;
        }catch (SQLException e) {
            throw new DAOException("Error in get all", e);
        }
    }

    @Override
    public void insert(ExpenseDto expenseDto) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_INTO_EXPENSE)){
            Expense expense = mapDtoToExpense(expenseDto);
            statement.setDouble(1, expense.getAmount());
            statement.setInt(2, expense.getCategory());
            statement.setString(3, expense.getDate());
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0){
                throw new DAOException("Error insert expense, any row has affected");
            }
        }
        catch (DAOException | SQLException e){
            assert e instanceof SQLException;
            throw new DAOException("Error in insert", (SQLException) e);
        }
    }

    @Override
    public void update(ExpenseDto expenseDto) throws DAOException {
        try(PreparedStatement statement = connection.prepareStatement(UPDATE_EXPENSE)){
            Expense expense = mapDtoToExpense(expenseDto);

            statement.setDouble(1,expense.getAmount());
            statement.setInt(2, expense.getCategory());
            statement.setString(3, expense.getDate());
            statement.setInt(4, expense.getId());
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0){
                throw new DAOException("Error in update expense");
            }
        }catch(SQLException e){
            throw new DAOException("Error in update", e);
        }catch (DAOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        try(PreparedStatement statement = connection.prepareStatement(DELETE_EXPENSE)){
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0){
                throw new DAOException("Error trying delete an expense, 0 rows has affected");
            }
        }catch(SQLException e){
            throw new DAOException("Error trying delete an expense", e);
        }
    }

    private Expense mapDtoToExpense(ExpenseDto expenseDto){
        Expense expense = new Expense();
        expense.setAmount(expenseDto.getAmount());
        expense.setCategory(expenseDto.getCategoryId());
        expense.setDate(expenseDto.getDate());
        return expense;
    }

    private ExpenseDto mapResultSetToExpenseDto(ResultSet resultSet) throws SQLException{
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setAmount(resultSet.getInt("amount"));
        expenseDto.setDate(String.valueOf(resultSet.getDate("date")));
        expenseDto.setCategory(resultSet.getInt("category_id"));
        return expenseDto;
    }

}
