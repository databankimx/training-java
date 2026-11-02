public class Main {
    public static void main(String[] args) {
        int a = 7;
        int b = 2;

        System.out.println("a + b = " + (a + b));
        System.out.println("a - b = " + (a - b));
        System.out.println("a * b = " + (a * b));
        System.out.println("a / b = " + (a / b));
        System.out.println("a % b = " + (a % b));

        double c = 7.0;
        System.out.println("c / b = " + (c / b));
        System.out.println("a / 2.0 = " + (a / 2.0));

        double average = (double) a / b;
        System.out.printf("average = %.2f%n", average);

        System.out.println("2 + 3 * 4 = " + (2 + 3 * 4));
        System.out.println("(2 + 3) * 4 = " + ((2 + 3) * 4));
        System.out.println("10 - 4 - 3 = " + (10 - 4 - 3));

        int count = 0;
        count++;
        count += 5;
        System.out.println("count = " + count);

        int total = 10;
        total -= 3;
        total *= 2;
        total /= 4;
        total %= 4;
        System.out.println("total = " + total);

        System.out.println("max(a, b) = " + Math.max(a, b));
        System.out.println("min(a, b) = " + Math.min(a, b));
        System.out.printf("sqrt(16) = %.2f%n", Math.sqrt(16));
        System.out.printf("round(2.6) = %d%n", Math.round(2.6));
        System.out.printf("pow(2, 10) = %.2f%n", Math.pow(2, 10));
    }
}
