public class Main {
    public static void main(String[] args) {
        int[] scores = {72, 95, 88, 61};
        System.out.println("Largest score: " + largest(scores));

        int[] temperatures = {-3, 4, 0};
        System.out.println("Largest temperature: " + largest(temperatures));
    }

    static int largest(int[] values) {
        int best = values[0];
        for (int i = 1; i < values.length; i++) {
            if (values[i] > best) {
                best = values[i];
            }
        }
        return best;
    }
}
