public class Main {
    public static void main(String[] args) {
        double[] readings = {18.5, 19.0, 21.5, 20.0, 17.0};

        System.out.println("First reading: " + readings[0]);
        System.out.println("Last reading: " + readings[readings.length - 1]);

        double total = readings[0] + readings[1] + readings[2] + readings[3] + readings[4];
        System.out.printf("Average: %.2f%n", total / readings.length);
    }
}
