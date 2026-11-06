public class Main {
    static void greet() {
        System.out.println("I just got executed!");
    }

    public static void main(String[] args) {
        System.out.println("Before the calls");
        greet();
        greet();
        System.out.println("After the calls");
    }
}
