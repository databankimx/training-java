public class Main {
    static void printNumbers(int[] numbers, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(numbers[i]);
        }
    }

    public static void main(String[] args) {
        int[] myNumbers = {10, 20, 30, 40, 50};
        printNumbers(myNumbers, 5);
        System.out.println("First three:");
        printNumbers(myNumbers, 3);
    }
}
