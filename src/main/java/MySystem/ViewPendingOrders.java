package MySystem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViewPendingOrders extends JFrame {

    public ViewPendingOrders() {
        setTitle("View Pending Orders");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Pending Orders");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JList<String> ordersList = new JList<>(getPendingOrders());
        JScrollPane scrollPane = new JScrollPane(ordersList);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }

    private String[] getPendingOrders() {
        List<String> orders = new ArrayList<>();
        String query = "SELECT order_id FROM orders WHERE status = 'Pending'"; // Update table/column names as needed

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                orders.add("Order #" + rs.getString("order_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders.toArray(new String[0]);
    }

    public static void main(String[] args) {
        new ViewPendingOrders();
    }
}
