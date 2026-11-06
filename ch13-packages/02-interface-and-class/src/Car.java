public class Car implements CarDescription {
    private final String make;
    private final String model;
    private final int year;

    public Car(String makeName, String modelName, int yearMade) {
        make = makeName;
        model = modelName;
        year = yearMade;
    }

    @Override
    public void display() {
        System.out.println("Make: " + make);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
    }
}
