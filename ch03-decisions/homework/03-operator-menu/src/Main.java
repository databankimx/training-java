import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter an operator (+, -, *, /): ");
        System.out.flush();
        char op = input.next().charAt(0);

        System.out.print("Enter the first number: ");
        System.out.flush();
        double first = input.nextDouble();

        System.out.print("Enter the second number: ");
        System.out.flush();
        double second = input.nextDouble();

        switch (op) {
            case '+':
                System.out.println(first + second);
                break;
            case '-':
                System.out.println(first - second);
                break;
            case '*':
                System.out.println(first * second);
                break;
            case '/':
                if (second == 0) {
                    System.out.println("Cannot divide by zero.");
                } else {
                    System.out.println(first / second);
                }
                break;
            default:
                System.out.println("Unknown operator.");
                break;
        }
    }
}
