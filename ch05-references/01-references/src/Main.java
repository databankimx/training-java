public class Main {
    public static void main(String[] args) {
        int[] food = {1, 2, 3};
        int[] meal = food;

        System.out.println("food[0]: " + food[0]);
        System.out.println("meal[0]: " + meal[0]);

        meal[0] = 99;
        System.out.println("food[0] after changing meal: " + food[0]);
    }
}
