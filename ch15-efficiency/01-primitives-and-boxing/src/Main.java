import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        long total = 0;
        for (int i = 1; i <= 5; i++) {
            total += i;
        }
        System.out.println("primitive total: " + total);

        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            numbers.add(i);
        }
        int boxedTotal = 0;
        for (int value : numbers) {
            boxedTotal += value;
        }
        System.out.println("boxed total: " + boxedTotal);
    }
}
