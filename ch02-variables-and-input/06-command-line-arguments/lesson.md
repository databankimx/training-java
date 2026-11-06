## Command-line arguments

So far the program asks the user for values once it's running. Many programs take their values before they start, by reading words typed after the program's name. Those words are called command-line arguments. You've already used them: the runner's argument box sends whatever you type there to the program in exactly this way.

Here's the program for this step, in `src/Main.java`. Press **Run** with no arguments first, then type the arguments in the box, as `4 9`, and press Run again.

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Arguments received: " + args.length);
        for (int i = 0; i < args.length; i++) {
            System.out.println("Argument " + (i + 1) + ": " + args[i]);
        }

        if (args.length == 2) {
            int first = Integer.parseInt(args[0]);
            int second = Integer.parseInt(args[1]);
            System.out.println(first + " + " + second + " = " + (first + second));
        } else {
            System.out.println("Give two numbers, for example: program 4 9");
        }
    }
}
```

Run it with the arguments `4 9`. The output is:

```text
Arguments received: 2
Argument 1: 4
Argument 2: 9
4 + 9 = 13
```

With no arguments, the output is `Arguments received: 0`, followed by the line asking for two numbers.

### args

`main` takes one parameter that carries the arguments. `args` is an array of text, with one item for each word typed after the program's name. `args.length` is the number of words, and `args[0]` is the first word the user typed.

The program's own name isn't passed in, so `args[0]` is the first argument, and the loop starts at 0. The output still numbers the arguments from 1, because that's how a reader expects to count them.

### Arguments are text

Every argument arrives as a `String`, which is text, even when it looks like a number. `Integer.parseInt` converts the text to an `int`, the same kind of conversion the user-input step used with `nextInt`. The conversion is done when you ask for it, so a program that expects numbers must convert them itself.

### Arguments and the runner

The runner's argument box splits what you type at each space, and sends each word as a separate argument. That's why `4 9` gives two arguments, and why a program that needs the whole line of text has to join the words back together.

---

### Quiz

*These questions are written for this course.*

````quiz
For the command `program 4 9`, what is `args.length`?
○ 3
→ Not quite. Java doesn't pass the program's name in args, so only the two numbers are counted.
◉ 2
→ Yes. The 4 and the 9 are the two arguments.
○ 4
→ Not quite. Each word is one argument, and there are two words after the program's name.
````

````quiz
What type does an argument have when it arrives in `args`?
○ int
→ Not quite. Every argument arrives as text, even when it looks like a number.
◉ String
→ Yes. Each argument is a String, which you convert when you need a number.
○ char
→ Not quite. A char holds a single character, but each argument is a whole word.
````

---

### Try it in the runner

Run the program with the arguments `4 nine`. The second argument isn't a number, so `Integer.parseInt` can't convert it, and the program throws a `NumberFormatException`. Predict the first line of the error message before you press Run. The runner shows the error with its stack trace, and the program stops. Handling bad input like this properly is the job of exceptions, which a later chapter covers.

Then run with no arguments at all, and predict the second line of the output.

---

Command-line arguments let a program take its values before it starts, which suits programs that run unattended, or that are used from a script. The summary comes next, and it covers the whole chapter.
