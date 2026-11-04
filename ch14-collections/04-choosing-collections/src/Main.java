import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>(List.of("Ada", "Grace", "Alan"));

        Map<String, Integer> lengths = new TreeMap<>();
        for (String name : names) {
            lengths.put(name, name.length());
        }

        System.out.println("Names: " + names.size());
        for (var entry : lengths.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
