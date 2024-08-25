package MySystem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddToCart extends JFrame {

    public AddToCart() {
        setTitle("Add to Cart");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Add to Cart");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JTextArea cartDetailsArea = new JTextArea();
        cartDetailsArea.setText(getCartDetails());
        cartDetailsArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(cartDetailsArea);

        JButton addToCartButton = new JButton("Add to Cart");

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(addToCartButton, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    private String getCartDetails() {
        StringBuilder cartDetails = new StringBuilder();
        String query = "SELECT * FROM cart"; // Update the table name as needed

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                cartDetails.append("Item: ").append(rs.getString("item_name"))
                           .append(", Quantity: ").append(rs.getInt("quantity"))
                           .append(", Price: ").append(rs.getDouble("price"))
                           .append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartDetails.toString();
    }

    public static void main(String[] args) {
        new AddToCart();
    }
}
