import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Scanner;

// A calculator with the core and all three bonus items: exponents with ^, sqrt, and square and curly brackets.
//
// Bonus 1 (exponents): done. ^ binds tighter than * and /, groups to the right, and takes a negative exponent.
// Bonus 2 (square roots): done. sqrt(...) reports a negative argument and any unknown function name.
//   Rounding: the result is printed with 15 significant digits, so a tiny floating-point error such as
//   2.0000000000000004 prints as 2. The value itself is not rounded, only its printed form.
// Bonus 3 (brackets): done. [ ] and { } group like ( ), and a closing bracket must match its opening one.
public class Main {
    record Example(String input, String expected) {
    }

    record ErrorCase(String input, String message) {
    }

    static final List<Example> VALUES = List.of(
            new Example("2 + 3 * 4", "14"),
            new Example("(2 + 3) * 4", "20"),
            new Example("10 - 4 - 3", "3"),
            new Example("8 / 4 / 2", "1"),
            new Example("-3 + 5", "2"),
            new Example("2 * -3", "-6"),
            new Example("10 / 4", "2.5"),
            new Example("1.5 * 4", "6"),
            new Example("2^3", "8"),
            new Example("2 * 3^2", "18"),
            new Example("2^3^2", "512"),
            new Example("-2^2", "-4"),
            new Example("2^-1", "0.5"),
            new Example("sqrt(16)", "4"),
            new Example("sqrt(2) * sqrt(2)", "2"),
            new Example("8^(1/3)", "2"),
            new Example("[2 + 3] * 2", "10"),
            new Example("{2 + 3} * 2", "10"));

    static final List<ErrorCase> ERRORS = List.of(
            new ErrorCase("2 + $", "unexpected character '$' at column 5"),
            new ErrorCase("1 / 0", "division by zero at column 3"),
            new ErrorCase("(2 + 3", "expected ')', found end of input at column 7"),
            new ErrorCase("2 + 3)", "expected end of input, found ')' at column 6"),
            new ErrorCase("2 +", "expected a number or '(', found end of input at column 4"),
            new ErrorCase("1.2.3", "invalid number '1.2.3' at column 1"),
            new ErrorCase("(2 + 3]", "expected ')', found ']' at column 7"),
            new ErrorCase("sqrt(-4)", "sqrt of a negative number at column 1"),
            new ErrorCase("cube(2)", "unknown function 'cube' at column 1"));

    public static void main(String[] args) {
        if (args.length == 0) {
            repl();
        } else if (args[0].equals("demo")) {
            demo();
        } else if (args[0].equals("selftest")) {
            System.exit(selftest());
        } else {
            // Any other arguments are read together as one expression, such as: 2 + 3 - (4 - 1)
            calculate(String.join(" ", args));
        }
    }

    // Prints a result, or an error with its message.
    static void calculate(String input) {
        try {
            System.out.println(format(Parser.evaluate(input)));
        } catch (CalcException error) {
            System.out.println("error: " + error.getMessage());
        }
    }

    // Read-eval-print: keeps asking for expressions until quit. Blank lines are ignored.
    static void repl() {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            if (!in.hasNextLine()) {
                break;
            }
            String line = in.nextLine().trim();
            if (line.equals("quit")) {
                break;
            }
            if (!line.isEmpty()) {
                calculate(line);
            }
        }
    }

    // Runs a fixed list of expressions, and prints each with its result or error.
    static void demo() {
        for (Example example : VALUES) {
            System.out.println(example.input() + " = " + format(Parser.evaluate(example.input())));
        }
        for (ErrorCase error : ERRORS) {
            System.out.println(error.input() + " -> error: " + error.message());
        }
    }

    // Checks each example and each error message. Prints PASS or FAIL for each one, and returns 1 if any check fails.
    static int selftest() {
        int failures = 0;
        for (Example example : VALUES) {
            String actual;
            try {
                actual = format(Parser.evaluate(example.input()));
            } catch (CalcException error) {
                actual = "error: " + error.getMessage();
            }
            if (actual.equals(example.expected())) {
                System.out.println("PASS " + example.input() + " = " + example.expected());
            } else {
                System.out.println("FAIL " + example.input() + ": expected " + example.expected() + ", got " + actual);
                failures++;
            }
        }
        for (ErrorCase error : ERRORS) {
            String actual;
            try {
                Parser.evaluate(error.input());
                actual = "no error";
            } catch (CalcException problem) {
                actual = problem.getMessage();
            }
            if (actual.equals(error.message())) {
                System.out.println("PASS " + error.input() + " -> " + error.message());
            } else {
                System.out.println("FAIL " + error.input() + ": expected '" + error.message() + "', got '" + actual + "'");
                failures++;
            }
        }
        System.out.println(failures == 0 ? "All checks passed." : failures + " check(s) failed.");
        return failures == 0 ? 0 : 1;
    }

    // Whole numbers print without a decimal point. Other numbers print with up to 15 significant digits, which hides
    // the tiny floating-point errors from the last place.
    static String format(double value) {
        if (value == Math.rint(value) && Math.abs(value) < 1e15) {
            return String.valueOf((long) value);
        }
        return new BigDecimal(value).round(new MathContext(15)).stripTrailingZeros().toPlainString();
    }
}
