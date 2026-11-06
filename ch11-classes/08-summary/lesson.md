## Summary: Classes

This chapter covered classes, which group data with the methods that work on it.

* **A class is a type, and its variables are objects.** Each object has its own copy of the fields the class declares.
* **Access keywords control visibility.** `public` members are the interface, and `private` members are hidden from outside code. A member with no keyword is visible within its package.
* **A method belongs to a class.** It's called on an object, and it uses that object's fields directly. A method that only reads is written for that purpose, and fields that must not change are `final`.
* **A constructor sets up an object when it's created.** It has the class's name and no return type, and it takes whatever arguments an object needs.
* **`new` calls the constructor.** Once a class has a constructor, the arguments of `new` must match one of its constructors.
* **`super` calls the base constructor.** A derived constructor passes the arguments the base part needs, as its first statement.
* **Encapsulation keeps the rules in one place.** Private fields, with setters that check each change, mean an object can't be left in a state its class doesn't allow.

### Check your understanding

*These questions are written for this course.*

````quiz
What access does a member have when it has no access keyword?
○ public
→ Not quite. Public needs the keyword.
◉ Package access, visible to code in the same package.
→ Yes. Without a keyword, a member is visible within its own package.
○ private
→ Not quite. Private needs the keyword.
````

````quiz
What does `final` do to a field?
○ It makes the field faster to read.
→ Not quite. The keyword is about changing the field, not speed.
◉ It makes the compiler check that the field is set once, and never changed afterwards.
→ Yes. A final field must be assigned exactly once, and the compiler enforces that.
○ It makes the field visible to all code.
→ Not quite. That's the job of public.
````

````quiz
A class has a constructor that takes a brand, a model, and a year. Which creation is valid in Java?
○ Car myCar = new Car();
→ Not quite. With a constructor taking arguments, there is no no-argument constructor to use.
◉ Car myCar = new Car("Ford", "Mustang", 1969);
→ Yes. The arguments match the constructor's parameters.
○ Car myCar = new Car("Ford");
→ Not quite. The constructor needs all three arguments.
````
