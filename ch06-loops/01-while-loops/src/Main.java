public class Main {
    public static void main(String[] args) {
        System.out.println("while loop:");
        int i = 0;
        while (i < 3) {
            System.out.println(++i);
        }

        System.out.println("do-while loop:");
        int j = 10;
        do {
            System.out.println("j is " + j);
            j++;
        } while (j < 10);
    }
}
