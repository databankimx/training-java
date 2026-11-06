public class Main {
    static void printNumbers(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);
        }
    }

    public static void main(String[] args) {
        int[] numbers = {10, 20, 30};
        int[] first = numbers;

        System.out.println(first[0]);
        System.out.println(first[1]);
        System.out.println(first[2]);

        printNumbers(first);
    }
}
