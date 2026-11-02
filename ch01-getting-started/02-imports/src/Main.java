import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> greetings = List.of("Hello World!", "I am learning Java");
        for (String greeting : greetings) {
            System.out.println(greeting);
        }
    }
}
