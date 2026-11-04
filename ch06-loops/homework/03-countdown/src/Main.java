import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a starting number: ");
        System.out.flush();
        int start = input.nextInt();

        for (int i = start; i > 0; i--) {
            System.out.println(i);
        }
        System.out.println("Liftoff!");
    }
}
