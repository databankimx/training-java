## Homework: Pair

A pair holds two values, which may be of different types. In this exercise, you write a generic class with two type parameters, and use it with two different combinations of types.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* write a generic class `Pair<First, Second>` with a constructor that takes one value of each type, and two methods, `getFirst` and `getSecond`, that return them
* in `main`, create a `Pair<Integer, String>` holding 3 and "three", and print `Pair: ` followed by the first value, a space, and the second value
* create a `Pair<String, Double>` holding "pi" and 3.14, and print it in the same form

### What to check

The output should be:

```text
Pair: 3 three
Pair: pi 3.14
```

The two pairs have different types, so the compiler checks each use against its own types. Each one returns the types it was given: an `Integer` and a `String` for the first pair, and a `String` and a `Double` for the second.

### Extensions

* Write a `swapped` method that returns a `Pair<Second, First>`, the same values in the opposite order. Predict the type of `swapped` for the first pair before you write it.
* Explain why `Pair<Integer, Integer>` is valid even though both type parameters are the same type.

---

*This exercise is not from the book. It practices the ideas in Chapter 19.*
