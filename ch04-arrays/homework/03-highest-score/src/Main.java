import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] scores = new int[3];

        System.out.print("Score 1: ");
        System.out.flush();
        scores[0] = input.nextInt();

        System.out.print("Score 2: ");
        System.out.flush();
        scores[1] = input.nextInt();

        System.out.print("Score 3: ");
        System.out.flush();
        scores[2] = input.nextInt();

        int highest = scores[0];
        if (scores[1] > highest) {
            highest = scores[1];
        }
        if (scores[2] > highest) {
            highest = scores[2];
        }

        System.out.println("The highest score is " + highest);
    }
}
