class Car {
    private String brand;
    private String model;
    private int year = 0;

    Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        setYear(year);
    }

    int getYear() {
        return year;
    }

    void setYear(int yr) {
        if (yr < 1886) {
            throw new IllegalArgumentException("year is before cars existed");
        }
        year = yr;
    }

    String describe() {
        return year + " " + brand + " " + model;
    }
}

public class Main {
    public static void main(String[] args) {
        Car myCar = new Car("Ford", "Mustang", 1969);
        System.out.println(myCar.describe());

        myCar.setYear(2014);
        System.out.println(myCar.describe());

        try {
            myCar.setYear(1700);
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
        }
        System.out.println(myCar.describe());
    }
}
