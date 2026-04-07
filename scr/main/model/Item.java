package scr.main.model;

import java.time.LocalDate;

public class Item {
    private String name;
    private String description;
    private String location;
    private String type;
    private LocalDate dateAdded;
    private String imagePath;

    public Item(String name, String description, String location, String type, String imagePath) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.type = type;
        this.imagePath = imagePath;
        this.dateAdded = LocalDate.now();
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }
    public LocalDate getDateAdded() { return dateAdded; }
    public String getImagePath() { return imagePath; } 
}