## Copies and references

Copying an object makes a second, independent object, and for a large object that can be expensive. A buffer of a million characters has to be duplicated. Passing an object passes a reference, so no contents are copied at all. The cost of a copy is paid only when your code calls a method that makes one. That's the choice this step shows.

Here's the class from `src/Main.java`. The `copy` method makes a second object, and the program makes its own printed note of each step:

```java
static class Buffer {
    private String data;

    Buffer(String data) {
        this.data = data;
        System.out.println("Create " + data);
    }

    // A copy is a second, independent object with the same contents.
    Buffer copy() {
        System.out.println("Copy " + data);
        return new Buffer(data);
    }

    void changeTo(String text) {
        data = text;
    }

    String text() {
        return data;
    }
}
```

The program makes three variables from the first object, in two different ways:

```java
Buffer a = new Buffer("alpha");
Buffer b = a.copy();
Buffer c = a;
```

`b` is a copy: a second object, with its own contents. `c` is not a copy. It's a second reference to the same object as `a`, and no new object is made at all.

Press **Run**. The output is:

```text
Create alpha
Copy alpha
Create alpha
a and c are the same object: true
a and b are the same object: false
a: changed
b: alpha
c: changed
```

Changing the object through `c` changes what `a` sees, because they are the same object. `b` is unaffected, because it's a copy that has its own text.

### Choosing between a copy and a reference

A reference is the right choice when two parts of the program should see the same object, and the object is meant to change. A copy is the right choice when the second part needs its own version, that the first part mustn't change. Making a copy costs time and memory in proportion to the size of the object, so a program copies only when it needs the separation.

The next step covers the case where the object has to be handed to another part of the program, and only one part should use it afterwards.

---

### Quiz

*These questions are written for this course.*

````quiz
After `Buffer c = a;`, what does a change through `c` do to `a`?
○ Nothing, because c is a copy.
→ Not quite. c is not a copy. It refers to the same object as a.
◉ It changes the object that a also refers to, since they are the same object.
→ Yes. Two references to one object see the same changes.
○ It throws an exception.
→ Not quite. Changing an object through any reference is allowed.
````

````quiz
Which line makes a second, independent object with the same contents?
○ Buffer c = a;
→ Not quite. That makes a second reference, not a second object.
◉ Buffer b = a.copy();
→ Yes. The copy method makes a new object with its own contents.
○ Buffer b = a;
→ Not quite. That makes a second reference to the same object, not a copy.
````

---

### Try it in the runner

Add `Buffer d = a;` after the existing lines, and predict whether `Create` or `Copy` prints. Neither prints, since `d` is only another reference. Then call `d.changeTo("again")`, and predict the output of `a`. It's `again`, since `a` and `d` share one object.

---

Copies and references are the two ways to give a second part of the program access to an object. The next step looks at a case where only one part should keep the object, and the object has to be handed over.
