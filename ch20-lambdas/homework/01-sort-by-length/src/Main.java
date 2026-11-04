import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>(List.of("pear", "fig", "banana", "kiwi"));

        // List.sort keeps the original order of words that compare as equal, so "pear" stays before "kiwi".
        words.sort((first, second) -> Integer.compare(first.length(), second.length()));

        StringBuilder line = new StringBuilder("By length:");
        for (String word : words) {
            line.append(" ").append(word);
        }
        System.out.println(line);
    }
}
