import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> counts = new TreeMap<>();
        counts.put("pear", 5);
        counts.put("apple", 3);
        counts.put("fig", 2);

        // The map keeps its keys sorted, so the words are printed alphabetically.
        for (var entry : counts.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        int total = 0;
        for (var entry : counts.entrySet()) {
            total += entry.getValue();
        }
        System.out.println("Total: " + total);
    }
}
