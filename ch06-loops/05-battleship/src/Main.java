public class Main {
    public static void main(String[] args) {
        char[][] board = new char[4][4];
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                board[row][col] = '.';
            }
        }

        // A ship of three cells across row 2, from column 1 to column 3.
        board[2][1] = 'S';
        board[2][2] = 'S';
        board[2][3] = 'S';

        int[][] shots = {{0, 0}, {2, 2}, {1, 3}, {2, 3}};
        int hits = 0;
        for (int[] shot : shots) {
            int row = shot[0];
            int col = shot[1];
            if (board[row][col] == 'S') {
                System.out.println("Hit at " + row + "," + col);
                board[row][col] = 'X';
                hits++;
            } else {
                System.out.println("Miss at " + row + "," + col);
            }
        }

        System.out.println("Hits: " + hits);
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                System.out.print(board[row][col]);
            }
            System.out.println();
        }
    }
}
