## Summary: Methods

This chapter covered how a program is divided into named pieces, and how those pieces pass values in and out.

* **A method is a named block of code.** Its definition gives the return type, the name, the parameters, and the body.
* **No declaration is needed in Java.** The compiler reads the whole class before it checks the calls, so a method can be called from anywhere in the class.
* **Parameters receive arguments.** Arguments must match the parameters in number and type. Java has no default values, so an optional argument is written as an overload that calls the full method.
* **`return` sends a value back, and ends the method.** A `void` method returns nothing.
* **Overloads share a name.** They must differ in their parameters, not just their return type. The compiler chooses between them by the arguments.
* **An array parameter receives a reference to the array.** The array carries its own length, and changes to its elements reach the caller.
* **A parameter of a primitive type is a copy.** To change the caller's data, pass an object such as an array.
* **Recursion handles a problem by calling itself on smaller parts.** It needs a point where the calls stop, and a finite problem, such as a folder tree, guarantees that.

### Check your understanding

*These questions are written for this course.*

````quiz
What must be true before `main` can call a method defined below it?
○ The method must return a value.
→ Not quite. The return type has nothing to do with where a method is defined.
◉ Nothing. Java reads the whole class before it checks the calls.
→ Yes. A method can be called from anywhere in the class, whatever its position.
○ The method must have no parameters.
→ Not quite. Methods can have parameters, as long as the call matches them.
````

````quiz
Java has no default parameter values. Which is the usual way to let a caller leave out a value?
○ Declare the parameter with a default, such as int count = 1.
→ Not quite. Java doesn't accept a default in a parameter declaration.
◉ Write an overload with fewer parameters that calls the full method.
→ Yes. The overload supplies the value and calls the method that does the work.
○ Pass null for the missing int.
→ Not quite. null works only for object types, not for an int.
````

````quiz
A recursive method calls itself on a folder's subfolders. What stops the recursion?
○ A counter with a fixed limit.
→ Not quite. The tree may be deeper or shallower than any fixed limit.
◉ The tree is finite, so each branch reaches files, which don't call the method again.
→ Yes. The recursion ends where the tree does.
○ The method returns a value at the first subfolder.
→ Not quite. The listing method doesn't return a value, and it visits every subfolder.
````
