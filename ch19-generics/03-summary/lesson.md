## Summary: Generics

This chapter covered how one definition can serve many types, for methods and for classes.

* **A generic method has a type parameter.** `static <T extends Comparable<T>> T largerOf(T a, T b)` works for any type that meets its bound.
* **The bound gives the method its operations.** `extends Comparable<T>` allows `compareTo` on values of type `T`.
* **Inference works out the type.** The compiler usually finds `T` from the arguments, and an explicit type goes before the method name, as in `Main.<String>largerOf`.
* **A generic class has a type parameter for the whole class.** `Box<T>` uses `T` for its field, constructor, and methods, and each use names the type.
* **Type arguments are object types.** `Integer` stands in for `int`, and `Box<int>` is not allowed.
* **The type is removed at run time.** Java compiles one version of each generic class or method, and the compiler checks each use before the program runs.
* **Each generic class is written in its own file.** The compiler checks the class against its bounds, and each use is checked on its own.

### Check your understanding

*These questions are written for this course.*

````quiz
What does a bound such as `extends Comparable<T>` allow the generic method to do?
○ Use the `>` operator on any type.
→ Not quite. The > operator works only on primitive numbers. A bound gives access to compareTo.
◉ Call compareTo on values of type T.
→ Yes. The bound promises that every T can be compared with another T.
○ Create new objects of type T.
→ Not quite. A bound says what T can do, not how to create it.
````

````quiz
What is the type argument of `Box<Integer> number = new Box<>(42);`?
○ int
→ Not quite. A type argument must be an object type, so Integer is used.
◉ Integer
→ Yes. The wrapper stands in for the int value.
○ Box
→ Not quite. Box is the generic class, and Integer is the type it's given.
````

````quiz
Why does Java compile one version of a generic class, rather than one for each type?
○ Because the types are too similar to tell apart.
→ Not quite. The types are different, but the code is the same for each.
◉ Because the type argument is removed at run time, and the compiler checks each use before the program runs.
→ Yes. The checks happen at compile time, so one version serves every type.
○ Because each type argument needs its own source file.
→ Not quite. The type arguments don't need separate files.
````
