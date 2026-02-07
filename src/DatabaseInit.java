import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInit {
    
    public static void init() {
        try (Connection c = DBConnection.getConnection(); Statement s = c.createStatement()) {

            String sql =
                    "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "username TEXT," +
                    "password TEXT," +
                    "role TEXT" +
                    ")";

            s.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
