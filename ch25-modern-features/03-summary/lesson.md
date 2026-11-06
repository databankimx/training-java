## Summary: Modern features

This chapter covered language features that reduce repetition and state intent more clearly.

* **`var` takes the type from the value.** The variable's type is fixed and checked on every later use.
* **A record pattern names the parts of a record.** `instanceof Point(int x, int y)` checks the type and names the components in one step.
* **A `switch` expression gives a value.** Each arm runs when its pattern matches, and the `default` arm handles the rest.
* **A pattern case can't come after `default`.** The compiler reports it as an error.
* **`Optional` shows that a value may be missing.** The method's return type says so, and the caller sees it.
* **`orElse` gives a fallback.** It returns the value when present, and the fallback otherwise.
* **`ifPresentOrElse` acts on both cases.** One lambda runs when a value is present, and another when it isn't.

### Check your understanding

*These questions are written for this course.*

````quiz
What does `var` do to the type of a variable?
○ It makes the variable's type change with each assignment.
→ Not quite. The type is fixed by the first value.
◉ It takes the type from the value on the right, and the type stays fixed.
→ Yes. Later assignments must fit that type.
○ It makes the variable hold any type.
→ Not quite. Every variable has one type, which the compiler checks.
````

````quiz
Why does a method that might find nothing return `Optional<Integer>` rather than `null`?
○ Because `null` is not allowed as a return value.
→ Not quite. Methods can return null, but the caller has to remember to check for it.
◉ So the possibility of no value is part of the return type, and the caller sees it.
→ Yes. The type tells the caller that a check is needed.
○ Because `Optional` is faster than `null`.
→ Not quite. The reason is that the missing value is visible in the type.
````

````quiz
What does `orElse(0)` give for an `Optional` that is empty?
○ An exception.
→ Not quite. orElse gives the fallback value when there is nothing inside.
◉ 0, the fallback value passed in.
→ Yes. The fallback is used only when the Optional is empty.
○ An empty Optional.
→ Not quite. orElse returns the contents, not another Optional.
````
