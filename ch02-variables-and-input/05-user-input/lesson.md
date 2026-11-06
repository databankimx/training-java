## User input

So far, every value in our programs was decided before the program ran. That's fine for learning, but most real programs need to ask something of the person using them. Java handles that with a `Scanner`, an object that reads from the input. `System.in` is the standard input, which is what the runner's input box feeds.

Here's the program for this step. Press **Run**, and type into the input box at the bottom of the Output pane when it asks you to. Press Enter after each answer.

```text
Enter some text: I like Java
You entered: I like Java
Enter a number: 4
You entered: 4
Enter another number: 9
You entered: 9
4 + 9 = 13
```

Your answers will be different, of course. The shape of the output is what matters.

The program starts by creating the scanner:

```java
import java.util.Scanner;

Scanner input = new Scanner(System.in);
```

The `import` at the top brings in `Scanner`, which is in `java.util`. Without it, the compiler can't find the name.

### Reading a whole line

For the first question, the program uses `nextLine`:

```java
String text = input.nextLine();
```

`nextLine` reads everything the user types up to the Enter key, including spaces. That's what you want for a name, a sentence, or anything else where the spaces matter.

### Reading a number

For the numbers, the program uses `nextInt`:

```java
int first = input.nextInt();
```

`nextInt` reads the next whole number from the input, and returns it as an `int`. It skips any spaces before the number, and it stops at the first character that isn't part of it.

That's useful, but it has a catch. `nextInt` doesn't read the Enter key, so the newline stays in the input. If you call `nextLine` straight after `nextInt`, it reads that leftover newline and returns an empty line. The program avoids the problem by reading the text first, and then using `nextInt` for the numbers. Two `nextInt` calls in a row are fine, because `nextInt` skips the spaces and newlines in front of the next number.

### Why the prompts use flush

Look at the first prompt:

```java
System.out.print("Enter some text: ");
System.out.flush();
```

The prompt has no newline at the end, so the output could wait in a buffer before it appears. `System.out.flush()` pushes the text out at once, so the user sees the question before they're asked to answer it. Without it, a program can sit silently with no prompt on the screen, which is confusing.

---

### Quiz

*These questions are written for this course.*

````quiz
Which method reads a whole line of text from the user, including spaces?
○ input.nextInt()
→ Not quite. nextInt reads a whole number, and stops at the first space.
○ System.out.println(text)
→ Not quite. println writes to the output. It doesn't read anything.
◉ input.nextLine()
→ Yes. nextLine reads up to the Enter key, spaces included.
````

---

### Try it in the runner

Move the question for the text to after the two numbers, so the text prompt comes last. Press **Run**, and answer the numbers first. The text prompt then reads an empty line, because the leftover newline from the last number is still waiting. The `nextLine` call returns an empty string, and the program prints `You entered:` with nothing after it. Put the text question back at the start before you continue.

Then press **Run** again, and type `hello` at the second prompt, where a number is expected. The program can't turn `hello` into a number, so `nextInt` throws an `InputMismatchException`. The runner shows the error with its stack trace, and the program stops. A real program should check the input first, and a later chapter shows how.

---

Being able to ask the user for something is the first step toward programs that respond to their input. The summary and the homework come next, and they'll put the whole chapter to work.
