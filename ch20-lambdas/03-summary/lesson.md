## Summary: Lambdas

This chapter covered unnamed functions written where they're used, and how they use the variables around them.

* **A lambda is an unnamed function.** `x -> x * x` has parameters, an arrow, and a body, and it's used through an interface such as `Function`.
* **The interface gives the lambda its shape.** `Function` takes one value and returns one, and other interfaces in `java.util.function` describe other shapes.
* **A comparison lambda gives a sort its rule.** It returns a negative number, zero, or a positive number, as `Integer.compare` does.
* **Sorting with a lambda keeps equal values in order.** `List.sort` is stable, so values that compare as equal keep their original order.
* **A lambda captures effectively final variables.** A captured variable must never be assigned again, which keeps its value fixed for the lambda.
* **A changing total is held in an array or an object.** The lambda changes the contents, and the variable that holds them stays effectively final.

### Check your understanding

*These questions are written for this course.*

````quiz
What does a comparison lambda for `List.sort` need to return?
○ true when a should come before b.
→ Not quite. Java comparisons return an int, not a boolean.
◉ A negative number, zero, or a positive number, depending on the order of a and b.
→ Yes. The sort uses the sign of the result to decide the order.
○ The larger of the two values.
→ Not quite. The comparison reports the order, not the value.
````

````quiz
Why can't a lambda add to a local int variable that it captured?
○ Because lambdas can't do arithmetic.
→ Not quite. A lambda can do arithmetic on its own values.
◉ Because a captured local variable must be effectively final, so it can't be assigned.
→ Yes. An array element can change, because the array variable itself is never reassigned.
○ Because int is not an object type.
→ Not quite. The rule is about assignment to captured variables, whatever the type.
````

````quiz
Which interface does a lambda that takes one value and returns one value use?
○ Runnable
→ Not quite. Runnable takes no value and returns nothing.
◉ Function
→ Yes. Function is the interface for one value in and one value out.
○ Comparator
→ Not quite. A Comparator takes two values and returns an int.
````
