## Initializing arrays

The last step created an array and then filled it one element at a time. Java also lets you give the values when you create the array, and it sets every element that you don't give a value for.

Here's the program for this step, in `src/Main.java`:

```java
int[] primes = {2, 3, 5, 7, 11};
System.out.println("Primes: " + primes.length + " values, last is " + primes[4]);
```

Press **Run**. The output begins:

```text
Primes: 5 values, last is 11
```

The list in curly braces creates the array and fills it in one step. Java works out the size from the number of values, so you don't write the 5 yourself. The same form can be written with `new`, as `new int[]{2, 3, 5, 7, 11}`, but when you create and fill an array in one line, the shorter form is the usual choice.

### Default values

An array you create with `new` and a size is filled with default values, before you've put anything in it. The default depends on the type:

| Element type | Default value |
|---|---|
| `int`, `long` | 0 |
| `double` | 0.0 |
| `boolean` | `false` |
| `char` | the character with code 0, which prints as nothing visible |
| Any object, such as `String` | `null`, which means no object |

The program shows this. It creates `squares` with four elements, sets three of them, and reads the fourth:

```java
int[] squares = new int[4];
squares[0] = 1;
squares[1] = 4;
squares[2] = 9;
System.out.println("Fourth square (not set yet): " + squares[3]);
```

The output is:

```text
Fourth square (not set yet): 0
```

Java never leaves an element with a random value. The fourth square was never set, so it's the default for `int`, which is 0. In C++, the same element would hold whatever was in memory, and reading it would be undefined behavior. Java's default values are one of the reasons Java programs are easier to predict.

### Object arrays and null

An array of `String` values starts with every element set to `null`. That's the Java word for "no object here", and reading it gives `null` rather than an empty string:

```java
String[] names = new String[3];
names[0] = "Ada";
System.out.println("Name 1: " + names[0]);
System.out.println("Name 2: " + names[1]);
```

The output is:

```text
Name 1: Ada
Name 2: null
```

The second name was never set, so it's `null`. If you call a method on a `null` value, such as `names[1].length()`, the program stops with a `NullPointerException`. The chapter on objects covers `null` in depth, and the rule to remember for now is that a `null` element has no value to work with.

### Decimal values

The last part of the program has an array of `double` values:

```java
double[] prices = {4.99, 12.50};
System.out.println("Price: " + prices[1]);
```

The output is `Price: 12.5`. Java prints a `double` with the fewest digits that identify it, so `12.50` shows as `12.5`. The value is the same. Use `printf` with `%.2f` when you need the two places printed.

### The length, again

`squares.length` gives 4, the size you asked for, whether or not every element has been set. The length counts the slots in the array, not the values you've put in it. That's why the length is useful for knowing where the array ends, even when it's only partly filled.

---

### Quiz

*These questions are written for this course.*

````quiz
What is the default value of an `int` element in a new array that has not been set?
○ Whatever value was in memory before.
→ Not quite. Java sets every element to a known default, so the value is never left to chance.
◉ 0
→ Yes. Numeric arrays start with 0 in every element.
○ null
→ Not quite. null is the default for objects, such as String, not for an int.
````

````quiz
What does a `String` array element hold if it has not been set?
○ An empty string, "".
→ Not quite. Its default is null, which means no object, not an empty string.
◉ null
→ Yes. An unset object element is null.
○ 0
→ Not quite. A String element can't be 0.
````

---

### Try it in the runner

Add `System.out.println("Name 3: " + names[2].length());` after the last name line, and press **Run**. Predict the result before you press Run. `names[2]` is `null`, so calling `length()` on it throws a `NullPointerException`. The output stops at that line. Remove the line before you continue.

Then change `primes` to `{2, 3, 5, 7}`, and predict the value printed by `primes[4]`. The index is past the end of the new array, so the program throws an `ArrayIndexOutOfBoundsException`, as in the last step.

---

Initializing an array with values is shorter, and defaults keep every element predictable. The next step extends arrays to two dimensions, with a row and a column index.
