package MySystem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ViewRecipeDetails extends JFrame {
    private String recipeName;

    public ViewRecipeDetails(String recipeName) {
        this.recipeName = recipeName;

        setTitle("Recipe Details");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Recipe Details");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JTextArea recipeDetailsArea = new JTextArea();
        recipeDetailsArea.setText(getRecipeDetails());
        recipeDetailsArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(recipeDetailsArea);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }

    private String getRecipeDetails() {
        StringBuilder details = new StringBuilder();
        String query = "SELECT * FROM recipes WHERE name = ?"; // Update table/column names as needed

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, recipeName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                details.append("Name: ").append(rs.getString("name")).append("\n")
                       .append("Category: ").append(rs.getString("category")).append("\n")
                       .append("Ingredients: ").append(rs.getString("ingredients")).append("\n")
                       .append("Instructions: ").append(rs.getString("instructions")).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return details.toString();
    }

    public static void main(String[] args) {
        // Example usage with a sample recipe name
        new ViewRecipeDetails("Chocolate Cake");
    }
}
