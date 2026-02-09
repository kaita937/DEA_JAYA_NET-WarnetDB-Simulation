import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDAO {

    public static void insertUser(String name) {
        String sql = "INSERT INTO users(name) VALUES(?)";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, name);
            ps.executeUpdate();

            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getAllUsers() {
        ArrayList<String> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()) {
                users.add(rs.getInt("id") + " - " + rs.getString("name"));
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }
}
