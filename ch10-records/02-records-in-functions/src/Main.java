record Car(String brand, String model, int year) {
}

public class Main {
    static Car makeCar(String brand, String model, int year) {
        return new Car(brand, model, year);
    }

    static void describe(Car car) {
        System.out.println(car.brand() + " " + car.model() + " " + car.year());
    }

    static Car makeOlder(Car car) {
        return new Car(car.brand(), car.model(), car.year() - 10);
    }

    public static void main(String[] args) {
        Car mine = makeCar("BMW", "X5", 1999);
        describe(mine);

        mine = makeOlder(mine);
        describe(mine);

        Car[] cars = {makeCar("Ford", "Mustang", 1969), makeCar("Fiat", "500", 2010)};
        for (Car car : cars) {
            describe(car);
        }
    }
}
