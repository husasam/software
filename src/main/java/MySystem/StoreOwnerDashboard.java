package MySystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoreOwnerDashboard extends JFrame {

    public StoreOwnerDashboard(String username) {
        setTitle("Store Owner Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel welcomeLabel = new JLabel("Welcome to Store Owner Dashboard");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton manageProductsButton = new JButton("Manage Products");
        JButton viewSalesButton = new JButton("View Sales");
        JButton dynamicDiscountButton = new JButton("Set Dynamic Discounts");
        JButton EditProfileButton = new JButton("EditProfile");
        
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.add(welcomeLabel);
        panel.add(manageProductsButton);
        panel.add(viewSalesButton);
        panel.add(dynamicDiscountButton);
        panel.add(EditProfileButton);
        
        add(panel, BorderLayout.CENTER);
        manageProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageProducts();
                dispose();
            }
        });
        viewSalesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewSales();
                dispose();
            }
        });
        dynamicDiscountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SetDynamicDiscounts(username);
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
