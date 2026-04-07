package scr.main.servies;

import java.util.HashMap;

public class UserService {
    private static HashMap<String, String> users = new HashMap<>();

    static {
        users.put("admin", "1234");
    }

    public static boolean login(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    public static boolean register(String username, String password) {
        if (users.containsKey(username)) {
            return false; // User already exists
        }
        users.put(username, password);
        return true;
    }
}