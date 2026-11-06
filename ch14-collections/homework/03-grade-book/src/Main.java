import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> scores = new ArrayList<>();
        scores.add(72);
        scores.add(88);
        scores.add(95);
        scores.add(60);

        System.out.println("Count: " + scores.size());

        int highest = scores.get(0);
        for (int score : scores) {
            if (score > highest) {
                highest = score;
            }
        }
        System.out.println("Highest: " + highest);

        int lowest = scores.get(0);
        for (int score : scores) {
            if (score < lowest) {
                lowest = score;
            }
        }
        System.out.println("Lowest: " + lowest);

        int total = 0;
        for (int score : scores) {
            total += score;
        }
        System.out.printf("Average: %.2f%n", (double) total / scores.size());
    }
}
