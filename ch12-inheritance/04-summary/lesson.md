## Summary: Inheritance

This chapter covered sharing structure between classes, and calling the right behavior through a parent type.

* **`extends` inherits.** A subclass has the accessible fields and methods of its superclass.
* **`super` reaches the parent.** A subclass constructor calls the superclass constructor first, with `super(...)`.
* **Private members stay private.** A subclass uses the parent's getters, not its private fields.
* **Overriding replaces a method.** The subclass defines a method with the same signature.
* **`@Override` is checked.** A method that doesn't replace anything fails to compile.
* **The object's class decides the method.** A call through a parent type runs the subclass's version.
* **Polymorphism lets one loop handle many kinds.** New subclasses work without changing the loop.
* **Fields aren't overridden.** A subclass field with the same name is a second field.
* **An abstract class can't be created.** It describes a shape, and its concrete subclasses are created.
* **An abstract method has no body.** Every concrete subclass must override it.

### Check your understanding

*These questions are written for this course.*

````quiz
What must a subclass constructor do first?
○ Print its name.
→ Not quite. The first step is to set up the inherited part.
◉ Call the superclass constructor, with super(...), unless it's written as this(...).
→ Yes. The inherited part must be set up before the subclass adds its own.
○ Call the subclass's own methods.
→ Not quite. The superclass constructor comes first.
````

````quiz
An array of `Animal` holds a `Cat`, and `sound()` is overridden in `Cat`. Which version runs?
○ The Animal version, because the array's type is Animal.
→ Not quite. The method is chosen by the object's real class.
◉ The Cat version.
→ Yes. The overriding version for the object's class runs.
○ A compile error.
→ Not quite. Calling through a parent type is valid, and it runs the subclass's version.
````

````quiz
Which statement about abstract classes is correct?
○ They can be created with new, but their methods are empty.
→ Not quite. An abstract class can't be created at all.
◉ They can't be created, and their abstract methods must be overridden by concrete subclasses.
→ Yes. An abstract class describes a shape, and the concrete subclasses supply the details.
○ They can only have static methods.
→ Not quite. An abstract class can have instance methods as well as abstract ones.
````
