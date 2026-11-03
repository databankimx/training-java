public class Main {
    public static void main(String[] args) {
        int count = 1;
        while (count <= 3) {
            System.out.println("while pass " + count);
            count++;
        }

        int attempts = 5;
        do {
            System.out.println("do-while pass, attempts left: " + attempts);
            attempts--;
        } while (attempts > 5);

        int countdown = 0;
        while (countdown > 0) {
            System.out.println("never printed");
            countdown--;
        }
        System.out.println("Countdown finished at " + countdown);
    }
}
