public class Main {
    public static void main(String[] args) {
        int[] scores = {90, 85, 77};
        System.out.println("total: " + total(scores));

        System.out.println("first before: " + scores[0]);
        doubleFirst(scores);
        System.out.println("first after: " + scores[0]);
    }

    static int total(int[] values) {
        int sum = 0;
        for (int value : values) {
            sum += value;
        }
        return sum;
    }

    static void doubleFirst(int[] values) {
        values[0] = values[0] * 2;
    }
}
