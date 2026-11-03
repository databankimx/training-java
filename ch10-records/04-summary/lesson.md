## Summary: Records

This chapter covered grouping related values under one type.

* **A record groups related values.** `record Car(String brand, String model, int year)` declares the type.
* **Components are read with methods.** `car.brand()` has parentheses, and it has the component's name.
* **Java generates the constructor, accessors, and text form.** You write only the declaration.
* **Records are immutable.** Their components are fixed when the record is created, and there are no setters.
* **A changed value is a new record.** A method that needs a different value returns a new record.
* **A compact constructor checks values.** The block runs before the components are set, so every record is valid.
* **`equals` compares components.** Two records with the same values are equal, even as separate objects.
* **`==` compares references.** For records, as for any object, it asks whether they are the same object.

### Check your understanding

*These questions are written for this course.*

````quiz
Which expression reads the `model` component of `car`?
○ car.model
→ Not quite. Component fields are private, and the accessor is a method.
◉ car.model()
→ Yes. The accessor has the component's name and parentheses.
○ car.getModel()
→ Not quite. Records don't use the get prefix.
````

````quiz
Why can't a record's `x` value be changed after it's created?
○ Because records are stored in read-only memory.
→ Not quite. The reason is the design of the record, not the memory.
◉ Because a record has no setters, and its fields are final.
→ Yes. The components are fixed in the constructor, and nothing changes them afterwards.
○ Because the compiler copies the value each time it's read.
→ Not quite. The value is fixed, not copied on each read.
````

````quiz
What runs before a record's components are set?
○ Nothing; the components are set first.
→ Not quite. A compact constructor's block runs before the components are assigned.
◉ The compact constructor's block, where a check can throw an exception.
→ Yes. The check runs first, so an invalid value is never created.
○ The toString method.
→ Not quite. toString runs when the record is printed, not when it's created.
````
