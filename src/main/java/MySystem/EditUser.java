package MySystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditUser extends JFrame {
    private JTextField emailField;
    private JComboBox<String> roleComboBox;
    private String username;
    private String Email;
    private String Role;

    public EditUser(String username) {
        this.username = username;

        setTitle("Edit User");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create fields for email and role
        emailField = new JTextField(20);
        roleComboBox = new JComboBox<>(new String[]{"Admin", "user", "Store Owner", "Supplier"});

        JButton saveButton = new JButton("Save Changes");

        // Layout and components
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Role:"));
        panel.add(roleComboBox);
        panel.add(saveButton);

        add(panel);

        // Load existing data into fields
        loadUserData(username);

        // Add action listener for the save button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String role = (String) roleComboBox.getSelectedItem();
                updateUserData(email, role);
            }
        });

        setVisible(true);
    }

    public void loadUserData(String username) {
        // Fetch and load existing user data into fields based on the given username
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT email, role FROM users WHERE username = ?")) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                emailField.setText(rs.getString("email"));
                Email=rs.getString("email");
                roleComboBox.setSelectedItem(rs.getString("role"));
                Role = rs.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading user data.");
        }
    }

    public void updateUserData(String email, String role) {
        String query = "UPDATE users SET email = ?, role = ? WHERE username = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, email);
            pstmt.setString(2, role);
            pstmt.setString(3, username);

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "User data updated successfully.");
            dispose();

            // Notify ManageUsers to refresh its data
            ManageUsers.refreshUserData();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating user data.");
        }
    }

	public String getEmail() {
		return this.Email;
	}
	public String getRole() {
		return this.Role;
	}
}
