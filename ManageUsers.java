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

public class ManageUsers extends JFrame {

    private JTable userTable;
    private DefaultTableModel tableModel;

    public ManageUsers() {
        setTitle("User List");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(new Object[]{"Username", "Email", "Role"}, 0);
        userTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(userTable);

        JButton editButton = new JButton("Edit User");
        JButton addButton = new JButton("Add User");
        JButton deleteButton = new JButton("Delete User");

        // Action listener for Edit button
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = userTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String username = (String) tableModel.getValueAt(selectedRow, 0);
                    new EditUser(username);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(ManageUsers.this, "Please select a user to edit.");
                }
            }
        });

        // Action listener for Add button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddUser();
                dispose();
            }
        });

        // Action listener for Delete button
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = userTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String username = (String) tableModel.getValueAt(selectedRow, 0);
                    deleteUser(username);
                } else {
                    JOptionPane.showMessageDialog(ManageUsers.this, "Please select a user to delete.");
                }
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(editButton);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

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

    private void deleteUser(String username) {
        String query = "DELETE FROM users WHERE username = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "User deleted successfully.");
            loadUserData();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting user.");
        }
    }

    public static void refreshUserData() {
        // Code to refresh user data
        // Here it's called directly for simplicity
        new ManageUsers();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ManageUsers());
    }
}
