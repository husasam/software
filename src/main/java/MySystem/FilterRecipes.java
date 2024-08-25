package MySystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilterRecipes extends JFrame {
    private JComboBox<String> categoryComboBox;
    private JList<String> recipeList;

    public FilterRecipes() {
        setTitle("Filter Recipes");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Filter Recipes by Category");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        categoryComboBox = new JComboBox<>(getCategories());
        JButton filterButton = new JButton("Filter");

        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = (String) categoryComboBox.getSelectedItem();
                recipeList.setListData(filterRecipes(selectedCategory));
            }
        });

        recipeList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(recipeList);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.NORTH);
        panel.add(categoryComboBox, BorderLayout.CENTER);
        panel.add(filterButton, BorderLayout.EAST);
        panel.add(scrollPane, BorderLayout.SOUTH);
        recipeList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedRecipe = recipeList.getSelectedValue();
                if (selectedRecipe != null) {
                    new ViewRecipeDetails(selectedRecipe); // فتح تفاصيل الوصفة
                }
            }
        });


        add(panel);
        setVisible(true);
    }

    private String[] getCategories() {
        List<String> categories = new ArrayList<>();
        String query = "SELECT DISTINCT category FROM recipes"; // Update table/column names as needed

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                categories.add(rs.getString("category"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories.toArray(new String[0]);
    }

    private String[] filterRecipes(String category) {
        List<String> recipes = new ArrayList<>();
        String query = "SELECT name FROM recipes WHERE category = ?"; // Update table/column names as needed

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, category);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                recipes.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recipes.toArray(new String[0]);
    }

    public static void main(String[] args) {
        new FilterRecipes();
    }
}
