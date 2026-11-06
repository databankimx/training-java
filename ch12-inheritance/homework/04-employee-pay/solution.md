## Homework: Employee pay: walkthrough

`Employee.pay` returns the salary. `Manager.pay` replaces it with the salary plus the bonus. `printPay` takes an `Employee`, and calls `pay` on it, so the call runs the version for the object's real class.

The `Manager` constructor passes the salary up with `super(salary)`, so the base class sets `monthlySalary`. The bonus is kept in the `Manager`'s own field, since only `Manager` uses it.

For the sample, the employee prints 3000, and the manager prints 4500 + 800, which is 5300.
