import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("What is your name? ");
        System.out.flush();
        String name = input.nextLine();

        System.out.print("How old are you? ");
        System.out.flush();
        int age = input.nextInt();

        System.out.println("Hello, " + name + ".");
        System.out.println("Next year you will be " + (age + 1) + ".");
    }
}
