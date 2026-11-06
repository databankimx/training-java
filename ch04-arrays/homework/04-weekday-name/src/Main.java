import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        Scanner input = new Scanner(System.in);

        System.out.print("Enter a day number (1-7): ");
        System.out.flush();
        int number = input.nextInt();

        if (number >= 1 && number <= days.length) {
            System.out.println("Day " + number + " is " + days[number - 1]);
        } else {
            System.out.println("That isn't a valid day number.");
        }
    }
}
