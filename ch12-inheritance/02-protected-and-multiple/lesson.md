## Protected members and multiple inheritance

Inheritance has access levels that matter for members. `public` members are visible to everyone, and `private` members are visible only inside their own class. `protected` sits between them: a protected member is visible inside its class, in classes derived from it, and in the same package, but not to code outside that family.

Here's the program for this step. The base class is in the `hr` package, in `src/hr/Employee.java`, and it has a protected field:

```java
package hr;

public class Employee {
    protected int salary = 0;
}
```

The derived class can use `salary` directly, since it's inside the family:

```java
package hr;

public class Programmer extends Employee {
    private int bonus = 15000;

    public void setSalary(int s) {
        salary = s;
    }

    public int getSalary() {
        return salary;
    }

    public int getBonus() {
        return bonus;
    }
}
```

`Main` is in the default package, outside `hr`, so it can't touch `salary`. The methods the class provides are the only way to read and change it, which is encapsulation again.

Press **Run**. The output is:

```text
Salary: 50000
Bonus: 15000
Badge: B-42
```

### More than one base

A Java class can extend only one class. A class can still take on the behavior of several sources by implementing interfaces, and an interface can supply a default method. Here the `Badge` interface provides `code`:

```java
package hr;

public interface Badge {
    default String code() {
        return "B-42";
    }
}
```

The `Engineer` class extends `Programmer` and implements `Badge`:

```java
package hr;

public class Engineer extends Programmer implements Badge {
}
```

`Engineer` gets the members of `Programmer`, including the methods that read the salary and bonus, and the default method `code` from `Badge`. `e.code()` works alongside `e.getBonus()`, because each source contributes its own methods.

A class and an interface can have methods with the same name. If two interfaces supply the same default method, the class must override it and say which one it means. Keep the sources independent, so that each name has one meaning.

### Choosing the access level

The rule for choosing is simple. Make a member `private` if only the class needs it. Make it `protected` if derived classes need it to do their job. Make it `public` only when the rest of the program needs it. Each step outward gives more code the power to change the member, so the smallest level that works is the safest.

---

### Quiz

*These questions are written for this course.*

````quiz
Which code can read a protected field of the base class in another package?
○ main, which is outside the class.
→ Not quite. Code outside the class hierarchy and its package can't read protected members.
◉ A class derived from the base.
→ Yes. Protected members are visible inside derived classes.
○ Any object of the base class, from anywhere.
→ Not quite. Protected is not public, so code outside the family can't use it through an object.
````

````quiz
What does `class Engineer extends Programmer implements Badge {}` give Engineer?
○ Two superclasses.
→ Not quite. A class can extend only one class.
◉ The members of Programmer, and the default methods of Badge.
→ Yes. The class takes its fields and methods from the class it extends, and its behavior from the interface it implements.
○ A copy of Badge's fields.
→ Not quite. Interfaces don't hold instance fields for a class to copy.
````

---

### Try it in the runner

Add `e.salary = 60000;` to `main`, and predict whether it compiles before you press **Run**. It doesn't. `salary` is protected, and `main` is in a different package, so it can't use it, even through an `Engineer`. The compiler names the field and says it has protected access.

Then change `salary` to `public` in `Employee`, and press **Run** again. The build now succeeds, which shows why protected exists: public access lets every part of the program change the salary, which is exactly what encapsulation prevents. Change it back to `protected`.

---

Protected members give derived classes access without opening a member to everyone, and interfaces let a class take on behavior from more than one source. The next step covers interfaces, which describe what a class can do.
