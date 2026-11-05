class Car {
    String brand;
    String model;
    int year;

    String name() {
        return brand + " " + model;
    }

    String fullName() {
        return year + " " + brand + " " + model;
    }
}

public class Main {
    public static void main(String[] args) {
        Car myCar = new Car();
        myCar.brand = "Ford";
        myCar.model = "Mustang";
        myCar.year = 1969;

        System.out.println(myCar.name());
        System.out.println(myCar.fullName());
    }
}
