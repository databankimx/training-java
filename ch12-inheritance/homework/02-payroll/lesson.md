## Payroll

A company pays two kinds of employee. A salaried employee earns a fixed annual salary, and an hourly employee earns a rate for each hour worked. Both have a name and a monthly pay, but the pay is worked out in different ways. Write the hierarchy, and print the monthly pay of each employee through the parent type.

Write your own program in your project first. If you get stuck, reveal the example: the source shows the finished program, and the section below says which lines to change for your own staff.

The program should:

* declare `abstract class Employee` with a name and an abstract method `int monthlyPay()`
* declare `Salaried`, with an annual salary, whose monthly pay is the salary divided by 12
* declare `Hourly`, with an hourly rate and a number of hours, whose monthly pay is the rate times the hours
* store Ada, salaried at 60000 a year, and Grace, hourly at 25 for 160 hours, in an array of `Employee`
* print each name and monthly pay, with a colon between them

### Where to change the code

The two `monthlyPay` methods hold the formulas, and each subclass constructor sets its fields. To add a kind of employee, subclass `Employee` and implement `monthlyPay`, and the loop works unchanged.

### What to check

The output should be:

```text
Ada: 5000
Grace: 4000
```

Ada's salary of 60000 divided by 12 is 5000. Grace earns 25 times 160, which is 4000.

### Extensions

* Give hourly employees overtime, paying 1.5 times the rate for any hours over 160 in the month, and check the result for 170 hours.
* Print the total payroll, by adding each employee's monthly pay in a loop over the array.

---

*This exercise is not from the book. It practices the ideas in Chapter 12.*
