public class Main {
    public static void main(String[] args) {
        System.out.println("Arguments received: " + args.length);
        for (int i = 0; i < args.length; i++) {
            System.out.println("Argument " + (i + 1) + ": " + args[i]);
        }

        if (args.length == 2) {
            int first = Integer.parseInt(args[0]);
            int second = Integer.parseInt(args[1]);
            System.out.println(first + " + " + second + " = " + (first + second));
        } else {
            System.out.println("Give two numbers, for example: program 4 9");
        }
    }
}
