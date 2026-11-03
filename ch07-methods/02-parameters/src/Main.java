public class Main {
    public static void main(String[] args) {
        int count = 5;
        increment(count);
        System.out.println("count after increment: " + count);

        int doubled = doubleValue(count);
        System.out.println("doubled: " + doubled);
        System.out.println("count still: " + count);
    }

    static void increment(int value) {
        value = value + 1;
        System.out.println("inside increment: " + value);
    }

    static int doubleValue(int value) {
        return value * 2;
    }
}
