class Employee {
    protected int monthlySalary;

    Employee(int salary) {
        monthlySalary = salary;
    }

    int pay() {
        return monthlySalary;
    }
}

class Manager extends Employee {
    private final int bonus;

    Manager(int salary, int bonus) {
        super(salary);
        this.bonus = bonus;
    }

    @Override
    int pay() {
        return monthlySalary + bonus;
    }
}

public class Main {
    static void printPay(Employee employee) {
        System.out.println("Monthly pay: " + employee.pay());
    }

    public static void main(String[] args) {
        printPay(new Employee(3000));
        printPay(new Manager(4500, 800));
    }
}
