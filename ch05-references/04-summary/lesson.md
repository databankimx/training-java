## Summary: References

This chapter covered how Java refers to an object without copying it, and how array references reach their elements.

* **A reference is another name for an object.** A second variable assigned from the first refers to the same object.
* **Changes to the object are seen through every name.** Changing an element through one reference changes it for all of them.
* **Reassigning a reference changes only that variable.** The old object is unchanged, and the other names keep it.
* **A primitive variable holds its own value.** It can't be a second name for another `int`.
* **`null` means no object.** A reference can be `null`, and it must be checked before use.
* **Using a `null` reference throws.** Reading an element through it throws a `NullPointerException`.
* **Array references reach elements by index.** Each element is read with `[index]`, counting from zero, and the index is checked.
* **An array knows its length.** A method that receives an array uses `length`, so no separate count is passed.

### Check your understanding

*These questions are written for this course.*

````quiz
After `int[] b = a;`, what does `b[0] = 7;` change?
○ Only b, since b is a copy.
→ Not quite. The assignment copied the reference, so b and a are the same array.
◉ The element that both a and b refer to.
→ Yes. Both names refer to one array, so either name sees the change.
○ Nothing, because arrays can't be changed through a reference.
→ Not quite. Changing an element through a reference is the normal way to use one.
````

````quiz
Which check is safe before reading `r[0]`, when `r` might be empty?
○ if (r[0] == null)
→ Not quite. That reads the element first, which is the failure the check should prevent.
◉ if (r != null)
→ Yes. The check happens before the element is read.
○ if (r == 0)
→ Not quite. A reference is compared with null, not with 0.
````

````quiz
What does the `length` of an array give, for a method that receives the array?
○ The number of values the caller intended to use.
→ Not quite. It's the number of elements the array actually holds.
◉ The number of elements, so the method needs no separate count.
→ Yes. An array carries its own length, which is why it's passed as a single parameter.
○ The size of one element in bytes.
→ Not quite. length counts elements, not bytes.
````
