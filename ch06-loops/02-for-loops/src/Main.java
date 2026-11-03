public class Main {
    public static void main(String[] args) {
        for (int i = 1; i <= 3; i++) {
            System.out.println("for pass " + i);
        }

        for (int i = 10; i >= 0; i -= 5) {
            System.out.println("counting down: " + i);
        }

        for (int row = 1; row <= 2; row++) {
            for (int col = 1; col <= 3; col++) {
                System.out.print(row + "," + col + " ");
            }
            System.out.println();
        }
    }
}
