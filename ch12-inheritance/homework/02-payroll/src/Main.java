abstract class Employee {
    private final String name;

    Employee(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    abstract int monthlyPay();
}

class Salaried extends Employee {
    private final int annualSalary;

    Salaried(String name, int annualSalary) {
        super(name);
        this.annualSalary = annualSalary;
    }

    @Override
    int monthlyPay() {
        return annualSalary / 12;
    }
}

class Hourly extends Employee {
    private final int hourlyRate;
    private final int hours;

    Hourly(String name, int hourlyRate, int hours) {
        super(name);
        this.hourlyRate = hourlyRate;
        this.hours = hours;
    }

    @Override
    int monthlyPay() {
        return hourlyRate * hours;
    }
}

public class Main {
    public static void main(String[] args) {
        Employee[] staff = {
            new Salaried("Ada", 60000),
            new Hourly("Grace", 25, 160)
        };
        for (Employee person : staff) {
            System.out.println(person.getName() + ": " + person.monthlyPay());
        }
    }
}
