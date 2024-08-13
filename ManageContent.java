package MySystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManageContent extends JFrame {

    private JTable contentTable;
    private DefaultTableModel tableModel;

    private JTextField titleField;
    private JTextArea contentField;

    public ManageContent() {
        setTitle("Manage Content");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Title", "Content"}, 0);
        contentTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(contentTable);

        titleField = new JTextField();
        contentField = new JTextArea(5, 20);

        JButton addButton = new JButton("Add Content");
        JButton updateButton = new JButton("Update Content");
        JButton deleteButton = new JButton("Delete Content");
        JButton refreshButton = new JButton("Refresh");

        JPanel formPanel = new JPanel(new GridLayout(3, 2));
        formPanel.add(new JLabel("Title:"));
        formPanel.add(titleField);
        formPanel.add(new JLabel("Content:"));
        formPanel.add(new JScrollPane(contentField));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
        loadContentData();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addContent();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateContent();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteContent();
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadContentData();
            }
        });

        setVisible(true);
    }

    private void loadContentData() {
        String query = "SELECT id, title, content FROM content";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            tableModel.setRowCount(0); // Clear existing data

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                tableModel.addRow(new Object[]{id, title, content});
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading content data.");
        }
    }

    private void addContent() {
        String title = titleField.getText();
        String content = contentField.getText();

        String query = "INSERT INTO content (title, content) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, title);
            pstmt.setString(2, content);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Content added successfully.");
            loadContentData();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding content.");
        }
    }

    private void updateContent() {
        int selectedRow = contentTable.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            String title = titleField.getText();
            String content = contentField.getText();

            String query = "UPDATE content SET title = ?, content = ? WHERE id = ?";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {

                pstmt.setString(1, title);
                pstmt.setString(2, content);
                pstmt.setInt(3, id);
                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "Content updated successfully.");
                loadContentData();

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating content.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a content to update.");
        }
    }

    private void deleteContent() {
        int selectedRow = contentTable.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);

            String query = "DELETE FROM content WHERE id = ?";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {

                pstmt.setInt(1, id);
                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "Content deleted successfully.");
                loadContentData();

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error deleting content.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a content to delete.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ManageContent());
    }
}
