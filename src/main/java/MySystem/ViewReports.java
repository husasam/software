package MySystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewReports extends JFrame {

    private JTable reportsTable;
    private DefaultTableModel tableModel;

    public ViewReports() {
        setTitle("View Reports");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(new Object[]{"Report Type", "Details"}, 0);
        reportsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(reportsTable);

        JButton refreshButton = new JButton("Refresh");

        refreshButton.addActionListener(e -> loadReportsData());

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(refreshButton, BorderLayout.SOUTH);

        add(panel);
        loadReportsData();
        setVisible(true);
    }

    private void loadReportsData() {
        // Example query, adjust based on actual requirements
        String query = "SELECT report_type, details FROM reports";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            tableModel.setRowCount(0); // Clear existing data

            while (rs.next()) {
                String reportType = rs.getString("report_type");
                String details = rs.getString("details");
                tableModel.addRow(new Object[]{reportType, details});
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading reports data.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViewReports());
    }
}
