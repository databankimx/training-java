public class Main {
    public static void main(String[] args) {
        int[] values = {4, 9, 12, 7, 12, 3};
        int target = 12;
        int foundAt = -1;

        for (int i = 0; i < values.length; i++) {
            if (values[i] == target) {
                foundAt = i;
                break;
            }
        }

        if (foundAt == -1) {
            System.out.println(target + " was not found.");
        } else {
            System.out.println(target + " first found at index " + foundAt + ".");
        }
    }
}
