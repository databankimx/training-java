import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> counts = new TreeMap<>();
        String[] words = {"apple", "pear", "apple", "fig", "pear", "apple"};

        for (String word : words) {
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }

        for (var entry : counts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("apple appears " + counts.get("apple") + " times");

        if (!counts.containsKey("kiwi")) {
            System.out.println("kiwi is not in the map");
        }
    }
}
