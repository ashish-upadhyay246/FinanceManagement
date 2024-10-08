package src.dao;

import src.exception.UserNotFoundException;
import src.exception.ExpenseNotFoundException;
import src.entity.User;
import src.entity.Expense;
import java.util.ArrayList;

public interface IFinanceRepository 
{
    boolean createUser(User user);
    boolean createExpense(Expense expense);
    ArrayList<Expense> getAllExpenses(int userId);
    
    boolean deleteUser(int userId) throws UserNotFoundException;
    int authenticateUser(String username, String password) throws UserNotFoundException;
    boolean getExpenseById(int expenseId) throws ExpenseNotFoundException;
    boolean updateExpense(Expense expense) throws ExpenseNotFoundException;
    boolean deleteExpense(int expenseId) throws ExpenseNotFoundException; 
}