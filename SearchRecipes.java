package MySystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SearchRecipes extends JFrame {
    private JTextField searchField;
    private JList<String> recipeList;

    public SearchRecipes() {
        setTitle("Search Recipes");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Search Recipes");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        searchField = new JTextField();
        JButton searchButton = new JButton("Search");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                recipeList.setListData(searchRecipes(searchText));
            }
        });

        recipeList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(recipeList);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.NORTH);
        panel.add(searchField, BorderLayout.CENTER);
        panel.add(searchButton, BorderLayout.EAST);
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

    private String[] searchRecipes(String searchText) {
        List<String> recipes = new ArrayList<>();
        String query = "SELECT name FROM recipes WHERE name LIKE ?"; // Update table/column names as needed

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, "%" + searchText + "%");
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
        new SearchRecipes();
    }
}
