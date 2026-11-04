public class Main {
    public static void main(String[] args) {
        int[] values = {3, 1, 4, 1, 5};
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            if (i > 0) {
                line.append(", ");
            }
            line.append(values[i]);
        }
        System.out.println(line.toString());
        System.out.println("Length: " + line.length());
    }
}
