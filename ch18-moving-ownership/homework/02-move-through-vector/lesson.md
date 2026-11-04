## Homework: Move through a list

A list of owned objects is a common way to keep track of them. In this exercise, you move one resource into a list, and then move it back out.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* define a `Resource` class that implements `AutoCloseable`. Its constructor prints `Acquire <name>`, and its `close` method prints `Release <name>`
* define a `Box` class that holds at most one resource, with a `take` method that returns the resource and empties the box, and an `isEmpty` method
* in `main`, create a box named `p` that holds a resource called `vessel`
* create a `List<Box>` named `holders`
* move the resource from `p` into a new box in `holders`, and print `Size after push: ` followed by `holders.size()`
* print `p owns nothing: yes` if `p` is empty, and `p owns nothing: no` otherwise
* move the resource out of the first box into a new box named `back`, and remove the emptied box from `holders`
* print `Got back: yes` if `back` holds the resource, and `Got back: no` otherwise
* print `End`, then close the resource that `back` holds, so its `Release` line prints, and let `main` finish

### What to check

The output should be:

```text
Acquire vessel
Size after push: 1
p owns nothing: yes
Got back: yes
End
Release vessel
```

The removed box is empty, so removing it releases nothing. The release happens at the point the program closes the resource, which is after `End`.

### Why close at the end

The resource is still held by `back` when `main` reaches its last line. The program closes it explicitly, so the `Release` message appears at a point you can see. Without that line, the resource would never print a release message, since Java doesn't report when a collector frees an object.

### Extensions

* Move two resources into the list, and check the size. Explain why the order of their releases is not something to depend on in this exercise.
* Remove `holders.remove(...)` from the program, and predict what the list holds at the end. Explain why the list would still contain an empty box.

---

*This exercise is not from the book. It practices the ideas in Chapter 18, with the lists from Chapter 14.*
