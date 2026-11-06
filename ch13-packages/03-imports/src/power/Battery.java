package power;

public class Battery {
    private final int capacity;
    private int charge = 0;

    public Battery(int capacity) {
        this.capacity = capacity;
    }

    public void recharge(int amount) {
        if (amount == 0) {
            charge = capacity;
        } else {
            charge += amount;
        }
        if (charge > capacity) {
            charge = capacity;
        }
    }

    public void recharge() {
        recharge(0);
    }

    public void show() {
        System.out.println("Charge: " + charge);
        System.out.println("Capacity: " + capacity);
        System.out.println();
    }
}
