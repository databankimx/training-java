import java.util.Scanner;

public class Main {
    // Reads one number from the player. Returns null when the player types q or quit, so the game can end.
    static Integer readNumber(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            System.out.flush();
            if (input.hasNextInt()) {
                return input.nextInt();
            }
            String word = input.next();
            if (word.startsWith("q") || word.startsWith("Q")) {
                return null;
            }
            System.out.println("Please type a number from 0 to 3.\n");
        }
    }

    public static void main(String[] args) {
        int[][] ships = {
            {0, 1, 1, 0},
            {0, 0, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 0}
        };

        Scanner input = new Scanner(System.in);
        int hits = 0;
        int turns = 0;

        while (hits < 4) {
            Integer row = readNumber(input, "Row: ");
            if (row == null) {
                break;
            }
            Integer column = readNumber(input, "Column: ");
            if (column == null) {
                break;
            }

            if (row < 0 || row > 3 || column < 0 || column > 3) {
                System.out.println("Please choose numbers from 0 to 3.\n");
                continue;
            }

            turns++;
            if (ships[row][column] == 1) {
                ships[row][column] = 0;
                hits++;
                System.out.println("Hit! " + (4 - hits) + " left.\n");
            } else {
                System.out.println("Miss\n");
            }
        }

        if (hits == 4) {
            System.out.println("All four ships hit in " + turns + " turns.");
        } else {
            System.out.println("Game over with " + (4 - hits) + " ships left.");
        }
    }
}
