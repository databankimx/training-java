class Battery {
    private final int capacity;
    private int charge = 0;

    Battery() {
        this(100);
    }

    Battery(int capacity) {
        this.capacity = capacity;
    }

    void recharge(int amount) {
        if (amount == 0) {
            charge = capacity;
        } else {
            charge += amount;
        }
        if (charge > capacity) {
            charge = capacity;
        }
    }

    void show() {
        System.out.println("Charge: " + charge);
        System.out.println("Capacity: " + capacity);
        System.out.println();
    }
}
