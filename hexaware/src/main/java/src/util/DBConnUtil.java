package src.util;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnUtil 
{
    private static Connection cn;
    public static Connection getConnection(String fname) 
    {
        if (cn == null) 
        {
            try 
            {
                String cs = DBPropertyUtil.getConnectionString(fname);
                cn = DriverManager.getConnection(cs);
            } 
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
        }
        return cn;
    }
}
