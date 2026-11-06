## Summary: Records

This chapter covered grouping related values into one type.

* **A record declaration creates a type.** `record Car(String brand, String model, int year) { }` describes what a car holds, and variables of type `Car` are created from it.
* **Components are read with methods.** `car.year()` returns the year component of the variable `car`.
* **Each component has its own type.** The compiler checks every value passed to the constructor against it.
* **Records can be passed and returned.** A method can build one, and return it as a value.
* **A record can't be changed.** A method that needs a different value returns a new record, and the caller stores it.
* **Constructors set the components.** Every component is given a value when the record is created, and an extra constructor can supply defaults through `this(...)`.
* **Constructor arguments are matched in order.** `new Settings("Scott", 9, true)` matches values to components in the order they're declared.
* **A copy is a separate record.** Two records with the same values are independent objects, and creating one never changes another.
* **Compact constructors check values.** A record can refuse an invalid value when it's created, as the records chapter's previous steps showed.

### Check your understanding

*These questions are written for this course.*

````quiz
Which constructor gives a record a default for its volume component?
○ A constructor that sets volume to 5 in its body after creation.
→ Not quite. A record's components can't be assigned after creation, so the default must be given in the constructor call.
◉ An extra constructor that calls the full constructor with this(..., 5, ...).
→ Yes. The default is passed to the full constructor when the record is created.
○ A default keyword in the component list.
→ Not quite. Java records have no default keyword for components.
````

````quiz
A method receives a record and wants a changed version. What should it do?
○ Assign to the component, since the caller will see the change.
→ Not quite. A record's components can't be assigned.
◉ Return a new record with the new value, and let the caller store it.
→ Yes. That's how a record's value changes in Java.
○ Pass a reference to the record, so the change is made in place.
→ Not quite. A reference doesn't allow a record to change.
````

````quiz
In `new Settings("Scott", 9, true)`, what does the value 9 set?
○ The name component.
→ Not quite. The first value goes to name.
◉ The volume component, the second one declared.
→ Yes. Constructor arguments are matched to components in declaration order.
○ The muted component.
→ Not quite. muted receives the third value, true.
````
