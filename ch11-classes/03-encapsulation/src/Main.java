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

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        account.deposit(100);
        account.deposit(-5);
        System.out.println("Balance: " + account.getBalance());

        boolean ok = account.withdraw(30);
        System.out.println("Withdraw 30 succeeded: " + ok);
        System.out.println("Balance: " + account.getBalance());

        boolean tooMuch = account.withdraw(500);
        System.out.println("Withdraw 500 succeeded: " + tooMuch);
        System.out.println("Balance: " + account.getBalance());
    }
}
