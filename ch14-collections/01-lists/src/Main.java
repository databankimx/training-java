import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> scores = new ArrayList<>();
        scores.add(72);
        scores.add(88);
        scores.add(95);

        System.out.println("Count: " + scores.size());
        System.out.println("First: " + scores.get(0));
        System.out.println("Last: " + scores.get(scores.size() - 1));

        int total = 0;
        for (int score : scores) {
            total += score;
        }
        System.out.println("Total: " + total);
        System.out.println("Average: " + total / scores.size());

        scores.add(60);
        System.out.println("After adding: " + scores.size());

        try {
            scores.get(10);
        } catch (IndexOutOfBoundsException error) {
            System.out.println("Index 10 is out of range.");
        }

        List<Integer> stack = new ArrayList<>(List.of(1, 2, 3));
        System.out.println("Front: " + stack.get(0) + ", back: " + stack.get(stack.size() - 1));

        stack.remove(stack.size() - 1);
        System.out.println("After pop_back: size " + stack.size() + ", back " + stack.get(stack.size() - 1));

        stack.add(1, 99);
        stack.remove(0);
        System.out.println("Elements: " + stack.get(0) + " " + stack.get(1));
    }
}
