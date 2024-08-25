package MySystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserDashboard extends JFrame {

    public UserDashboard(String username) {
        setTitle("User Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel welcomeLabel = new JLabel("Welcome to User Dashboard");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton browseRecipesButton = new JButton("Browse Recipes");
        JButton purchaseDessertsButton = new JButton("Purchase Desserts");
        JButton viewOrdersButton = new JButton("View Orders");
        JButton EditProfileButton = new JButton("EditProfile");

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.add(welcomeLabel);
        panel.add(browseRecipesButton);
        panel.add(purchaseDessertsButton);
        panel.add(viewOrdersButton);
        panel.add(EditProfileButton);

        add(panel, BorderLayout.CENTER);
        viewOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewOrders();
                dispose();
            }
        });
        
        purchaseDessertsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PurchaseDesserts();
                dispose();
            }
        });
        
        browseRecipesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BrowseRecipes();
                dispose();
            }
        });
        EditProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> new UserProfile(username).setVisible(true));
            }
        });

        setVisible(true);
    }
}
