## Encapsulation

Encapsulation means a class keeps its data private, and controls every change to it through its own methods. The class decides what counts as a valid change, so no other part of the program can put the data into a state the class doesn't allow.

Here's the program for this step, in `src/Main.java`. The class `BankAccount` keeps its balance private:

```java
class BankAccount {
    private int balance;

    int getBalance() {
        return balance;
    }

    void deposit(int amount) {
        if (amount <= 0) {
            System.out.println("Deposit rejected: " + amount);
            return;
        }
        balance += amount;
    }

    boolean withdraw(int amount) {
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }
}
```

Press **Run**. The output is:

```text
Deposit rejected: -5
Balance: 100
Withdraw 30 succeeded: true
Balance: 70
Withdraw 500 succeeded: false
Balance: 70
```

`balance` is declared `private`, so only code inside `BankAccount` can touch it. `Main` has to use the methods, and each method checks the request before it changes anything.

### The checks

`deposit` refuses an amount of zero or less, and prints a message. The rejected deposit never reaches `balance`, so the balance can't go down by a negative deposit. `withdraw` refuses an amount larger than the balance, and returns `false`, so the caller can tell the request failed.

Both methods are the only way the balance can change. That's the point of encapsulation: the rules live in one place, next to the data they protect, and the rest of the program can rely on them.

### Getters

`getBalance` is a getter, which returns a private field for reading. Java programmers write one for each field the rest of the program needs to see, and they keep the field private. A getter without a matching setter gives read-only access, and that's often all a value needs.

The old style was to write a setter for every field. Prefer a method with a meaningful name, such as `deposit`, over a plain setter, because it says what the change means and can check it.

---

### Quiz

*These questions are written for this course.*

````quiz
Why is `balance` declared `private`?
○ So that it can be read faster.
→ Not quite. The reason is control over changes, not speed.
◉ So that only the class's own methods can change it, and they can check the change.
→ Yes. Private fields are changed only through the methods that protect them.
○ Because private fields are always zero.
→ Not quite. A private field can hold any value its class allows.
````

````quiz
What does `withdraw(500)` return when the balance is 70?
○ true, and the balance becomes negative.
→ Not quite. The check refuses an amount larger than the balance.
◉ false, and the balance stays 70.
→ Yes. The request is refused, so nothing changes, and the caller is told.
○ An exception, which stops the program.
→ Not quite. The method returns false, and doesn't throw.
````

---

### Try it in the runner

Add `account.balance = 1000;` after the existing lines, and predict the result. The build fails, because `balance` has private access in `BankAccount`. Remove the line before you continue.

Then change `deposit(-5)` to `deposit(0)`, and predict the output. The deposit is refused with `Deposit rejected: 0`, because the check is `amount <= 0`. Restore the original call before you continue.

---

Encapsulation keeps a class's rules next to its data, so no other code can break them. The summary gathers the chapter together, and the homework builds a class of its own.
