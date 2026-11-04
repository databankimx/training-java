public class Main {
    public static void main(String[] args) {
        String first = "Java";
        String second = "Java";
        String third = new String("Java");

        System.out.println("first == second: " + (first == second));
        System.out.println("first == third: " + (first == third));
        System.out.println("first.equals(third): " + first.equals(third));

        int[] a = {1, 2};
        int[] b = {1, 2};
        System.out.println("a == b: " + (a == b));
    }
}
