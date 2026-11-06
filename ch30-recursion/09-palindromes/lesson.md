## Detecting palindromes

A palindrome reads the same forwards and backwards. "race car" and "Madam, I'm Adam." are palindromes once spaces, punctuation, and case are ignored. This step checks text for that property, and it writes the check three ways: with a reversal, recursively, and with a loop.

### Cleaning the text first

A palindrome check should ignore everything but the letters, and it shouldn't care about case. The `cleanText` method keeps the letters and lower-cases them:

```java
static String cleanText(String text) {
    StringBuilder clean = new StringBuilder();
    for (char c : text.toCharArray()) {
        if (Character.isLetter(c)) {
            clean.append(Character.toLowerCase(c));
        }
    }
    return clean.toString();
}
```

`Character.isLetter` decides which characters to keep, and `Character.toLowerCase` converts each one. `StringBuilder` builds the result without creating a new string for each character. The Python lesson uses a list comprehension for the same job.

Testing it on "Madam, I'm Adam." gives `madamimadam`, which is the text the checks will work on.

### The easy way

The simplest check reverses the text and compares it with the original:

```java
static boolean isPalindromeSimple(String text) {
    String clean = cleanText(text);
    return clean.equals(new StringBuilder(clean).reverse().toString());
}
```

`StringBuilder.reverse` returns the characters in the opposite order, and `equals` compares the two strings. This is the right choice when there's no reason to do it another way.

### The recursive way

Suppose the reversal isn't available, or the task is to write the check recursively. A palindrome has two base cases: a text of fewer than two characters is a palindrome by definition, and a text whose first and last characters differ is not. Otherwise, the first and last characters are removed, and the middle is checked the same way:

```java
static boolean isPalindromeRecursive(String text) {
    if (text.length() < 2) {
        return true;
    }
    return text.charAt(0) == text.charAt(text.length() - 1)
            && isPalindromeRecursive(text.substring(1, text.length() - 1));
}
```

The `&&` stops the recursion early. If the first and last characters differ, the call on the middle isn't made at all. Each call removes two characters, so the recursion depth grows by one for every two letters. For very long text, a `StackOverflowError` is possible, as the first step of this chapter showed.

### The iterative way

The loop version does the same comparisons with two indexes. `low` starts at the first character and `high` at the last, and they move inwards until they meet:

```java
static boolean isPalindromeIterative(String text) {
    int low = 0;
    int high = text.length() - 1;
    while (low < high) {
        if (text.charAt(low) != text.charAt(high)) {
            return false;
        }
        low++;
        high--;
    }
    return true;
}
```

The indexes replace the substring in the recursive version. Each pass moves the two ends inwards without copying the middle of the text, so the work for each pass is smaller than the recursive version's copy. This version has no depth limit.

### Running it

Press **Run**. The output is:

```text
madamimadam
Recursive:
- 'foo' is not a palindrome
- 'race car' is a palindrome
- 'boot' is not a palindrome
- 'toot' is a palindrome
- 'Madam, I'm Adam.' is a palindrome
Iterative:
- 'foo' is not a palindrome
- 'race car' is a palindrome
- 'boot' is not a palindrome
- 'toot' is a palindrome
- 'Madam, I'm Adam.' is a palindrome
```

Both methods give the same results for each word.

---

### Quiz

*These questions are written for this course.*

````quiz
Why does the recursive method return true when the text has fewer than two characters?
○ Because an empty text is never a palindrome.
→ Not quite. An empty text, and a single character, are palindromes by definition.
◉ Because a text of zero or one character reads the same both ways, so it's the base case.
→ Yes. It's the first base case, and the recursion stops there.
○ Because the method can't check short texts.
→ Not quite. The method checks them, and the result is true.
````

````quiz
Why does the iterative version run without the depth limit the recursive version has?
○ Because it checks fewer characters.
→ Not quite. It checks the same pairs of characters.
◉ Because it doesn't make a call for each pair, so the call stack doesn't grow.
→ Yes. The loop moves the two indexes inwards, using one frame.
○ Because `charAt` is faster than `substring`.
→ Not quite. Speed isn't the reason for the difference in depth.
````

---

### Try it in the runner

Add `"A man, a plan, a canal: Panama"` to the list of words, and predict whether it's a palindrome before you press **Run**. It is, once the spaces, punctuation, and case are removed. Then add `"palindrome"`, and predict the answer. It's not a palindrome, since its first and last letters differ.

---

Recursion can check a palindrome by peeling off its ends, and a loop does the same with two indexes. The next lesson is the recursive quicksort, which splits a list around a pivot.
