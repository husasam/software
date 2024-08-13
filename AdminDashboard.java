package MySystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboard extends JFrame {
static String user;
    public AdminDashboard(String username) {
    	user=username;
        setTitle("Admin Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel welcomeLabel = new JLabel("Welcome to Admin Dashboard");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton manageUsersButton = new JButton("Manage Users");
        JButton viewReportsButton = new JButton("View Reports");
        JButton manageContentButton = new JButton("Manage Content");

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.add(welcomeLabel);
        panel.add(manageUsersButton);
        panel.add(viewReportsButton);
        panel.add(manageContentButton);

        add(panel, BorderLayout.CENTER);

        // ActionListener for Manage Users button
        manageUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserList(); // Open Manage Users window
                dispose(); // Close AdminDashboard window
            }
        });

        // ActionListener for View Reports button
        viewReportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewReports(); // Open View Reports window
                dispose(); // Close AdminDashboard window
            }
        });

        // ActionListener for Manage Content button
        manageContentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageContent(); // Open Manage Content window
                dispose(); // Close AdminDashboard window
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new AdminDashboard(user);
    }
}
