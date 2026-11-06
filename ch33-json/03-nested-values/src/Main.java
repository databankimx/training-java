import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {
    // Writes any supported value as JSON. A map is an object and a list is an array, and each is written by calling
    // this method on its own values, so nested data of any depth is handled by the same code.
    static String jsonValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String text) {
            return jsonString(text);
        }
        if (value instanceof Map<?, ?> object) {
            return jsonObject(object);
        }
        if (value instanceof List<?> array) {
            return jsonArray(array);
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

    static String jsonObject(Map<?, ?> object) {
        StringBuilder json = new StringBuilder("{");
        boolean first = true;
        for (Map.Entry<?, ?> entry : object.entrySet()) {
            if (!first) {
                json.append(", ");
            }
            json.append(jsonString(entry.getKey().toString())).append(": ").append(jsonValue(entry.getValue()));
            first = false;
        }
        return json.append("}").toString();
    }

    // Writes a list as a JSON array: the values in order, in square brackets.
    static String jsonArray(List<?> array) {
        StringBuilder json = new StringBuilder("[");
        boolean first = true;
        for (Object item : array) {
            if (!first) {
                json.append(", ");
            }
            json.append(jsonValue(item));
            first = false;
        }
        return json.append("]").toString();
    }

    public static void main(String[] args) {
        Map<String, Object> address = new LinkedHashMap<>();
        address.put("city", "The Colony");
        address.put("state", "TX");

        Map<String, Object> person = new LinkedHashMap<>();
        person.put("name", "Ada");
        person.put("languages", List.of("Java", "Python"));
        person.put("address", address);
        person.put("scores", List.of(90, 85));
        System.out.println(jsonValue(person));
    }
}
