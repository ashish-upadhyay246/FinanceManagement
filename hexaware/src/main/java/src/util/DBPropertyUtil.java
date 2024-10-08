package src.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {

    public static String getConnectionString(String fname) 
    {
        Properties p = new Properties();
        try (FileInputStream fis = new FileInputStream(fname)) 
        {
            p.load(fis);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        String url = p.getProperty("db.url");
        String username = p.getProperty("db.username");
        String password = p.getProperty("db.password");

        return url + "?user=" + username + "&password=" + password;
    }
}
