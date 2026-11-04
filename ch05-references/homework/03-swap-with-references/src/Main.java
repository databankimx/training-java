import java.util.Scanner;

public class Main {
    static void swapValues(int[] pair) {
        int temp = pair[0];
        pair[0] = pair[1];
        pair[1] = temp;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter a: ");
        System.out.flush();
        int a = input.nextInt();

        System.out.print("Enter b: ");
        System.out.flush();
        int b = input.nextInt();

        int[] pair = {a, b};
        swapValues(pair);
        System.out.println("a = " + pair[0] + ", b = " + pair[1]);
    }
}
