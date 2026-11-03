record Car(String brand, String model, int year) {
}

public class Main {
    public static void main(String[] args) {
        Car myCar = new Car("Toyota", "Corolla", 2020);

        System.out.println("Brand: " + myCar.brand());
        System.out.println("Model: " + myCar.model());
        System.out.println("Year: " + myCar.year());
        System.out.println("Whole car: " + myCar);
    }
}
