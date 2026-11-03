public class Main {
    public static void main(String[] args) {
        int[] data = {1, 2, 3};

        replaceWith(data);
        System.out.println("after replaceWith: " + data[0]);

        fillWith(data, 7);
        System.out.println("after fillWith: " + data[0]);
    }

    static void replaceWith(int[] values) {
        values = new int[]{9, 9, 9};
        System.out.println("inside replaceWith: " + values[0]);
    }

    static void fillWith(int[] values, int number) {
        for (int i = 0; i < values.length; i++) {
            values[i] = number;
        }
    }
}
