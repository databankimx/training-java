public class Main {
    public static void main(String[] args) {
        int[] primes = {2, 3, 5, 7, 11};
        System.out.println("Primes: " + primes.length + " values, last is " + primes[4]);

        int[] squares = new int[4];
        squares[0] = 1;
        squares[1] = 4;
        squares[2] = 9;
        System.out.println("Fourth square (not set yet): " + squares[3]);
        System.out.println("Squares length: " + squares.length);

        String[] names = new String[3];
        names[0] = "Ada";
        System.out.println("Name 1: " + names[0]);
        System.out.println("Name 2: " + names[1]);

        double[] prices = {4.99, 12.50};
        System.out.println("Price: " + prices[1]);
    }
}
