// The kinds of token the tokenizer produces.
enum Kind {
    NUMBER, PLUS, MINUS, STAR, SLASH, OPEN, CLOSE, END
}

// One piece of the input. column is where the token starts, counted from 1.
record Token(Kind kind, String text, double value, int column) {
}
