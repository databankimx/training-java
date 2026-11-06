public class Main {
    public static void main(String[] args) {
        System.out.println("NOT");
        System.out.println(" b  ~b");
        for (int b = 0; b <= 1; b++) {
            System.out.println(" " + b + "  " + (~b & 1));
        }

        System.out.println();
        System.out.println("b1 b2  AND  OR  XOR");
        for (int b1 = 0; b1 <= 1; b1++) {
            for (int b2 = 0; b2 <= 1; b2++) {
                System.out.println(" " + b1 + "  " + b2 + "    " + (b1 & b2) + "   " + (b1 | b2) + "   " + (b1 ^ b2));
            }
        }
    }
}
