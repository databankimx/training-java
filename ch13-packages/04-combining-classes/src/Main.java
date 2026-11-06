public class Main {
    public static void main(String[] args) {
        Car first = new Car("Ford", "Escort", 1986);
        first.display();
        System.out.println();

        Car second = new Car("Nissan", "Leaf", 2015);
        second.display();
        System.out.println();

        ElectricCar eCar = new ElectricCar("Nissan", "Leaf", 2015, 25);
        eCar.showCharge();

        eCar.charge(25);
        eCar.showCharge();

        eCar.charge();
        eCar.showCharge();
    }
}
