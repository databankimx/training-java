public class Main {
    public static void main(String[] args) {
        String name = null;

        if (name == null) {
            System.out.println("No name entered.");
        } else if (name.equals("Ada")) {
            System.out.println("Welcome back, Ada.");
        } else {
            System.out.println("Hello, " + name + ".");
        }

        name = "Ada";
        if (name == null) {
            System.out.println("No name entered.");
        } else if (name.equals("Ada")) {
            System.out.println("Welcome back, Ada.");
        } else {
            System.out.println("Hello, " + name + ".");
        }
    }
}
