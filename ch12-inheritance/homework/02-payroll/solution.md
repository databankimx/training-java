## Payroll: walkthrough

`Employee` holds the name and declares `monthlyPay` as abstract, so every employee must supply a pay formula. The loop calls `monthlyPay` through the `Employee` type, and the right formula runs for each object.

Salaried pay uses integer division, `annualSalary / 12`, so any leftover cents are dropped. For this exercise the salary divides evenly, so the result is exact. A real payroll would use `double` or exact decimal values, which a later chapter discusses.

Each subclass passes the name to the `Employee` constructor with `super(name)`, so the name is set in one place and is private in the parent.
