public class Main {
    public static void main(String[] args) {
        int[] original = {1, 2, 3};
        int[] alias = original;

        alias[0] = 99;
        System.out.println("original[0]: " + original[0]);
        System.out.println("alias[0]: " + alias[0]);

        alias = new int[]{7, 8, 9};
        System.out.println("original[0] after alias is reassigned: " + original[0]);
        System.out.println("alias[0] now: " + alias[0]);
    }
}
