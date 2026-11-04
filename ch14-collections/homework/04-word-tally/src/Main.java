import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        String[] words = {"red", "blue", "red", "green", "blue", "red"};
        Map<String, Integer> counts = new TreeMap<>();

        for (String word : words) {
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }

        for (var entry : counts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        String best = null;
        int bestCount = 0;
        for (var entry : counts.entrySet()) {
            if (entry.getValue() > bestCount) {
                best = entry.getKey();
                bestCount = entry.getValue();
            }
        }
        System.out.println("Most common: " + best);
    }
}
