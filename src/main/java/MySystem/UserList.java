package MySystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserList extends JFrame {

    private JTable userTable;
    private DefaultTableModel tableModel;

    public UserList() {
        setTitle("User List");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(new Object[]{"Username", "Email", "Role"}, 0);
        userTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(userTable);

        JButton editButton = new JButton("Edit User");

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = userTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String username = (String) tableModel.getValueAt(selectedRow, 0);
                    new EditUser(username);
                } else {
                    JOptionPane.showMessageDialog(UserList.this, "Please select a user to edit.");
                }
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(editButton, BorderLayout.SOUTH);

        add(panel);
        loadUserData();
        setVisible(true);
    }

    private void loadUserData() {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT username, email, role FROM users");
             ResultSet rs = pstmt.executeQuery()) {

            tableModel.setRowCount(0); // Clear existing data

            while (rs.next()) {
                String username = rs.getString("username");
                String email = rs.getString("email");
                String role = rs.getString("role");
                tableModel.addRow(new Object[]{username, email, role});
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading user data.");
        }
    }
}
