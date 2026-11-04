class Car {
    String brand;
    String model;
    int year;
}

public class Main {
    public static void main(String[] args) {
        Car carObj1 = new Car();
        carObj1.brand = "BMW";
        carObj1.model = "X5";
        carObj1.year = 1999;

        Car carObj2 = new Car();
        carObj2.brand = "Ford";
        carObj2.model = "Mustang";
        carObj2.year = 1969;

        System.out.println(carObj1.brand + " " + carObj1.model + " " + carObj1.year);
        System.out.println(carObj2.brand + " " + carObj2.model + " " + carObj2.year);
    }
}
