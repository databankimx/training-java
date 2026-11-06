import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// A small JSON library. serialize turns Java values into JSON text, and deserialize reads JSON text back into Java
// values. Objects become LinkedHashMap, arrays become ArrayList, whole numbers become Long, other numbers become
// Double, and true, false, and null become Boolean and null.
public final class Json {
    private Json() {
    }

    // Writes a Java value as JSON text. Unsupported types are written with their toString form.
    public static String serialize(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String text) {
            return quote(text);
        }
        if (value instanceof Map<?, ?> object) {
            StringBuilder json = new StringBuilder("{");
            boolean first = true;
            for (Map.Entry<?, ?> entry : object.entrySet()) {
                if (!first) {
                    json.append(", ");
                }
                json.append(quote(entry.getKey().toString())).append(": ").append(serialize(entry.getValue()));
                first = false;
            }
            return json.append("}").toString();
        }
        if (value instanceof List<?> array) {
            StringBuilder json = new StringBuilder("[");
            boolean first = true;
            for (Object item : array) {
                if (!first) {
                    json.append(", ");
                }
                json.append(serialize(item));
                first = false;
            }
            return json.append("]").toString();
        }
        return value.toString();
    }

    // Reads one JSON value from the text. The whole text must be a single value, with only spaces around it.
    public static Object deserialize(String text) {
        Parser parser = new Parser(text);
        Object value = parser.parseValue();
        parser.skipWhitespace();
        if (!parser.atEnd()) {
            throw parser.error("Unexpected text after the value");
        }
        return value;
    }

    // Wraps a string in quotes, escaping the characters that JSON requires.
    private static String quote(String text) {
        StringBuilder json = new StringBuilder("\"");
        for (char c : text.toCharArray()) {
            switch (c) {
                case '"' -> json.append("\\\"");
                case '\\' -> json.append("\\\\");
                case '\n' -> json.append("\\n");
                case '\r' -> json.append("\\r");
                case '\t' -> json.append("\\t");
                default -> json.append(c);
            }
        }
        return json.append('"').toString();
    }

    // A recursive descent parser. Each kind of value has a method, and objects and arrays call parseValue on their
    // contents, so nested data is read by the same methods at every level.
    private static final class Parser {
        private final String text;
        private int pos = 0;

        Parser(String text) {
            this.text = text;
        }

        boolean atEnd() {
            return pos >= text.length();
        }

        void skipWhitespace() {
            while (!atEnd() && Character.isWhitespace(text.charAt(pos))) {
                pos++;
            }
        }

        IllegalArgumentException error(String message) {
            return new IllegalArgumentException(message + " at position " + pos);
        }

        void expect(char c) {
            if (atEnd() || text.charAt(pos) != c) {
                throw error("Expected '" + c + "'");
            }
            pos++;
        }

        Object parseValue() {
            skipWhitespace();
            if (atEnd()) {
                throw error("Unexpected end of text");
            }
            char c = text.charAt(pos);
            if (c == '{') {
                return parseObject();
            }
            if (c == '[') {
                return parseArray();
            }
            if (c == '"') {
                return parseString();
            }
            if (c == '-' || Character.isDigit(c)) {
                return parseNumber();
            }
            if (text.startsWith("true", pos)) {
                pos += 4;
                return Boolean.TRUE;
            }
            if (text.startsWith("false", pos)) {
                pos += 5;
                return Boolean.FALSE;
            }
            if (text.startsWith("null", pos)) {
                pos += 4;
                return null;
            }
            throw error("Unexpected character '" + c + "'");
        }

        Map<String, Object> parseObject() {
            pos++;
            Map<String, Object> object = new LinkedHashMap<>();
            skipWhitespace();
            if (!atEnd() && text.charAt(pos) == '}') {
                pos++;
                return object;
            }
            while (true) {
                skipWhitespace();
                if (atEnd() || text.charAt(pos) != '"') {
                    throw error("Expected a key");
                }
                String key = parseString();
                skipWhitespace();
                expect(':');
                object.put(key, parseValue());
                skipWhitespace();
                if (!atEnd() && text.charAt(pos) == ',') {
                    pos++;
                    continue;
                }
                expect('}');
                return object;
            }
        }

        List<Object> parseArray() {
            pos++;
            List<Object> array = new ArrayList<>();
            skipWhitespace();
            if (!atEnd() && text.charAt(pos) == ']') {
                pos++;
                return array;
            }
            while (true) {
                array.add(parseValue());
                skipWhitespace();
                if (!atEnd() && text.charAt(pos) == ',') {
                    pos++;
                    continue;
                }
                expect(']');
                return array;
            }
        }

        String parseString() {
            expect('"');
            StringBuilder result = new StringBuilder();
            while (true) {
                if (atEnd()) {
                    throw error("Unterminated string");
                }
                char c = text.charAt(pos++);
                if (c == '"') {
                    return result.toString();
                }
                if (c != '\\') {
                    result.append(c);
                    continue;
                }
                if (atEnd()) {
                    throw error("Unfinished escape");
                }
                char escape = text.charAt(pos++);
                switch (escape) {
                    case '"' -> result.append('"');
                    case '\\' -> result.append('\\');
                    case '/' -> result.append('/');
                    case 'n' -> result.append('\n');
                    case 'r' -> result.append('\r');
                    case 't' -> result.append('\t');
                    case 'b' -> result.append('\b');
                    case 'f' -> result.append('\f');
                    case 'u' -> {
                        if (pos + 4 > text.length()) {
                            throw error("Incomplete \\u escape");
                        }
                        result.append((char) Integer.parseInt(text.substring(pos, pos + 4), 16));
                        pos += 4;
                    }
                    default -> throw error("Unknown escape \\" + escape);
                }
            }
        }

        Number parseNumber() {
            int start = pos;
            if (text.charAt(pos) == '-') {
                pos++;
            }
            while (!atEnd() && (Character.isDigit(text.charAt(pos)) || ".eE+-".indexOf(text.charAt(pos)) >= 0)) {
                pos++;
            }
            String number = text.substring(start, pos);
            try {
                if (number.contains(".") || number.contains("e") || number.contains("E")) {
                    return Double.parseDouble(number);
                }
                return Long.parseLong(number);
            } catch (NumberFormatException problem) {
                throw error("Bad number " + number);
            }
        }
    }
}
