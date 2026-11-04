public class Main {
    public static void main(String[] args) {
        String text = "";
        for (int i = 0; i < 5; i++) {
            text += i;
        }
        System.out.println("concatenated: " + text);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            builder.append(i);
        }
        System.out.println("builder: " + builder);
        System.out.println("length: " + builder.length());
    }
}
