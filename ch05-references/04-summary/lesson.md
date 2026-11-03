## Summary: References

This chapter covered how Java refers to objects.

* **A variable of an object type holds a reference.** The object itself lives elsewhere, and Java manages it.
* **Assignment copies the reference, not the object.** After `b = a;`, both names refer to one object.
* **Reassigning a name changes only that name.** Other names that referred to the old object keep it.
* **Copying an object needs a method.** A second name with `=` is an alias, not a copy.
* **`null` means no object.** An object reference starts as `null` until it's given a value.
* **Check for `null` before you use the object.** `name != null` guards a call such as `name.length()`.
* **`==` compares references.** For objects, it asks whether two names refer to the same object.
* **`equals` compares contents.** For `String` values, it asks whether the text is the same. It's case sensitive.
* **`equals` is safe with `null` on the right.** `"Java".equals(name)` returns `false` if `name` is `null`.
* **Java has no pointers you can see.** There are no addresses and no pointer arithmetic, so references can't be used to read past the end of an object.

### Check your understanding

*These questions are written for this course.*

````quiz
After `String a = "Hi"; String b = a;`, what does `a == b` give?
○ false, because b is a copy.
→ Not quite. b is a second reference to the same object, not a copy.
◉ true, because both names refer to the same object.
→ Yes. Assignment copies the reference, so the two names are the same object.
○ A compile error.
→ Not quite. The comparison is valid, and it gives true.
````

````quiz
Which expression is safest when `name` might be null, and you want to check whether it equals "Ada"?
○ name.equals("Ada")
→ Not quite. That throws a NullPointerException when name is null.
◉ "Ada".equals(name)
→ Yes. The known text is on the left, so a null name gives false, not an exception.
○ name == "Ada"
→ Not quite. That compares references, so it doesn't compare the text.
````

````quiz
What does `int[] x = {1, 2}; int[] y = {1, 2}; x == y` give?
○ true, because the elements are the same.
→ Not quite. == compares the references, and these are two different arrays.
◉ false, because they are two different array objects.
→ Yes. Each array literal creates its own object.
○ A compile error.
→ Not quite. Comparing two arrays with == is valid, and it gives false.
````
