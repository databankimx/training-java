import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String[] words = {"the", "cat", "saw", "the", "dog", "the", "cat"};
        Map<String, Integer> counts = new HashMap<>();

        for (String word : words) {
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }

        System.out.println("the: " + counts.get("the"));
        System.out.println("cat: " + counts.get("cat"));
        System.out.println("dog: " + counts.get("dog"));
    }
}
