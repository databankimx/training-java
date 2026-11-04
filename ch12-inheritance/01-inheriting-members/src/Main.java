class Vehicle {
    String brand = "Ford";

    void honk() {
        System.out.println("Toot, toot!");
    }
}

class Car extends Vehicle {
    String model = "Mustang";
}

class SportsCar extends Car {
    int topSpeed = 180;
}

public class Main {
    public static void main(String[] args) {
        Car myCar = new Car();
        myCar.honk();
        System.out.println(myCar.brand + " " + myCar.model);

        SportsCar fast = new SportsCar();
        fast.honk();
        System.out.println(fast.brand + " " + fast.model + " " + fast.topSpeed);
    }
}
