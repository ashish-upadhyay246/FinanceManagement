package main;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;
import src.util.*;
import src.dao.*;
import src.entity.*;
class junit_test {

    private FinanceRepositoryImpl repo;
    private Connection conn;

    @Test
    void testCreateUser() throws SQLException {
    	System.out.println("hello");
   	 	conn = DBConnUtil.getConnection("db.properties");
        if (conn != null) {
            System.out.println("Connection established.");
        }
    	repo = new FinanceRepositoryImpl(conn);
        User testUser = new User(0, "jatin", "Jatin@123", "jatin@gmail.com");
        boolean result = repo.createUser(testUser);
        assertTrue(result, "Error -> User was not created successfully");

    }
}
