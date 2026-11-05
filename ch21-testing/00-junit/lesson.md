## JUnit

The checks in the previous steps are written by hand, and each one prints its own result. Java teams usually use JUnit, a testing framework. A test is a method marked with `@Test`, and a set of assertions checks each result. JUnit finds the tests, runs each one, and reports how many passed. Its output is the same however many tests there are, which makes it easy for a team to read.

Here's the method under test, in `src/Main.java`:

```java
static int clamp(int value, int low, int high) {
    if (value < low) {
        return low;
    }
    if (value > high) {
        return high;
    }
    return value;
}
```

The tests are in `tests/ClampTest.java`, and each is a method with the `@Test` annotation:

```java
@Test
void belowTheRangeBecomesTheLowEnd() {
    assertEquals(0, Main.clamp(-3, 0, 10));
}
```

`assertEquals` takes the expected value and the actual value. If they differ, the test fails, and JUnit reports the difference. The test name says what it checks, so a failure points straight to the case that broke.

### Running the tests

Press **Test**. The runner compiles the step with JUnit, runs every `@Test` method through the JUnit console launcher, and prints a tree of the results. Each test appears with its name and a status, and the summary at the end gives the counts. A passing run finishes with `Finished with exit code 0`. A failing test changes its line in the tree, and the exit code becomes non-zero.

Press **Run** to see the main program's output, which is separate from the tests:

```text
clamp(15, 0, 10) = 10
clamp(-3, 0, 10) = 0
```

### Installing JUnit

JUnit is a third-party library, so the course doesn't include it in the repository. The runner needs the console launcher, the file `junit-platform-console-standalone.jar`, placed in `resources/vendor/junit` in the course folder. The runner reports a clear message if the file is missing. The jar's licence must be checked before it's added, as with any vendored library.

### Why a framework

A framework gives every test the same structure, so a reader knows where the expected value and the actual value are. It also finds the tests itself, so a new test is only a new method. The hand-written checks in the earlier steps do the same job by hand, and a framework does it for every test in the project.

---

### Quiz

*These questions are written for this course.*

````quiz
What marks a method as a test in JUnit?
○ Its name starts with test.
→ Not quite. The name doesn't matter to JUnit. The annotation does.
◉ The @Test annotation.
→ Yes. JUnit finds and runs every method marked with @Test.
○ Being placed in a class called Tests.
→ Not quite. JUnit finds tests by their annotation, not by the class name.
````

````quiz
In `assertEquals(0, Main.clamp(-3, 0, 10))`, what is the first argument?
○ The actual value the method returned.
→ Not quite. The first argument is the expected value.
◉ The expected value, which the test compares with the actual value.
→ Yes. assertEquals fails when the two differ.
○ The number of values in the range.
→ Not quite. The arguments of assertEquals are the expected and actual values.
````

---

### Try it in the runner

Change the expected value in `belowTheRangeBecomesTheLowEnd` from 0 to 1, and predict the result of **Test** before you press it. The test fails, the tree marks it, and the exit code is non-zero. Restore the 0 before you continue.

---

JUnit runs every marked test and reports the results together. The earlier steps in this chapter show the same kind of checks written by hand.
