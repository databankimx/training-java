class ElectricCar extends Car {
    private final Battery battery = new Battery();

    ElectricCar(String makeName, String modelName, int yearMade, int charge) {
        super(makeName, modelName, yearMade);
        if (charge > 0) {
            battery.recharge(charge);
        }
    }

    void charge(int amount) {
        battery.recharge(amount);
    }

    void charge() {
        battery.recharge(0);
    }

    void showCharge() {
        battery.show();
    }
}
