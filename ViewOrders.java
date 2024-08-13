package MySystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewOrders extends JFrame {

    private JTable ordersTable;
    private DefaultTableModel tableModel;

    public ViewOrders() {
        setTitle("View Orders");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(new Object[]{"Order ID", "Customer Name", "Product", "Quantity", "Status"}, 0);
        ordersTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(ordersTable);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
        loadOrderData();

        setVisible(true);
    }

    private void loadOrderData() {
        String query = "SELECT order_id, customer_name, product, quantity, status FROM orders";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            tableModel.setRowCount(0); // Clear existing data

            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                String customerName = rs.getString("customer_name");
                String product = rs.getString("product");
                int quantity = rs.getInt("quantity");
                String status = rs.getString("status");
                tableModel.addRow(new Object[]{orderId, customerName, product, quantity, status});
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading order data.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViewOrders());
    }
}
