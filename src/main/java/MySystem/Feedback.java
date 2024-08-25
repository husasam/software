package MySystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Feedback {
    private int id;
    private String content;
    private int userId;
    private int recipeId;

    public Feedback(int int1, int int2, int int3, String string, Timestamp timestamp) {
		// TODO Auto-generated constructor stub
	}

	// Getters
    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public int getUserId() {
        return userId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

	public int getProductId() {
		return this.id;
	}

	public String getFeedbackText() {
		return this.content;
	}
}


class FeedbackDAO {

    public void addFeedback(Feedback feedback) throws SQLException {
        String query = "INSERT INTO feedback (user_id, product_id, feedback_text) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, feedback.getUserId());
            stmt.setInt(2, feedback.getProductId());
            stmt.setString(3, feedback.getFeedbackText());
            stmt.executeUpdate();
        }
    }

    public List<Feedback> getFeedbackByProductId(int productId) throws SQLException {
        String query = "SELECT * FROM feedback WHERE product_id = ?";
        List<Feedback> feedbackList = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	feedbackList.add(new Feedback(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("product_id"),rs.getString("feedback_text"), rs.getTimestamp("feedback_date")));
            }
        }
        return feedbackList;
    }
}


