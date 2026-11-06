public class Main {
    // Writes a string as a JSON string: wrapped in double quotes, with the characters JSON needs escaped.
    static String jsonString(String text) {
        StringBuilder json = new StringBuilder("\"");
        for (char c : text.toCharArray()) {
            switch (c) {
                case '"' -> json.append("\\\"");
                case '\\' -> json.append("\\\\");
                case '\n' -> json.append("\\n");
                case '\t' -> json.append("\\t");
                default -> json.append(c);
            }
        }
        return json.append('"').toString();
    }

    public static void main(String[] args) {
        System.out.println(jsonString("Hello"));
        System.out.println(jsonString("Say \"hi\""));
        System.out.println(jsonString("C:\\Users"));
        System.out.println(jsonString("one\ttwo"));
    }
}
