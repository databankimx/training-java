// The kinds of token the tokenizer produces.
enum Kind {
    NUMBER, PLUS, MINUS, STAR, SLASH, CARET, OPEN, CLOSE, IDENT, END
}

// One piece of the input. column is where the token starts, counted from 1.
record Token(Kind kind, String text, double value, int column) {
}
