package main;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;
import src.util.*;
import src.dao.*;
import src.entity.*;

class expense_test {
	  private FinanceRepositoryImpl repo;
	  private Connection conn;

	@Test
	void testCreateExpense_Success() throws SQLException 
	{
		conn = DBConnUtil.getConnection("db.properties");
        if (conn != null) 
        {
            System.out.println("Connection established.");
        }
	    Expense testExpense = new Expense(3, 0, 150.0, 2, new java.util.Date(), "Groceries");
	    repo = new FinanceRepositoryImpl(conn);
	    boolean result = repo.createExpense(testExpense);
	    assertTrue(result, "Error -> Expense was not created successfully");
	}


}
