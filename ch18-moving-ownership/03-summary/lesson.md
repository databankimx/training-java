## Summary: Copies, references, and ownership

This chapter covered the three ways one part of a program can give another part access to an object.

* **A copy makes a second, independent object.** The copy method makes a new object, and changes to one don't affect the other.
* **A reference shares one object.** Two references see the same changes, and `==` on references compares identity.
* **A hand-off gives an object to one new holder.** The first holder is emptied, so only one holder uses the object at a time.
* **Java can't enforce a hand-off.** Any number of references can refer to one object, so the program keeps the rule itself, usually with a method that takes the object and empties its holder.
* **Check before using a taken value.** A holder that has been emptied returns `null`, and an `isEmpty` test is the safe check.
* **The close is separate from the hand-off.** A hand-off decides who may use an object. Releasing it at a known point needs `AutoCloseable`, as the resource management chapter showed.

### Check your understanding

*These questions are written for this course.*

````quiz
Which way gives a second part of the program its own object, with changes kept separate?
○ A reference, such as Buffer c = a;
→ Not quite. A reference shares the object, so changes are seen through both names.
◉ A copy, such as Buffer b = a.copy();
→ Yes. The copy is a second object, and it changes independently.
○ Comparing the two with ==
→ Not quite. == compares identity, and it doesn't create anything.
````

````quiz
After a hand-off with `take()`, what does the first holder give back when it's asked for its contents?
○ The object it had before the hand-off.
→ Not quite. The hand-off removed the object from the holder.
◉ null, so the program must check isEmpty() before using the value.
→ Yes. An empty holder returns null, and the check prevents a NullPointerException.
○ A copy of the object.
→ Not quite. A hand-off does not copy the object.
````

````quiz
Why can't Java enforce the rule that a handed-off object has only one holder?
○ Because Java objects can't be stored in a box.
→ Not quite. A box can hold any object.
◉ Because any number of references can refer to one object, so the program must keep the rule.
→ Yes. The language allows many references, and the hand-off method keeps the count at one.
○ Because the garbage collector copies objects when they're handed off.
→ Not quite. The collector doesn't copy objects.
````
