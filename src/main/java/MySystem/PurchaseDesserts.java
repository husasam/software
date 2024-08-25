package MySystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PurchaseDesserts extends JFrame {

    public PurchaseDesserts() {
        setTitle("Purchase Desserts");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Purchase Desserts");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JButton viewAvailableDessertsButton = new JButton("View Available Desserts");
        JButton addToCartButton = new JButton("Add to Cart");
        JButton checkoutButton = new JButton("Checkout");

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.add(label);
        panel.add(viewAvailableDessertsButton);
        panel.add(addToCartButton);
        panel.add(checkoutButton);

        add(panel, BorderLayout.CENTER);
        viewAvailableDessertsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewAvailableDesserts();
                dispose();
            }
        });

        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddToCart(); 
                dispose(); 
            }
        });

        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Checkout(); 
                dispose();
            }
        });

        setVisible(true);
    }
}

