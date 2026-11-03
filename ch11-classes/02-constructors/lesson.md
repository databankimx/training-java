## Constructors

A constructor sets up a new object. It runs when `new` is used, and its job is to give every field a correct starting value before anything else can use the object.

Here's the program for this step, in `src/Main.java`. The class `Account` has two constructors:

```java
class Account {
    private final String owner;
    private int balance;

    Account(String owner) {
        this(owner, 0);
    }

    Account(String owner, int startingBalance) {
        this.owner = owner;
        this.balance = startingBalance;
    }

    String summary() {
        return owner + ": " + balance;
    }
}
```

Press **Run**. The output is:

```text
Ada: 100
Grace: 0
```

A constructor has the same name as its class, and no return type, not even `void`. The `new Account("Ada", 100)` call runs the two-value constructor, and `new Account("Grace")` runs the one-value constructor.

### The this keyword

Inside a constructor, `this` refers to the object being created. The line `this.balance = startingBalance;` uses it to tell the field `balance` apart from the parameter with the same meaning. The field is `this.balance`, and the parameter is simply `startingBalance`, so the two names don't clash.

The one-value constructor calls the two-value one with `this(owner, 0);`. That call must be the first line of the constructor, and it lets one constructor do the real work, while the others supply defaults. This is the same overloading idea from the methods chapter, applied to constructors.

### Final fields

The `owner` field is declared `final`, so it must be set exactly once, in a constructor. After that, nothing can change it, which is the right choice for a value that shouldn't change, such as an account's owner. `balance` is not final, because it changes as money moves in and out.

### When Java supplies a constructor

If a class declares no constructor at all, Java supplies one with no parameters. Once you declare any constructor, Java doesn't add that default, so `new Account()` would fail here. That's why the class has the one-value constructor that makes a sensible default for the balance.

---

### Quiz

*These questions are written for this course.*

````quiz
What is the return type of a constructor?
○ void
→ Not quite. A constructor has no return type at all, not even void.
◉ None: a constructor is written without a return type.
→ Yes. Its name is the class name, and it has no return type.
○ The class name.
→ Not quite. The class name is the constructor's name, not its return type.
````

````quiz
Why does the constructor `Account(String owner)` call `this(owner, 0)`?
○ Because a constructor must always call another one.
→ Not quite. Only the one-value constructor here calls another.
◉ To reuse the two-value constructor, so the balance is set in one place.
→ Yes. The call runs the constructor that does the real work.
○ To create a second object.
→ Not quite. The call sets up the same object, and doesn't create another.
````

---

### Try it in the runner

Remove the one-value constructor, and change `new Account("Grace")` to `new Account("Grace", 0)`. Predict the output. The program prints the same two lines, because the two-value constructor does the same work. Restore the one-value constructor before you continue.

Then change `this.owner = owner;` in the two-value constructor to `owner = owner;`, so the field is never set. Predict the result. The build fails, with an error saying the final field `owner` might not have been initialized. The compiler refuses to build a constructor that leaves a final field empty. Restore `this.owner` before you continue.

---

A constructor sets up each new object, and it can hand its work to another constructor with `this(...)`. The next step covers encapsulation, which keeps the fields of an object under the class's control.
