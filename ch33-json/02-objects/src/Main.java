import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    // Writes one value as JSON. The types handled here are text, whole numbers, true or false, and null.
    static String jsonValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String text) {
            return jsonString(text);
        }
        return value.toString();
    }

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

    // Writes a map as a JSON object: the keys, in order, each followed by its value.
    static String jsonObject(Map<String, ?> object) {
        StringBuilder json = new StringBuilder("{");
        boolean first = true;
        for (Map.Entry<String, ?> entry : object.entrySet()) {
            if (!first) {
                json.append(", ");
            }
            json.append(jsonString(entry.getKey())).append(": ").append(jsonValue(entry.getValue()));
            first = false;
        }
        return json.append("}").toString();
    }

    public static void main(String[] args) {
        // A LinkedHashMap keeps the keys in the order they were added. A plain HashMap doesn't promise any order.
        Map<String, Object> person = new LinkedHashMap<>();
        person.put("name", "Ada");
        person.put("age", 36);
        person.put("active", true);
        person.put("nickname", null);
        System.out.println(jsonObject(person));
    }
}
