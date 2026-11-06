import java.util.List;

// Stages two and three: reads the tokens with a recursive descent parser, and works out the value as it goes.
//
// expr    := term (('+' | '-') term)*
// term    := factor (('*' | '/') factor)*
// factor  := '-' factor | number | '(' expr ')'
final class Parser {
    private final List<Token> tokens;
    private int pos = 0;

    private Parser(String input) {
        this.tokens = Tokenizer.tokenize(input);
    }

    static double evaluate(String input) {
        Parser parser = new Parser(input);
        double value = parser.expr();
        Token extra = parser.peek();
        if (extra.kind() != Kind.END) {
            throw new CalcException("expected end of input, found '" + extra.text() + "' at column " + extra.column());
        }
        return value;
    }

    private Token peek() {
        return tokens.get(pos);
    }

    private Token next() {
        return tokens.get(pos++);
    }

    private double expr() {
        double value = term();
        while (peek().kind() == Kind.PLUS || peek().kind() == Kind.MINUS) {
            Token op = next();
            double right = term();
            value = op.kind() == Kind.PLUS ? value + right : value - right;
        }
        return value;
    }

    private double term() {
        double value = factor();
        while (peek().kind() == Kind.STAR || peek().kind() == Kind.SLASH) {
            Token op = next();
            double right = factor();
            if (op.kind() == Kind.SLASH) {
                if (right == 0) {
                    throw new CalcException("division by zero at column " + op.column());
                }
                value /= right;
            } else {
                value *= right;
            }
        }
        return value;
    }

    private double factor() {
        Token t = peek();
        switch (t.kind()) {
            case MINUS -> {
                next();
                return -factor();
            }
            case NUMBER -> {
                next();
                return t.value();
            }
            case OPEN -> {
                next();
                double value = expr();
                Token close = peek();
                if (close.kind() != Kind.CLOSE) {
                    throw new CalcException("expected ')', found " + describe(close) + " at column " + close.column());
                }
                next();
                return value;
            }
            default -> throw new CalcException(
                    "expected a number or '(', found " + describe(t) + " at column " + t.column());
        }
    }

    private static String describe(Token t) {
        return t.kind() == Kind.END ? "end of input" : "'" + t.text() + "'";
    }
}
