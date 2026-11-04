class BankAccount {
    private double balance;

    BankAccount(double openingBalance) {
        if (openingBalance < 0) {
            throw new IllegalArgumentException("opening balance cannot be negative");
        }
        balance = openingBalance;
    }

    void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit must be positive.");
        }
        balance += amount;
    }

    void withdraw(double amount) {
        if (amount > balance) {
            throw new IllegalStateException("Insufficient funds.");
        }
        balance -= amount;
    }

    double getBalance() {
        return balance;
    }
}

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(0.0);
        account.deposit(100.0);
        account.withdraw(30.0);
        System.out.printf("Balance: %.2f%n", account.getBalance());

        try {
            account.withdraw(500.0);
        } catch (IllegalStateException error) {
            System.out.println(error.getMessage());
        }

        try {
            account.deposit(-5.0);
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
        }

        System.out.printf("Balance: %.2f%n", account.getBalance());
    }
}
