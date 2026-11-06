## Captures

A lambda often needs a value from the code around it. A lambda can use a local variable from the method that creates it, and that use is called a capture. Java's rule for captures is strict: the captured variable must be *effectively final*, which means it's never assigned again after it's first given a value. This keeps the rule simple, because a captured value can't change under the lambda's feet.

The program has two lambdas, and each shows one side of the rule.

```java
int factor = 3;
Function<Integer, Integer> scaleByValue = x -> x * factor;
System.out.println("Scaled by value: " + scaleByValue.apply(5));
```

The lambda uses `factor`, which is never assigned again, so the capture is allowed. It multiplies by 3. The output is `Scaled by value: 15`.

If the program later assigned `factor = 10;`, the build would fail. A variable that changes can't be captured, because the lambda would have to see either the old value or the new one, and Java doesn't allow either choice to be made silently. The error reads `local variables referenced from a lambda expression must be final or effectively final`.

```java
int[] total = {0};
IntConsumer addTo = x -> total[0] += x;
addTo.accept(4);
addTo.accept(6);
System.out.println("Total through an array: " + total[0]);
```

The lambda can't assign to a local `int`, but it can change the contents of an array. The array variable `total` is never reassigned, so it's effectively final, and the lambda changes the one element through it. After both calls the element is 10, and the output is `Total through an array: 10`.

Press **Run**. The output for the whole program is:

```text
Scaled by value: 15
Total through an array: 10
```

### Where the captured value lives

A captured value is copied into the lambda when the lambda is created. The effectively final rule is what makes that safe: since the value never changes, the copy is always right. A lambda that is stored and called later still has the value it captured, and it doesn't refer to a local variable that has gone out of scope.

### Choosing a holder for changing values

When a lambda must change something, the usual choices are an array of one element, as here, or a field of an object, which the lambda reaches through a reference to the object. Both let the lambda change the state, and the variable that holds the array or object stays effectively final. The same approach works for counters in loops and for totals in streams, which later chapters use.

---

### Quiz

*These questions are written for this course.*

````quiz
Why does the lambda `x -> x * factor` keep the value 3 after a later assignment, if one were allowed?
○ Because lambdas are compiled before the variables change.
→ Not quite. Compilation time doesn't decide the value.
◉ Because the captured value is copied into the lambda when it is created, and effectively final variables never change anyway.
→ Yes. The rule means the captured value can't change under the lambda.
○ Because Java lambdas never use local variables.
→ Not quite. They do, through captures, as long as the variable is effectively final.
````

````quiz
Why can't a lambda assign to a local `int` variable that it captures?
○ Because lambdas are read-only.
→ Not quite. A lambda can change state, but not through a captured local variable.
◉ Because a captured variable must be effectively final, so it can't be reassigned anywhere.
→ Yes. The rule keeps the captured value fixed for the lambda's whole life.
○ Because int values are too large to change.
→ Not quite. The restriction is about reassignment, not the size of the value.
````

---

### Try it in the runner

Uncomment or add a line `factor = 10;` after the lambda is created, and predict whether the program builds before you press **Run**. It doesn't. The variable is no longer effectively final, so the lambda can't capture it. Remove the line before you continue.

Then change `factor` to an array, `int[] factor = {3};`, use `factor[0]` in the lambda, and set `factor[0] = 10;` before the call. Predict the output of the scaled line. It's `50`, since the lambda reads the element when it's called, and the array variable never changed.

---

A lambda captures the values of effectively final local variables, and a changing value is kept in a holder such as an array. The next step is the chapter summary, and then the homework exercises that use lambdas with the library's sorting and searching.
