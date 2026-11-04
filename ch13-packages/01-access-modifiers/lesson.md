## Access modifiers

An access modifier decides which code can use a member, meaning a field or a method. Java has four levels, and they're chosen with a keyword in front of the declaration, or with no keyword at all.

Here's the class for this step, in `src/util/Counter.java`:

```java
package util;

public class Counter {
    private int secret = 100;
    int step = 1;

    public int next() {
        secret += step;
        return secret;
    }

    int packageStep() {
        return step;
    }
}
```

The `Main` class is in the default package, so it's outside `util`. It uses only the public member:

```java
import util.Counter;

public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();
        System.out.println("next: " + counter.next());
        System.out.println("next: " + counter.next());
    }
}
```

Press **Run**. The output is:

```text
next: 101
next: 102
```

`next` is public, so any code can call it. Each call adds `step`, which is 1, to the private `secret`, and returns the new value.

### The four levels

| Keyword | Visible to |
|---|---|
| `public` | Any code, in any package. |
| `protected` | The class itself, its package, and its subclasses. Covered in the inheritance chapter. |
| (none) | Only code in the same package. This is the default, called package access. |
| `private` | Only code inside the same class. |

The field `step` has no keyword, so only code in the `util` package can see it. `packageStep` is package-private too. `secret` is private, so only `Counter` itself can read or change it.

The practical rule is to make a member as private as the job allows. Start with `private`, and widen it only when another class truly needs the member.

---

### Quiz

*These questions are written for this course.*

````quiz
Which level of access lets code in the same package see a member, but not code in other packages?
○ public
→ Not quite. public lets code in any package see the member.
◉ No keyword, which is package access.
→ Yes. A member with no access keyword is visible only inside its own package.
○ private
→ Not quite. private is visible only inside the same class, not the whole package.
````

````quiz
Why does `Main` get an error if it calls `counter.packageStep()`?
○ Because methods can never be called from another class.
→ Not quite. Methods can be called, if they're visible to the caller.
◉ Because packageStep has package access, and Main is in a different package.
→ Yes. Code outside util can't see package-private members.
○ Because the method returns an int.
→ Not quite. The return type has nothing to do with access.
````

---

### Try it in the runner

Add `System.out.println(counter.packageStep());` to `main`, and predict the result. The build fails, because `packageStep` is not public, and `Main` is in another package. Remove the line before you continue.

Then add `System.out.println(counter.secret);`, and predict the result. The build fails again, because `secret` is private to `Counter`. Remove the line before you continue.

---

Access modifiers decide the visibility of each member. The next step puts one class in each file, and shows how the files find each other.
