import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Ada");
        names.add("Grace");
        System.out.println(names);

        java.util.ArrayList<String> other = new java.util.ArrayList<>();
        System.out.println(other.size());
    }
}
