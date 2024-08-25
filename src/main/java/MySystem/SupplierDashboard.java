package MySystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SupplierDashboard extends JFrame {

    public SupplierDashboard(String username) {
        setTitle("Supplier Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel welcomeLabel = new JLabel("Welcome to Supplier Dashboard");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton manageProductsButton = new JButton("Manage Products");
        JButton viewOrdersButton = new JButton("View Orders");
        JButton communicateButton = new JButton("Communicate with Store Owners");
        JButton EditProfileButton = new JButton("EditProfile");

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.add(welcomeLabel);
        panel.add(manageProductsButton);
        panel.add(viewOrdersButton);
        panel.add(communicateButton);
        panel.add(EditProfileButton);

        add(panel, BorderLayout.CENTER);
        manageProductsButton.addActionListener(e -> {
            new ManageProducts();
            dispose();
        });
        
        viewOrdersButton.addActionListener(e -> {
            new ViewOrders();
            dispose();
        });
        
        communicateButton.addActionListener(e -> {
            new CommunicateWithStoreOwners(username);
            dispose(); 
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
