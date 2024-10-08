package main;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import src.util.*;
import src.dao.*;
import src.entity.*;
class search_expense {
	private FinanceRepositoryImpl repo;
    private Connection conn;
	
	@Test
	void testGetAllExpenses_Success() throws SQLException 
	{
	 	conn = DBConnUtil.getConnection("db.properties");
        if (conn != null)
        {
            System.out.println("Connection established.");
        }
	    int userId = 3;  
	    repo = new FinanceRepositoryImpl(conn);
	    List<Expense> expenses = repo.getAllExpenses(userId);
	    assertNotNull(expenses, "Error -> The expenses list should not be null");
	    assertTrue(expenses.size() > 0, "Error -> Expected expenses for the user but got an empty list");
	}


}
