package MySystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddUser extends JFrame {
    private JTextField usernameField;
    private JTextField emailField;
    private JComboBox<String> roleComboBox;

    public AddUser() {
        setTitle("Add User");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        usernameField = new JTextField(20);
        emailField = new JTextField(20);
        roleComboBox = new JComboBox<>(new String[]{"Admin", "Store Owner", "Supplier"});

        JButton addButton = new JButton("Add User");

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Role:"));
        panel.add(roleComboBox);
        panel.add(addButton);

        add(panel);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUser();
            }
        });

        setVisible(true);
    }

    private void addUser() {
        String username = usernameField.getText();
        String email = emailField.getText();
        String role = (String) roleComboBox.getSelectedItem();

        String query = "INSERT INTO users (username, email, role) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, role);

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "User added successfully.");
            dispose();

            // Notify ManageUsers to refresh its data
            ManageUsers.refreshUserData();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding user.");
        }
    }
}
