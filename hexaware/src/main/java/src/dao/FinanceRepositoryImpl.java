package src.dao;

import src.entity.*;

import src.entity.Expense;
import src.exception.ExpenseNotFoundException;
import src.exception.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FinanceRepositoryImpl implements IFinanceRepository {
    private Connection c=null;
    public FinanceRepositoryImpl(Connection c) {
        this.c = c;
    }
    public FinanceRepositoryImpl() {
    	System.out.println("Default ");
    }
    
    @Override
    public int authenticateUser(String username, String password) throws UserNotFoundException//to authenticate pre-existing user
    {
    	if (c == null) {
            throw new NullPointerException("Database connection is not established.");
        }
        String q = "select user_id from Users where username = ? and password = ?";
        try (PreparedStatement ps = c.prepareStatement(q)) 
        {
        	ps.setString(1, username);
        	ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            int uID=0;
            if(rs.next()) 
            {
            	uID=rs.getInt("user_id");
            }
            else
            {
            	throw new UserNotFoundException("User with ID " + uID + " does not exist.");
            }
            return uID;
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
            return 0;
        }
    }
    
    @Override
    public boolean deleteUser(int userId) throws UserNotFoundException //to delete an existing user
    {
    	if (c == null) {
            throw new NullPointerException("Database connection is not established.");
        }
        String q = "delete from Users where user_id = ?";
        try (PreparedStatement ps = c.prepareStatement(q)) 
        {
        	ps.setInt(1, userId);
            int r = ps.executeUpdate();
            if (r == 0)
                throw new UserNotFoundException("User with ID " + userId + " does not exist.");
            return true;
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean createUser(User user) //function to create a new user
    {
    	if (c == null) {
            throw new NullPointerException("Database connection is not established.");
        }
        String q = "insert into Users (username, password, email) values (?, ?, ?)";
        try (PreparedStatement ps = c.prepareStatement(q)) 
        {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            return ps.executeUpdate() > 0;
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public ArrayList<Expense> getAllExpenses(int userId) //to get all expenses for a user ID
    {
    	if (c == null) {
            throw new NullPointerException("Database connection is not established.");
        }
        ArrayList<Expense> exp = new ArrayList<>();
        String q = "select * from Expenses where user_id = ?";
        try (PreparedStatement ps = c.prepareStatement(q)) {
        	ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) 
            {
                Expense expense = new Expense(rs.getInt("user_id"),rs.getInt("expense_id"),rs.getDouble("amount"),rs.getInt("category_id"),rs.getDate("date"),rs.getString("description"));
                exp.add(expense);
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return exp;
    }

    @Override
    public boolean createExpense(Expense expense) //to create a new expense for a user
    {
    	if (c == null) {
            throw new NullPointerException("Database connection is not established.");
        }
        String q = "insert into Expenses (user_id, amount, category_id, date, description) values (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = c.prepareStatement(q)) 
        {
        	ps.setInt(1, expense.getUserId());
        	ps.setDouble(2, expense.getAmount());
        	ps.setInt(3, expense.getCategoryId());
        	ps.setDate(4, new java.sql.Date(expense.getDate().getTime()));
        	ps.setString(5, expense.getDescription());
            return ps.executeUpdate() > 0;
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean updateExpense(Expense expense) throws ExpenseNotFoundException //to update an expense
    {
    	if (c == null) {
            throw new NullPointerException("Database connection is not established.");
        }
        String q = "update Expenses set amount = ?, category_id = ?, date = ?, description = ? where expense_id = ?";
        try (PreparedStatement ps = c.prepareStatement(q)) {
        	ps.setDouble(1, expense.getAmount());
        	ps.setInt(2, expense.getCategoryId());
        	ps.setDate(3, new java.sql.Date(expense.getDate().getTime()));
        	ps.setString(4, expense.getDescription());
        	ps.setInt(5, expense.getExpenseId());
            int r = ps.executeUpdate();
            
            if (r == 0)
                throw new ExpenseNotFoundException("Expense with ID " + expense.getExpenseId() + " does not exist.");
            return true;
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteExpense(int expenseId) throws ExpenseNotFoundException //to delete an expense
    {
    	if (c == null) {
            throw new NullPointerException("Database connection is not established.");
        }
        String q = "delete from Expenses where expense_id = ?";
        try (PreparedStatement ps = c.prepareStatement(q)) 
        {
        	ps.setInt(1, expenseId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0)
                throw new ExpenseNotFoundException("Expense with ID " + expenseId + " does not exist.");
            return true;
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean getExpenseById(int expenseId) 
    {
    	if (c == null) {
            throw new NullPointerException("Database connection is not established.");
        }
        String q = "select expense_id from Expenses where expense_id = ?";
        try (PreparedStatement ps = c.prepareStatement(q)) {
            ps.setInt(1, expenseId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
