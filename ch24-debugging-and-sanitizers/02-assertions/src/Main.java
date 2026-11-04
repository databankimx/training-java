public class Main {
    // Returns the average of a list of scores. The caller promises at least one score.
    static double average(int[] scores) {
        assert scores.length > 0 : "average needs at least one score";
        int total = 0;
        for (int score : scores) {
            total += score;
        }
        return (double) total / scores.length;
    }

    public static void main(String[] args) {
        System.out.println("Average: " + average(new int[] {70, 80, 90}));

        // This call breaks the promise. Assertions are switched off by default, so the program carries on.
        System.out.println("Empty average: " + average(new int[] {}));
        System.out.println("Program continued");
    }
}
