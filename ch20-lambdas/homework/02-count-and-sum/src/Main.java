import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // The lambda cannot assign to a local variable, so each total is kept in an array of one element.
        int[] evenCount = {0};
        int[] evenSum = {0};

        numbers.forEach(value -> {
            if (value % 2 == 0) {
                evenCount[0]++;
                evenSum[0] += value;
            }
        });

        System.out.println("Even count: " + evenCount[0]);
        System.out.println("Sum of evens: " + evenSum[0]);
    }
}
