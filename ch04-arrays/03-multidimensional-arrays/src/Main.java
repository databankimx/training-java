public class Main {
    public static void main(String[] args) {
        int[][] table = {
            {1, 2, 3},
            {4, 5, 6}
        };
        System.out.println("Rows: " + table.length);
        System.out.println("Columns in row 0: " + table[0].length);
        System.out.println("Row 1, column 2: " + table[1][2]);

        int[][] grid = new int[2][3];
        grid[0][0] = 7;
        grid[1][2] = 9;
        System.out.println("grid[0][0]: " + grid[0][0]);
        System.out.println("grid[0][1] (not set yet): " + grid[0][1]);
        System.out.println("grid[1][2]: " + grid[1][2]);
    }
}
