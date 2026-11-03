public class Main {
    public static void main(String[] args) {
        String name = null;
        System.out.println("name is null: " + (name == null));

        if (name != null) {
            System.out.println("name length: " + name.length());
        } else {
            System.out.println("No name yet");
        }

        name = "Ada";
        System.out.println("name is null: " + (name == null));
        System.out.println("name length: " + name.length());
    }
}
