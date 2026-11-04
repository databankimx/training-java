public class Main {
    public static void main(String[] args) {
        String[][] letters = {
            {"A", "B", "C", "D"},
            {"E", "F", "G", "H"}
        };

        System.out.println(letters[1][2]);
        System.out.println(letters[0][3]);

        int[][] grid = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("Center: " + grid[1][1]);
        System.out.println("Sum of the first row: " + (grid[0][0] + grid[0][1] + grid[0][2]));

        grid[2][0] = 0;
        System.out.println("Bottom-left: " + grid[2][0]);
    }
}
