## Homework: Bank account: walkthrough

The constructor checks the opening balance before it stores it, so a negative balance can't exist. `deposit` and `withdraw` each check their amount before changing the balance, and a refused operation returns before the `+=` or `-=` runs. The balance is unchanged after a failed call.

`main` catches each exception and prints its message. The last `printf` shows 70.00 again, because the failed withdrawal and deposit changed nothing.
