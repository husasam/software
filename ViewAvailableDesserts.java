package MySystem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViewAvailableDesserts extends JFrame {

    public ViewAvailableDesserts() {
        setTitle("View Available Desserts");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Available Desserts");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JList<String> dessertList = new JList<>(getDessertList());
        JScrollPane scrollPane = new JScrollPane(dessertList);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }

    private String[] getDessertList() {
        List<String> desserts = new ArrayList<>();
        String query = "SELECT name FROM desserts"; // Update the table name as needed

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                desserts.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return desserts.toArray(new String[0]);
    }

    public static void main(String[] args) {
        new ViewAvailableDesserts();
    }
}
