import java.util.ArrayList;
import java.util.List;

// Stage one: turns the text into tokens. Each token records the column where it starts.
final class Tokenizer {
    private Tokenizer() {
    }

    static List<Token> tokenize(String input) {
        List<Token> tokens = new ArrayList<>();
        int i = 0;
        while (i < input.length()) {
            char c = input.charAt(i);
            int column = i + 1;

            if (Character.isWhitespace(c)) {
                i++;
            } else if (Character.isDigit(c) || c == '.') {
                int start = i;
                while (i < input.length() && (Character.isDigit(input.charAt(i)) || input.charAt(i) == '.')) {
                    i++;
                }
                String text = input.substring(start, i);
                if (text.equals(".") || text.chars().filter(ch -> ch == '.').count() > 1) {
                    throw new CalcException("invalid number '" + text + "' at column " + column);
                }
                tokens.add(new Token(Kind.NUMBER, text, Double.parseDouble(text), column));
            } else {
                Kind kind = switch (c) {
                    case '+' -> Kind.PLUS;
                    case '-' -> Kind.MINUS;
                    case '*' -> Kind.STAR;
                    case '/' -> Kind.SLASH;
                    case '(' -> Kind.OPEN;
                    case ')' -> Kind.CLOSE;
                    default -> throw new CalcException("unexpected character '" + c + "' at column " + column);
                };
                tokens.add(new Token(kind, String.valueOf(c), 0, column));
                i++;
            }
        }
        tokens.add(new Token(Kind.END, "", 0, input.length() + 1));
        return tokens;
    }
}
