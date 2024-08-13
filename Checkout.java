package MySystem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Checkout extends JFrame {

    public Checkout() {
        setTitle("Checkout");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Checkout");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JTextArea orderSummaryArea = new JTextArea();
        orderSummaryArea.setText(getOrderSummary());
        orderSummaryArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(orderSummaryArea);

        JButton completeOrderButton = new JButton("Complete Order");

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(completeOrderButton, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    private String getOrderSummary() {
        StringBuilder orderSummary = new StringBuilder();
        String query = "SELECT * FROM orders WHERE status = 'Pending'"; // Update as needed

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                orderSummary.append("Order ID: ").append(rs.getString("order_id"))
                            .append(", Total: ").append(rs.getDouble("total_amount"))
                            .append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderSummary.toString();
    }

    public static void main(String[] args) {
        new Checkout();
    }
}
