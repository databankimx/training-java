// An error in the input. The message is the text the calculator prints after "error: ".
class CalcException extends RuntimeException {
    CalcException(String message) {
        super(message);
    }
}
