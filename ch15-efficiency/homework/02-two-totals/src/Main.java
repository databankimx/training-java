import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int count = 10000;

        long primitiveTotal = 0;
        for (int i = 1; i <= count; i++) {
            primitiveTotal += i;
        }

        List<Integer> boxes = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            boxes.add(i);
        }
        long boxedTotal = 0;
        for (int value : boxes) {
            boxedTotal += value;
        }

        System.out.println("Primitive total: " + primitiveTotal);
        System.out.println("Boxed total: " + boxedTotal);
        System.out.println("Totals agree: " + (primitiveTotal == boxedTotal));
    }
}
