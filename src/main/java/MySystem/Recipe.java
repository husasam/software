package MySystem;

public class Recipe {
    private int id;
    private String title;
    private String description;
    private int createdBy;

    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }
}
