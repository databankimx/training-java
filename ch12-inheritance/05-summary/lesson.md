## Summary: Inheritance and polymorphism

This chapter covered how one class can build on another, and how a single call can do different work for different objects.

* **A derived class inherits from a base.** `class Car extends Vehicle` gives `Car` the visible members of `Vehicle`, and `Car` can add its own.
* **Inheritance chains.** A class can extend a class that extends another, and each one adds to the one above.
* **Access levels matter in derived classes.** `protected` members are visible to derived classes and to their package, but not to other code. `private` members are visible only inside their own class.
* **A class extends one class and can implement several interfaces.** `class Engineer extends Programmer implements Badge` gets the members of `Programmer` and the default methods of `Badge`.
* **Instance methods are virtual.** A call through a base type uses the version in the object's real class, decided at run time. `final` stops a method from being replaced.
* **`@Override` checks the replacement.** A method that doesn't match a method in its base is a compile error.
* **A parameter refers to the object.** A subclass object passed as its base type keeps its own methods.

### Check your understanding

*These questions are written for this course.*

````quiz
Which member of a base class can a derived class use, but code outside the class hierarchy and its package cannot?
○ public
→ Not quite. Public members are visible to everyone.
◉ protected
→ Yes. Protected members are visible to derived classes and their package.
○ private
→ Not quite. Private members are visible only inside their own class, not even to derived classes.
````

````quiz
Why does a call through a base-type variable run the subclass's method?
○ Because the method is private to the subclass.
→ Not quite. Access has nothing to do with which version runs.
◉ Because instance methods are virtual, so the lookup uses the object's real class at run time.
→ Yes. The variable's declared type decides which methods can be called, not which version runs.
○ Because the method is static.
→ Not quite. Static methods are not chosen through an object in this way.
````

````quiz
Which keyword stops a method from being replaced in a subclass?
○ @Override
→ Not quite. @Override checks that a method replaces one, and doesn't stop replacement.
◉ final
→ Yes. A final method cannot be overridden.
○ static
→ Not quite. A static method is not replaced by a subclass, but it's hidden, not overridden, and the keyword isn't the one that stops replacement.
````
