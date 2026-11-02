import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter some text: ");
        System.out.flush();
        String text = input.nextLine();
        System.out.println("You entered: " + text);

        System.out.print("Enter a number: ");
        System.out.flush();
        int first = input.nextInt();
        System.out.println("You entered: " + first);

        System.out.print("Enter another number: ");
        System.out.flush();
        int second = input.nextInt();
        System.out.println("You entered: " + second);

        System.out.println(first + " + " + second + " = " + (first + second));
    }
}
