import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String json = "{\"name\": \"Ada\", \"age\": 36, \"languages\": [\"Java\", \"Python\"], "
                + "\"active\": true, \"nickname\": null}";

        Object parsed = Json.deserialize(json);
        System.out.println("Type: " + parsed.getClass().getSimpleName());

        Map<?, ?> data = (Map<?, ?>) parsed;
        System.out.println("age is a " + data.get("age").getClass().getSimpleName());

        System.out.println("Serialized again: " + Json.serialize(parsed));
        System.out.println("Round trip matches: " + Json.serialize(parsed).equals(json));

        try {
            Json.deserialize("{\"name\": }");
        } catch (IllegalArgumentException error) {
            System.out.println("Error: " + error.getMessage());
        }
    }
}
