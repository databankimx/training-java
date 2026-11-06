## Homework: Employee pay

Write an `Employee` class and a `Manager` class extending it, so that each kind of employee computes its own pay, and a single method prints any employee's pay.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own version.

The program should:

* define `Employee`, with a `protected` field `monthlySalary` of type `int`, a constructor that takes the salary, and a method `pay` that returns `monthlySalary`
* define `Manager`, extending `Employee`, with a constructor that takes a salary and a `bonus`, stores the bonus in a `private` field, and overrides `pay` to return the salary plus the bonus. The constructor passes the salary to the base class with `super(salary)`, which the constructors lesson explained.
* define `printPay`, which takes an `Employee` and prints `Monthly pay: ` followed by the value of `pay()`
* in `main`, create an `Employee` with a salary of 3000, a `Manager` with a salary of 4500 and a bonus of 800, and print the pay for each with `printPay`

### What to check

The output should be:

```text
Monthly pay: 3000
Monthly pay: 5300
```

The second line uses the manager's version of `pay`, even though `printPay` only knows about `Employee`. If it prints 4500, the `pay` method in `Manager` isn't replacing the one in `Employee`.

### Extensions

* Make `bonus` a `protected` field instead, and add a third class, `Director`, extending `Manager`, that adds a further amount. Think about which class's `pay` should be used, and how `Director` reaches the bonus.
* Add a second `printPay` that takes an `int` pay amount and prints it in the same format. Check which version each call uses, and explain how Java chooses between them.

---

*This exercise is not from the book. It practices the ideas in Chapter 12.*
