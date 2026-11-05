class Car {
    String brand;
    String model;
    int year;

    Car(String mk, String mod, int yr) {
        brand = mk;
        model = mod;
        year = yr;
    }
}

public class Main {
    public static void main(String[] args) {
        Car myCar1 = new Car("Ford", "Mustang", 1969);
        Car myCar2 = new Car("Dodge", "Challenger", 2014);

        System.out.println(myCar1.year + " " + myCar1.brand + " " + myCar1.model);
        System.out.println(myCar2.year + " " + myCar2.brand + " " + myCar2.model);
    }
}
