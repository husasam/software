package MySystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrowseRecipes extends JFrame {
    private JList<String> recipeList;

    public BrowseRecipes() {
        setTitle("Browse Recipes");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Browse Recipes");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JButton searchRecipesButton = new JButton("Search Recipes");
        JButton filterRecipesButton = new JButton("Filter Recipes");
        JButton viewRecipeDetailsButton = new JButton("View Recipe Details");

        // Recipe list to display available recipes
        recipeList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(recipeList);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buttonPanel.add(searchRecipesButton);
        buttonPanel.add(filterRecipesButton);
        buttonPanel.add(viewRecipeDetailsButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel);

        searchRecipesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SearchRecipes(); 
                dispose();
            }
        });

        filterRecipesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FilterRecipes();
                dispose();
            }
        });

        viewRecipeDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedRecipe = recipeList.getSelectedValue();
                if (selectedRecipe != null) {
                    new ViewRecipeDetails(selectedRecipe);
                } else {
                    JOptionPane.showMessageDialog(BrowseRecipes.this, "Please select a recipe from the list.");
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new BrowseRecipes();
    }
}
