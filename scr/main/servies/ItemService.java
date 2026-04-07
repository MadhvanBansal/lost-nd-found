package scr.main.servies;
import java.util.ArrayList;

import scr.main.model.Item;

public class ItemService {

    private static ArrayList<Item> items = new ArrayList<>();
    private static int lostCount = 0;
    private static int foundCount = 0;

    public static void addItem(Item item) {
        if (item != null) {
            items.add(item);
            if (item.getType().equalsIgnoreCase("Lost")) {
                lostCount++;
            } else if (item.getType().equalsIgnoreCase("Found")) {
                foundCount++;
            }
        }
    }

    public static ArrayList<Item> searchItem(String keyword) {
        ArrayList<Item> result = new ArrayList<>();
        
        // Blank search should reveal all data in tables, rather than nothing
        if (keyword == null || keyword.trim().isEmpty()) {
            return items; 
        }

        for (Item i : items) {
            if (i.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(i);
            }
        }
        return result;
    }

    public static ArrayList<Item> getAllItems() {
        return items;
    }

    public static int getLostCount() {
        return lostCount;
    }

    public static int getFoundCount() {
        return foundCount;
    }
}