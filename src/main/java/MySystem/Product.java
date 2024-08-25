package MySystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Product {
	private int id;
    private String name;
    private double price;
    private String category;
    private int storeOwnerId;

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStoreOwnerId() {
        return storeOwnerId;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStoreOwnerId(int storeOwnerId) {
        this.storeOwnerId = storeOwnerId;
    }

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String string) {
		this.category=string;
		
	}
}

class ProductDAO {
    public void addProduct(Product product) {
        String query = "INSERT INTO products (name, price, category, store_owner_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setString(3, product.getCategory());
            stmt.setInt(4, product.getStoreOwnerId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getProductsByOwner(int ownerId) {
        String query = "SELECT * FROM products WHERE store_owner_id = ?";
        List<Product> products = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, ownerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setCategory(rs.getString("category"));
                product.setStoreOwnerId(rs.getInt("store_owner_id"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

}