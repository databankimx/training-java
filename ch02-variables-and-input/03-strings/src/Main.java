public class Main {
    public static void main(String[] args) {
        String first = "Hello";
        String second = "Java";
        String greeting = first + ", " + second + "!";
        System.out.println(greeting);

        System.out.println("Length: " + greeting.length());
        System.out.println("First letter: " + greeting.charAt(0));

        greeting += " Welcome.";
        System.out.println(greeting);

        System.out.println("Tab:\tBackslash:\\ Quote:\"");
    }
}
