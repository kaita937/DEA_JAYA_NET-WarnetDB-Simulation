import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    private JTextField txtName;
    private JTextArea textArea;

    public MainFrame() {
        setTitle("SQLite Swing Test");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new FlowLayout());
        JLabel lblName = new JLabel("Nama:");
        txtName = new JTextField(15);
        JButton btnSave = new JButton("Simpan");

        topPanel.add(lblName);
        topPanel.add(txtName);
        topPanel.add(btnSave);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton btnLoad = new JButton("Tampilkan Data");

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnLoad, BorderLayout.SOUTH);

        add(panel);

        // EVENT
        btnSave.addActionListener(e -> {
            String name = txtName.getText();
            if (!name.isEmpty()) {
                UserDAO.insertUser(name);
                txtName.setText("");
                JOptionPane.showMessageDialog(this, "Data tersimpan");
            }
        });

        btnLoad.addActionListener(e -> {
            textArea.setText("");
            ArrayList<String> users = UserDAO.getAllUsers();
            for (String u : users) {
                textArea.append(u + "\n");
            }
        });
    }
}
