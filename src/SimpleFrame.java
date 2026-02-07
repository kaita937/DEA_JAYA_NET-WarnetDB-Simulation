import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class SimpleFrame extends JFrame {

    public SimpleFrame() {

        setTitle("DEA_JAYA.NET");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ICON (AMAN)
        java.net.URL iconURL = getClass().getResource("/image.png");
        if (iconURL != null) {
            Image icon = Toolkit.getDefaultToolkit().getImage(iconURL);
            setIconImage(icon);
        } else {
            System.out.println("Icon tidak ditemukan");
        }

        JTextField field = new JTextField();
        JButton btnSimpan = new JButton("Simpan Nama");
        JButton btnLihat = new JButton("Lihat Data");

        btnSimpan.addActionListener(e -> simpan(field.getText()));
        btnLihat.addActionListener(e -> lihatData());

        JPanel panelBtn = new JPanel();
        panelBtn.add(btnSimpan);
        panelBtn.add(btnLihat);

        add(field, BorderLayout.NORTH);
        add(panelBtn, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void lihatData() {
        StringBuilder data = new StringBuilder();

        try (Connection c = DBConnection.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM pelanggan")) {

            while (rs.next()) {
                data.append(rs.getInt("id"))
                    .append(" - ")
                    .append(rs.getString("nama"))
                    .append("\n");
            }

            JOptionPane.showMessageDialog(this,
                    data.length() == 0 ? "Belum ada data" : data.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void simpan(String nama) {
        if (nama.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong");
            return;
        }

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps =
                     c.prepareStatement("INSERT INTO pelanggan(nama) VALUES(?)")) {

            ps.setString(1, nama);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data tersimpan");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
