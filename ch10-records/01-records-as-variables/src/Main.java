record Car(String brand, String model, int year) {
}

public class Main {
    public static void main(String[] args) {
        Car myCar1 = new Car("BMW", "X5", 1999);
        Car myCar2 = new Car("Ford", "Mustang", 1969);

        System.out.println(myCar1.brand() + " " + myCar1.model() + " " + myCar1.year());
        System.out.println(myCar2.brand() + " " + myCar2.model() + " " + myCar2.year());
    }
}
