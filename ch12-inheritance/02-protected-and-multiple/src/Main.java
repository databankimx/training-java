import hr.Engineer;

public class Main {
    public static void main(String[] args) {
        Engineer e = new Engineer();
        e.setSalary(50000);
        System.out.println("Salary: " + e.getSalary());
        System.out.println("Bonus: " + e.getBonus());
        System.out.println("Badge: " + e.code());
    }
}
