import java.sql.Connection;
import java.sql.DriverManager;

import javax.management.RuntimeErrorException;

public class DBConnection {
    private static final String URL = "jdbc:sqlite:warnet.db";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}