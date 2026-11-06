## Running tests

Checks in `main` are useful, but a person has to read the output to see whether they passed. The build can do better. A test that ends with an exit code can be run by the runner, by a build script, or by an automated system, and each of them reads the same result: zero means the test passed, and anything else means it failed. The runner's **Test** button runs the step's tests for you.

In Java, a test is a class in the step's `tests` folder. The runner compiles it with the step's source and runs its `main` method. The program in this step ends with its exit code, which is the result.

### The function under test

The step checks a `clamp` method, which limits a value to a range:

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

A value inside the range is returned unchanged. A value below the range becomes the low end, and a value above it becomes the high end. The five checks cover each of those cases, plus the two ends of the range, which are the edges where a mistake would show.

Press **Run**. The output is:

```text
PASS inside the range stays the same
PASS below the range becomes the low end
PASS above the range becomes the high end
PASS the low end stays the same
PASS the high end stays the same
Failures: 0
```

### The test class

The test lives in `tests/Tests.java`, and it calls `clamp` through the class name, `Main.clamp`:

```java
public class Tests {
    static int failures = 0;

    static void check(boolean passed) {
        if (!passed) {
            failures++;
        }
    }

    public static void main(String[] args) {
        check(Main.clamp(5, 0, 10) == 5);
        check(Main.clamp(-3, 0, 10) == 0);
        check(Main.clamp(15, 0, 10) == 10);
        check(Main.clamp(0, 0, 10) == 0);
        check(Main.clamp(10, 0, 10) == 10);

        System.out.println("Failures: " + failures);
        System.exit(failures == 0 ? 0 : 1);
    }
}
```

`System.exit` ends the program with the value it's given. The conditional operator from the decisions chapter gives 0 when no check failed, and 1 when any did. That single number is the test result.

Press **Test**. The runner compiles the step with its tests, runs the `Tests` class, and reports the exit code. A passing test shows the `Failures` line from `Tests`, followed by `Finished with exit code 0`. `Tests` prints only that summary line, not one line per check. The `PASS` lines above come from `Main`, which the Run button runs and the Test button doesn't. So the Test output confirms the result, and the Run output shows each check.

### Why exit codes matter

A program's exit code is how the rest of the system learns whether it succeeded. Build scripts, continuous integration, and the runner's Test button all read it. A test that only prints PASS and FAIL is readable by people, but a test that returns a failing exit code is readable by machines too, and that's what makes automated checking possible.

---

### Quiz

*These questions are written for this course.*

````quiz
What does the runner use to decide whether a test passed?
○ The words the program prints.
→ Not quite. The runner doesn't read the output text to decide.
◉ The exit code, where zero means success.
→ Yes. Any other value means the test failed.
○ The number of lines the program prints.
→ Not quite. The count of lines has no meaning for the test result.
````

````quiz
What does `System.exit(failures == 0 ? 0 : 1);` do?
○ It prints the number of failures.
→ Not quite. The printing is done by the line before it.
◉ It ends the program with 0 when every check passed, and 1 when one failed.
→ Yes. That exit code is the test result the runner reads.
○ It starts the tests again.
→ Not quite. The call ends the program, and the tests run only once.
````

---

### Try it in the runner

Change the expected value in the second check of `Tests.java`, from 0 to 10, so the check fails, and predict the exit code before you press **Test**. The result is 1, since one check failed, and the runner reports the test as failed with `Finished with exit code 1`.

---

A test that ends with an exit code can be run by people and by machines. The next step is the chapter summary, and then two homework exercises that write tests of their own.
