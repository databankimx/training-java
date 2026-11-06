import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> counts = new HashMap<>();
        String[] words = {"apple", "pear", "apple", "fig", "pear", "apple"};

        for (String word : words) {
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }

        System.out.println("Distinct words: " + counts.size());
        System.out.println("apple: " + counts.get("apple"));
        System.out.println("pear: " + counts.get("pear"));
        System.out.println("fig: " + counts.get("fig"));

        if (!counts.containsKey("kiwi")) {
            System.out.println("kiwi is not in the map");
        }
    }
}
