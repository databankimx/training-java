import java.util.regex.Pattern;

public class Main {
    // A simple shape for an address: letters, digits, dots, or dashes, an at sign, a domain, and a dot and a suffix.
    static final Pattern EMAIL = Pattern.compile("[\\w.-]+@[\\w-]+\\.[a-z]{2,}");

    static boolean isEmail(String text) {
        return EMAIL.matcher(text).matches();
    }

    public static void main(String[] args) {
        System.out.println("ada@example.com: " + isEmail("ada@example.com"));
        System.out.println("grace.h@mail.org: " + isEmail("grace.h@mail.org"));
        System.out.println("no-at-sign.com: " + isEmail("no-at-sign.com"));
        System.out.println("two@@signs.com: " + isEmail("two@@signs.com"));
        System.out.println("ada@examplexcom: " + isEmail("ada@examplexcom"));
    }
}
