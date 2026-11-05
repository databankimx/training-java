## String methods and formatting

A `String` is an object, and it comes with methods for the work programs do most often: trimming user input, searching for text, cutting it apart, and building output in a neat form. The methods are called with a dot, as the classes chapter showed, and none of them changes the string. Each returns a new one, or a value about the old one.

Here's the program for this step, in `src/Main.java`. It starts with some spaces around a title, and trims them away:

```java
String title = "  Java Basics  ";
String trimmed = title.trim();
System.out.println("Trimmed: [" + trimmed + "]");
```

Press **Run**. The output begins:

```text
Trimmed: [Java Basics]
```

`trim` returns a new string without the spaces at either end. The original `title` is unchanged, so the program keeps the value it trimmed.

### Searching and cutting

```java
System.out.println("Length: " + trimmed.length());
System.out.println("Contains Basics: " + trimmed.contains("Basics"));
System.out.println("Index of Basics: " + trimmed.indexOf("Basics"));
System.out.println("Substring: " + trimmed.substring(0, 4));
```

The output is:

```text
Length: 11
Contains Basics: true
Index of Basics: 5
Substring: Java
```

Positions start at 0, so the B of "Basics" is at index 5. `substring(0, 4)` takes the characters from index 0 up to, but not including, index 4, which gives the first four letters. The end index is never included, which matches the way array ranges work.

### Replacing and splitting

```java
System.out.println("Replaced: " + trimmed.replace("Basics", "Methods"));

String[] words = trimmed.split(" ");
System.out.println("Words: " + words.length);
System.out.println("Joined: " + String.join("-", words));
```

The output is:

```text
Replaced: Java Methods
Words: 2
Joined: Java-Basics
```

`split` cuts the string at each match of its argument, and returns an array of the pieces. `String.join` does the reverse, joining an array's elements with a separator.

### Comparing and repeating

```java
System.out.println("Equal ignoring case: " + "JAVA".equalsIgnoreCase("java"));
System.out.println("Blank? " + "   ".isBlank());
System.out.println("Repeat: " + "ab".repeat(3));
```

The output is:

```text
Equal ignoring case: true
Blank? true
Repeat: ababab
```

Two strings should be compared with `equals` or `equalsIgnoreCase`, not with `==`, which compares identity, as the classes chapter showed. `isBlank` is true for an empty string or one made only of whitespace, which is useful for checking user input.

### Formatting

`String.format` builds text from a pattern, using the same codes as `printf`:

```java
System.out.println("Formatted: " + String.format("%-8s|%5.2f", "pi", 3.14159));
```

The output is:

```text
Formatted: pi      | 3.14
```

`%-8s` prints a string left-aligned in eight spaces, and `%5.2f` prints a decimal with two places, right-aligned in five. Formatting keeps columns of output lined up, which matters in reports and tables.

---

### Quiz

*These questions are written for this course.*

````quiz
What does `trim` do to a string?
○ It changes the string in place.
→ Not quite. A string doesn't change. trim returns a new string.
◉ It returns a new string with the spaces at each end removed.
→ Yes. The original string is left as it was.
○ It removes every space in the string.
→ Not quite. trim removes only the spaces at the two ends.
````

````quiz
What does `"Java Basics".indexOf("Basics")` return?
○ 4
→ Not quite. Position 4 is the space, and Basics starts after it.
◉ 5
→ Yes. Positions start at 0, so B is the sixth character.
○ 6
→ Not quite. That is one past the B, so it's the position of the a.
````

---

### Try it in the runner

Change `trimmed.substring(0, 4)` to `trimmed.substring(5)`, and predict the output before you press **Run**. The one-argument form runs to the end of the string, so the output is `Basics`. Then change `"   ".isBlank()` to `"   a".isBlank()`, and predict the result. It's `false`, since the string holds a letter.

---

String methods let a program clean, search, cut, and format text without writing the loops itself. The rest of the course uses them in the homework and in the text-processing chapters.
