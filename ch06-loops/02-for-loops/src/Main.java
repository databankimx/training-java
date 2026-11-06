public class Main {
    public static void main(String[] args) {
        System.out.println("Counting up:");
        for (int i = 0; i < 5; ++i) {
            System.out.println(i + 1);
        }

        System.out.println("Counting by twos:");
        for (int i = 0; i < 10; i += 2) {
            System.out.println(i + 2);
        }

        System.out.println("Counting down:");
        for (int i = 5; i > 0; i--) {
            System.out.println(i);
        }

        System.out.println("Nested loops:");
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 3; ++j) {
                System.out.println(i + ", " + j);
            }
        }
    }
}
