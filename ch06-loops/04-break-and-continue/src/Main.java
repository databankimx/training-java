public class Main {
    public static void main(String[] args) {
        System.out.println("Break at 5:");
        int i = 0;
        while (true) {
            ++i;
            System.out.println(i);
            if (i == 5) {
                break;
            }
        }

        System.out.println("Continue past 5:");
        for (int j = 1; j <= 10; ++j) {
            if (j <= 5) {
                continue;
            }
            System.out.println(j);
        }
    }
}
