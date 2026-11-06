import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("Enter your age (0-120): ");
            System.out.flush();
            try {
                if (!input.hasNextInt()) {
                    input.next();
                    throw new IllegalArgumentException("not a number");
                }
                int age = input.nextInt();
                if (age < 0 || age > 120) {
                    throw new IndexOutOfBoundsException("age must be from 0 to 120");
                }
                System.out.println("Age accepted: " + age);
                break;
            } catch (IllegalArgumentException | IndexOutOfBoundsException error) {
                System.out.println(error.getMessage());
            }
        }
    }
}
