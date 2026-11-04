import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String[] words = {"the", "cat", "saw", "the", "dog", "the", "cat"};
        Set<String> distinct = new HashSet<>();

        for (String word : words) {
            distinct.add(word);
        }

        System.out.println("Words: " + words.length);
        System.out.println("Distinct: " + distinct.size());
        System.out.println("Has dog: " + distinct.contains("dog"));
        System.out.println("Has bird: " + distinct.contains("bird"));
    }
}
