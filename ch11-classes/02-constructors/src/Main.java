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

public class Main {
    public static void main(String[] args) {
        Account ada = new Account("Ada", 100);
        Account grace = new Account("Grace");
        System.out.println(ada.summary());
        System.out.println(grace.summary());
    }
}
