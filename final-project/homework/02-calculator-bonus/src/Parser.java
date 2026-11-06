import java.util.List;

// Stages two and three: reads the tokens with a recursive descent parser, and works out the value as it goes.
//
// expr    := term (('+' | '-') term)*
// term    := unary (('*' | '/') unary)*
// unary   := '-' unary | power
// power   := primary ('^' unary)?
// primary := number | open expr close | 'sqrt' open expr close
//
// Each rule calls the rules below it, so precedence follows from the structure. Power is right-associative because its
// exponent is a unary, which can itself contain another power.
final class Parser {
    private final List<Token> tokens;
    private int pos = 0;

    private Parser(String input) {
        this.tokens = Tokenizer.tokenize(input);
    }

    // Evaluates a whole expression. Anything left after the expression is an error.
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
        double value = unary();
        while (peek().kind() == Kind.STAR || peek().kind() == Kind.SLASH) {
            Token op = next();
            double right = unary();
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

    // A minus sign in front of a value. It applies after any power, so -2^2 is -(2^2).
    private double unary() {
        if (peek().kind() == Kind.MINUS) {
            next();
            return -unary();
        }
        return power();
    }

    // A base raised to a power. The exponent is a unary, so 2^-1 works, and 2^3^2 groups to the right.
    private double power() {
        double base = primary();
        if (peek().kind() == Kind.CARET) {
            next();
            double exponent = unary();
            return Math.pow(base, exponent);
        }
        return base;
    }

    private double primary() {
        Token t = peek();
        switch (t.kind()) {
            case NUMBER -> {
                next();
                return t.value();
            }
            case OPEN -> {
                next();
                double value = expr();
                expectClosing(t);
                return value;
            }
            case IDENT -> {
                next();
                return function(t);
            }
            default -> throw new CalcException(
                    "expected a number or '(', found " + describe(t) + " at column " + t.column());
        }
    }

    // A named function. Only sqrt is known. Its argument is in brackets, like any other group.
    private double function(Token name) {
        if (!name.text().equals("sqrt")) {
            throw new CalcException("unknown function '" + name.text() + "' at column " + name.column());
        }
        if (peek().kind() != Kind.OPEN) {
            throw new CalcException("expected '(' after 'sqrt' at column " + peek().column());
        }
        Token open = next();
        double argument = expr();
        expectClosing(open);
        if (argument < 0) {
            throw new CalcException("sqrt of a negative number at column " + name.column());
        }
        return Math.sqrt(argument);
    }

    // The closing bracket must match the opening one: ( with ), [ with ], and { with }.
    private void expectClosing(Token open) {
        String wanted = switch (open.text()) {
            case "[" -> "]";
            case "{" -> "}";
            default -> ")";
        };
        Token close = peek();
        if (close.kind() != Kind.CLOSE || !close.text().equals(wanted)) {
            throw new CalcException("expected '" + wanted + "', found " + describe(close) + " at column " + close.column());
        }
        next();
    }

    // How a token reads in an error message.
    private static String describe(Token t) {
        return t.kind() == Kind.END ? "end of input" : "'" + t.text() + "'";
    }
}
