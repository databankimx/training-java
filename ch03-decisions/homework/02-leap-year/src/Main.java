public class Main {
    public static void main(String[] args) {
        java.util.Scanner input = new java.util.Scanner(System.in);
        System.out.print("Enter a year: ");
        System.out.flush();
        int year = input.nextInt();

        boolean isLeap = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
        if (isLeap) {
            System.out.println(year + " is a leap year.");
        } else {
            System.out.println(year + " is not a leap year.");
        }
    }
}
