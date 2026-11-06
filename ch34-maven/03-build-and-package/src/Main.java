public class Main {
    public static void main(String[] args) {
        System.out.println("Arguments received: " + args.length);
        for (String arg : args) {
            System.out.println("  " + arg);
        }
    }
}
