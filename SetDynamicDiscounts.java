package MySystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SetDynamicDiscounts extends JFrame {

    private JTextField productIdField;
    private JTextField discountField;
    private JButton applyDiscountButton;
    private String username; // Store owner username

    public SetDynamicDiscounts(String username) {
        this.username = username;
        setTitle("Set Dynamic Discounts");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        productIdField = new JTextField(30);
        discountField = new JTextField(30);
        applyDiscountButton = new JButton("Apply Discount");

        applyDiscountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productId = productIdField.getText();
                String discount = discountField.getText();
                if (productId.isEmpty() || discount.isEmpty()) {
                    JOptionPane.showMessageDialog(SetDynamicDiscounts.this, "All fields must be filled.");
                } else {
                    applyDiscount(productId, discount);
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Product ID:"));
        panel.add(productIdField);
        panel.add(new JLabel("Discount (%):"));
        panel.add(discountField);
        panel.add(applyDiscountButton);

        add(panel);
        setVisible(true);
    }

    private void applyDiscount(String productId, String discount) {
        String query = "UPDATE products SET discount = ? WHERE product_id = ? AND store_owner = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, discount);
            pstmt.setString(2, productId);
            pstmt.setString(3, username);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Discount applied successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error applying discount.");
        }
    }

    public static void main(String[] args) {
        // For testing purposes, replace "storeOwner1" with a real username
        new SetDynamicDiscounts("storeOwner1");
    }
}
