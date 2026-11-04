public class Main {
    public static void main(String[] args) {
        int[] partial = new int[5];
        partial[0] = 7;
        partial[1] = 8;
        System.out.println(partial[0] + " " + partial[1] + " " + partial[2] + " " + partial[4]);

        String[] cars = {"Volvo", "BMW", "Ford", "Mazda", "Tesla"};
        System.out.println("Cars: " + cars.length);
        System.out.println("Last: " + cars[cars.length - 1]);

        int[] squares = new int[3];
        squares[0] = 1;
        squares[1] = 4;
        squares[2] = 9;
        System.out.println(squares[2]);
    }
}
