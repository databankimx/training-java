package hr;

public class Programmer extends Employee {
    private int bonus = 15000;

    public void setSalary(int s) {
        salary = s;
    }

    public int getSalary() {
        return salary;
    }

    public int getBonus() {
        return bonus;
    }
}
