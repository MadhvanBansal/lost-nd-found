package scr.main.servies;

import java.sql.*;

public class UserService {
    // Database URL - this will create a file named "system.db" in your project folder
    private static final String URL = "jdbc:sqlite:system.db";

    static {
        // Initialize the database and create the users table if it doesn't exist
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                         "username TEXT PRIMARY KEY, " +
                         "password TEXT NOT NULL)";
            stmt.execute(sql);
            
            // Add a default admin if table is empty
            if (!checkUserExists("admin")) {
                register("admin", "1234");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkUserExists(String username) {
        String sql = "SELECT 1 FROM users WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Returns true if a record is found
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean register(String username, String password) {
        if (checkUserExists(username)) {
            return false; // User already exists
        }

        String sql = "INSERT INTO users(username, password) VALUES(?, ?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Returns true if credentials match
        } catch (SQLException e) {
            return false;
        }
    }
}