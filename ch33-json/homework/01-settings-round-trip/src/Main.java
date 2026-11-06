import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Settings as they might be saved to a file.
        String saved = "{\"volume\": 7, \"theme\": \"dark\", \"autosave\": true}";

        Map<String, Object> settings = castToMap(Json.deserialize(saved));
        settings.put("volume", 9L);
        settings.put("language", "en-US");

        String updated = Json.serialize(settings);
        System.out.println("Updated: " + updated);
        System.out.println("Reads back: " + Json.deserialize(updated).equals(settings));
    }

    // The deserialized object is a LinkedHashMap, so this cast is safe for this text.
    @SuppressWarnings("unchecked")
    static Map<String, Object> castToMap(Object value) {
        return (Map<String, Object>) value;
    }
}
