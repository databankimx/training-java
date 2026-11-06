public class Main {
    // The number of pairs that are out of order: an earlier value larger than a later one. Checks every pair.
    static int countInversions(int[] values) {
        int count = 0;
        for (int i = 0; i < values.length; i++) {
            for (int j = i + 1; j < values.length; j++) {
                if (values[i] > values[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    // Insertion sort on a copy of the list. Returns the number of moves it makes. The copy keeps the caller's list unchanged.
    static int insertionMoves(int[] original) {
        int[] values = original.clone();
        int moves = 0;
        for (int i = 1; i < values.length; i++) {
            int j = i;
            while (j > 0 && values[j - 1] > values[j]) {
                int temp = values[j - 1];
                values[j - 1] = values[j];
                values[j] = temp;
                moves++;
                j--;
            }
        }
        return moves;
    }

    static void report(int[] values) {
        int inversions = countInversions(values);
        int moves = insertionMoves(values);
        System.out.println("Inversions: " + inversions);
        System.out.println("Insertion sort moves: " + moves);
        System.out.println("Match: " + (inversions == moves ? "yes" : "no"));
    }

    public static void main(String[] args) {
        report(new int[] {5, 1, 4, 2, 3});
        report(new int[] {1, 2, 3, 4, 5});
    }
}
