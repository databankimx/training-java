## Homework: Vowel tests

Write a method that counts the vowels in a word, and a set of checks that test it. Only the lowercase vowels a, e, i, o, and u count in this exercise.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own tests.

The program should:

* write `static int countVowels(String word)`, which counts the characters that are a, e, i, o, or u
* write a `check` method that prints `PASS` or `FAIL` with a name, and counts the failures
* run at least four checks in `main`, using these cases: "hello" has 2 vowels, "" has 0, "rhythm" has 0, and "education" has 5
* print `Failures: ` and the count
* write a `Tests` class in the `tests` folder, which checks the same cases and ends with `System.exit` set to 0 when every check passes, and 1 otherwise

### What to check

The output of `Main` should be:

```text
PASS hello has 2 vowels
PASS an empty word has no vowels
PASS rhythm has no vowels
PASS education has 5 vowels
Failures: 0
```

Then press **Test** to run the checks. The result should show the test as passed, with `Finished with exit code 0`. Test prints only the `Failures` line from `Tests`, so the `PASS` lines come from **Run**, not **Test**.

### Extensions

* Add a check that uses an uppercase vowel, such as "HELLO". Decide what the answer should be, and whether the method is right to give it, before you run the check.
* Write one more check of your own for a word you choose. Work out its vowel count by hand first, and explain why that matters.

---

*This exercise is not from the book. It practices the ideas in Chapter 21, with the strings from Chapter 2.*
