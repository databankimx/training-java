import java.util.ArrayList;
import java.util.List;

public class Main {
    static class Resource {
        final String name;

        Resource(String name) {
            this.name = name;
            System.out.println("Acquire " + name);
        }
    }

    public static void main(String[] args) {
        Resource original = new Resource("shared");
        List<Resource> holders = new ArrayList<>();

        holders.add(original);
        System.out.println("Holders: " + holders.size());

        holders.add(original);
        System.out.println("Holders: " + holders.size());

        holders.clear();
        System.out.println("Holders: " + holders.size());

        original = null;
        System.out.println("End");
    }
}
