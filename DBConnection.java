import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.io.File;

public class DBConnection {

    private static Connection connection;

    private static String getDBUrl() {
        try {
            String jarDir = new File(
                    DBConnection.class
                            .getProtectionDomain()
                            .getCodeSource()
                            .getLocation()
                            .toURI()
            ).getParent();

            File dbFolder = new File(jarDir, "database");
            if (!dbFolder.exists()) {
                dbFolder.mkdir();
            }

            File dbFile = new File(dbFolder, "test.db");
            return "jdbc:sqlite:" + dbFile.getAbsolutePath();

        } catch (Exception e) {
            throw new RuntimeException("Gagal menentukan lokasi database", e);
        }
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(getDBUrl());
                createTableIfNotExists(connection);
            }
            return connection;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void createTableIfNotExists(Connection conn) {
        String sql = """
            CREATE TABLE IF NOT EXISTS users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL
            );
        """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabel users siap.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
