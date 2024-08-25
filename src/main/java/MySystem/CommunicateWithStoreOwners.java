package MySystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommunicateWithStoreOwners extends JFrame {

    private JTextArea messageArea;
    private JTextField receiverNameField;
    private JButton sendButton;
    private String username; // The username of the sender

    public CommunicateWithStoreOwners(String username) {
        this.username = username;
        setTitle("Communicate with Store Owners");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        messageArea = new JTextArea(10, 30);
        receiverNameField = new JTextField(30);
        sendButton = new JButton("Send Message");

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String receiverName = receiverNameField.getText();
                String message = messageArea.getText();
                if (receiverName.isEmpty() || message.isEmpty()) {
                    JOptionPane.showMessageDialog(CommunicateWithStoreOwners.this, "Receiver name and message cannot be empty.");
                } else {
                    String receiverUsername = getUserIdByName(receiverName);
                    if (receiverUsername != null) {
                        String messagesent = sendMessage(username, receiverUsername, message);
                        JOptionPane.showMessageDialog(null, messagesent);
                    } else {
                        JOptionPane.showMessageDialog(CommunicateWithStoreOwners.this, "Receiver not found.");
                    }
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Receiver Username:"));
        panel.add(receiverNameField);
        panel.add(new JLabel("Message:"));
        panel.add(new JScrollPane(messageArea));
        panel.add(sendButton);

        add(panel);
        setVisible(true);
    }

    private String getUserIdByName(String name) {
        String username = null;
        String query = "SELECT username FROM users WHERE username = ?"; // Adjust the query based on your schema

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    username = rs.getString("username"); // Or another column for user ID
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving user ID.");
        }

        return username;
    }

    public String sendMessage(String senderUsername, String receiverUsername, String message) {
        String query = "INSERT INTO messages (sender_username, receiver_username, message) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, senderUsername);
            pstmt.setString(2, receiverUsername);
            pstmt.setString(3, message);
            pstmt.executeUpdate();
            return "Message sent successfully!";
            

        } catch (SQLException e) {
            e.printStackTrace();
            return "Error";
        }
		
    }

    public static void main(String[] args) {
        // For testing purposes, replace "user1" with a real username
        new CommunicateWithStoreOwners("user1");
    }
}
