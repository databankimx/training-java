## Summary: Classes

This chapter covered writing classes, creating objects from them, and protecting their data.

* **A class is a blueprint.** It declares the fields and methods each object has.
* **An object is one instance.** Each object has its own copy of the fields.
* **Instance methods work on one object.** They're called on an object, and they change that object's fields.
* **Static methods belong to the class.** They're called on the class name, with no object.
* **A constructor sets up each new object.** It has the class name, no return type, and runs with `new`.
* **`this` refers to the object being created.** It tells a field apart from a parameter with the same name.
* **One constructor can call another with `this(...)`.** The call must be the first line.
* **A final field is set once, in a constructor.** A constructor that leaves one unset doesn't compile.
* **Encapsulation keeps fields private.** Only the class's methods can change them, and they can check each change.
* **Getters read private values.** A getter without a setter gives read-only access.

### Check your understanding

*These questions are written for this course.*

````quiz
Which kind of method needs an object to be called?
○ A static method.
→ Not quite. A static method belongs to the class, so it's called on the class name.
◉ An instance method.
→ Yes. An instance method works on one object's fields, so it's called on that object.
○ A constructor.
→ Not quite. A constructor is run by new, and it doesn't need an existing object.
````

````quiz
Why does a class keep its fields private?
○ Because private fields are faster to read.
→ Not quite. The reason is control over changes.
◉ So that every change goes through a method the class controls, and can check.
→ Yes. Private fields can only be changed by the class's own methods.
○ Because other classes can't use the class at all.
→ Not quite. Other classes can use the class through its public methods.
````

````quiz
What happens if a constructor doesn't set a final field?
○ The field is set to its default at run time.
→ Not quite. A final field must be set in the constructor, and the compiler checks it.
◉ The build fails, because the final field might not have been initialized.
→ Yes. The compiler refuses a constructor that leaves a final field unset.
○ The program runs, and the field stays null.
→ Not quite. The build fails before the program runs.
````
