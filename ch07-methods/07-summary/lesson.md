## Summary: Methods

This chapter covered splitting a program into named methods.

* **A method names one job.** It has a return type, a name in camel case, and parameters in parentheses.
* **`void` means no return value.** A method with any other return type must return a value on every path.
* **Java doesn't need forward declarations.** A method can be called before it appears in the file.
* **Parameters are copies.** A method changing a parameter of a primitive type doesn't change the caller's variable.
* **Return values carry results back.** The caller decides what to do with them.
* **Overloading shares a name.** Methods with the same name and different parameter types are separate methods, chosen at compile time.
* **Overloading replaces default values.** Write one method per set of options.
* **An array parameter is shared.** A method can change its elements, and the caller sees the change.
* **Arrays know their length.** `values.length` needs no separate parameter.
* **A parameter can't be rebound for the caller.** Reassigning a parameter changes only the method's copy of the reference.
* **Recursion needs a base case.** Without one, the calls never stop, and the program throws a `StackOverflowError`.

### Check your understanding

*These questions are written for this course.*

````quiz
What happens to the caller's `int` variable when a method changes its parameter?
○ It changes too, because the parameter is the same variable.
→ Not quite. The parameter is a copy of the value.
◉ It stays the same, because the parameter is a copy.
→ Yes. Only the method's copy changes.
○ It becomes zero.
→ Not quite. Nothing resets the caller's variable.
````

````quiz
Which pair of methods can be declared in the same class?
○ void print(int a) and void print(int b)
→ Not quite. The parameter names don't matter, and these have the same parameter types.
◉ void print(int a) and void print(String a)
→ Yes. The parameter types differ, so the compiler can tell them apart.
○ int add(int a) and void add(int a)
→ Not quite. Changing only the return type isn't enough.
````

````quiz
Which statement about a recursive method is correct?
○ It doesn't need a base case if it's written carefully.
→ Not quite. Without a base case, the calls never stop.
◉ It needs a base case, where the answer is known without another call.
→ Yes. The base case stops the recursion.
○ It can only be written with a for loop.
→ Not quite. Recursion is a method calling itself, with no loop needed.
````
