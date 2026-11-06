public class Main {
    public static void main(String[] args) {
        int number = 10;
        System.out.println(number);

        number = 15;
        System.out.println(number);

        int whole = -3;
        double price = 5.99;
        char letter = 'a';
        boolean flag = true;
        String text = "Java";

        System.out.println("int: " + whole);
        System.out.println("double: " + price);
        System.out.println("char: " + letter);
        System.out.println("boolean: " + flag);
        System.out.println("String: " + text);

        float small = 5.75f;
        double large = 19.99;
        double scientific = 35e3;
        System.out.println("float: " + small);
        System.out.println("double: " + large);
        System.out.println("scientific: " + scientific);

        char code = 97;
        System.out.println("code 97 as char: " + code);
        System.out.println("'a' + 1 = " + ('a' + 1));

        int x = 5, y = 6, z = 50;
        System.out.println(x + " " + y + " " + z);

        int first, second, third;
        first = second = third = 50;
        System.out.println(first + " " + second + " " + third);

        final double pi = 3.14159;
        System.out.println("pi = " + pi);
    }
}
