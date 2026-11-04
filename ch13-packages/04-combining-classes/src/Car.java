class Car {
    private final String make;
    private final String model;
    private final int year;

    Car(String makeName, String modelName, int yearMade) {
        make = makeName;
        model = modelName;
        year = yearMade;
    }

    void display() {
        System.out.println("Make: " + make);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
    }
}
