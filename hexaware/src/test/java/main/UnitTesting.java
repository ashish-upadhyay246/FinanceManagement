package main;

import src.entity.User;
import src.entity.Expense;
import java.sql.Connection;
import java.lang.Exception;
import src.util.DBConnUtil;
import org.junit.jupiter.api.Test;
import src.dao.FinanceRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import src.exception.UserNotFoundException;
import src.exception.ExpenseNotFoundException;
import static org.junit.jupiter.api.Assertions.*;

class UnitTesting {
	
	private Connection c;
    private FinanceRepositoryImpl obj;
   
    @BeforeEach
    void setUp() {
        c = DBConnUtil.getConnection("db.properties");
        obj = new FinanceRepositoryImpl(c);
    }
    
    @Test
    void testCreateUser() 
    {
    	System.out.println("\nCreating user");
    	obj = new FinanceRepositoryImpl(c);
        User tu = new User(0, "pratima", "333", "p@gmail.com");
        boolean r = obj.createUser(tu);
        assertTrue(r, "User was not created successfully");
    }
    
    @Test
	void testCreateExpense()
	{
    	System.out.println("\nCreating expenses");
	    Expense te = new Expense(3, 0, 150.0, 2, new java.util.Date(), "electronics");
	    obj = new FinanceRepositoryImpl(c);
	    boolean r = obj.createExpense(te);
	    assertTrue(r, "Expense was not created successfully");
	}
    
    @Test
    void testDeleteUser()
    {
    	System.out.println("\nDeleting user");
        int uid = 55;
        Exception ex = assertThrows(UserNotFoundException.class, () -> obj.deleteUser(uid));
        assertEquals("User with ID " + uid + " does not exist.", ex.getMessage());
        System.out.println(ex.getMessage());
    }

    @Test
    void testDeleteExpense() {
    	System.out.println("\nDeleting expense");
        int exid = 55;
        Exception ex = assertThrows(ExpenseNotFoundException.class, () -> obj.deleteExpense(exid));
        assertEquals("Expense with ID " + exid + " does not exist.", ex.getMessage());
        System.out.println(ex.getMessage());
    }

    @Test
    void testGetExpenseById()
    {
        System.out.println("\nSearching for expense");
        int etest = 37;
        boolean exists = obj.getExpenseById(etest);
        assertTrue(exists, "Error -> Expected expense to exist, but it was not found.");
        if (exists) {
            System.out.println("Expense with ID " + etest + " was found.");
        }
    }


    
}
