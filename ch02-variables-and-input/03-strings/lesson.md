## Strings

Numbers aren't the only thing programs work with. Names, messages, file paths, and the text a user types are all strings. In Java, a string is a `String`, and it's built into the language, so no import is needed. You've already used one: the name in the input step was a `String`.

This is an introduction, so we'll keep it to the basics you'll need in the next step and the homework. We come back to strings in much more depth later in the course, where we look at searching, splitting, and comparing text.

Here's the program for this step, in `src/Main.java`:

```java
String first = "Hello";
String second = "Java";
String greeting = first + ", " + second + "!";
System.out.println(greeting);
```

Press **Run**. The output is:

```text
Hello, Java!
```

Three things happen here. Each string is created with its text in double quotes. The `+` operator joins strings together, so `greeting` is built from the pieces. And `greeting` is printed like any other value.

### Quotes matter

Double quotes make a string, as in `"Hello"`. Single quotes make a single character, as in `'H'`. The two are different types, so `"H"` and `'H'` aren't interchangeable. The compiler will reject one where the other is expected.

### Length and positions

The next lines of the program ask about the string itself:

```java
System.out.println("Length: " + greeting.length());
System.out.println("First letter: " + greeting.charAt(0));
```

The output is:

```text
Length: 12
First letter: H
```

`greeting.length()` returns the number of characters, including spaces and punctuation. Notice that the method name is followed by empty parentheses. That's a call to a method that belongs to the string, and you'll see that pattern a lot.

`greeting.charAt(0)` gives the character at position 0. Positions in Java start at zero, not one, so the first character is at 0, the second at 1, and so on. This is the most common source of off-by-one mistakes, and you'll meet it again.

### Adding to a string

`+=` adds text to the end of a string, the same way `+=` adds to a number:

```java
greeting += " Welcome.";
```

Now `greeting` holds `Hello, Java! Welcome.`. Like the number operators, `+=` changes the variable itself. A `String` can't be changed in place, so Java creates a new string with the added text and stores it in `greeting`. You don't need to think about that, but it's why repeated `+=` in a long loop can be slow, as the efficiency chapter shows.

### Special characters

A string can't contain a double quote directly, because the quote would end the string. Instead you write a backslash in front of it, which is an escape sequence:

```java
System.out.println("Tab:\tBackslash:\\ Quote:\"");
```

`\t` is a tab, `\\` is a single backslash, and `\"` is a double quote. The output shows the tab as a gap, and the other two as the characters themselves:

```text
Tab:	Backslash:\ Quote:"
```

---

### Quiz

*These questions are written for this course.*

````quiz
What is the position of the first character in a `String`?
○ 1
→ Not quite. Java starts counting positions at zero.
◉ 0
→ Yes. The first character is at position 0, and the second is at position 1.
○ -1
→ Not quite. Positions run from zero upwards.
````

````quiz
Which expression gives the number of characters in `name`?
○ name.length
→ Not quite. length is a method, so it needs its parentheses.
◉ name.length()
→ Yes. length() returns the number of characters, spaces and punctuation included.
○ name[length]
→ Not quite. That's not a valid way to ask for the length.
````

---

### Try it in the runner

Change `greeting.charAt(0)` to `greeting.charAt(4)`, and predict the letter before you press **Run**. Position 4 is the fifth character, which is `o` in `Hello`. Then try a position past the end, such as `greeting.charAt(50)`. The program stops with a `StringIndexOutOfBoundsException`, and the runner shows it with its stack trace. That's a sign you've read outside the string. Change the position back before you continue.

---

That's enough about strings for now. The next step covers the methods that work on strings: trimming, searching, splitting, and formatting.
