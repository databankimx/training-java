import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Main {
    static class Car {
        private final String brand;
        private final int year;

        Car(String brand, int year) {
            this.brand = brand;
            this.year = year;
        }

        // Two cars are equal when their brand and year are equal, whichever objects they are.
        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Car car)) {
                return false;
            }
            return brand.equals(car.brand) && year == car.year;
        }

        // Equal cars must produce the same hash code, so hash-based collections can find them.
        @Override
        public int hashCode() {
            return Objects.hash(brand, year);
        }

        // The text printed when a car is shown in output or joined into a string.
        @Override
        public String toString() {
            return brand + " (" + year + ")";
        }
    }

    public static void main(String[] args) {
        Car first = new Car("Ford", 1969);
        Car second = new Car("Ford", 1969);

        System.out.println("first == second: " + (first == second));
        System.out.println("first.equals(second): " + first.equals(second));

        Set<Car> garage = new HashSet<>();
        garage.add(first);
        garage.add(second);
        System.out.println("Cars in the garage: " + garage.size());

        System.out.println("Car: " + first);
    }
}
