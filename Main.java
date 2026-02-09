import java.io.FileWriter;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileWriter("log.txt", true))) {
            out.println("=== APLIKASI DIJALANKAN ===");
            out.println("java.version = " + System.getProperty("java.version"));
            out.println("java.home    = " + System.getProperty("java.home"));
            out.println("user.dir     = " + System.getProperty("user.dir"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        javax.swing.SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
