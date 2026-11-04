public class Main {
    public static void main(String[] args) {
        CarDescription first = new Car("Ford", "Escort", 1986);
        first.display();
        System.out.println();

        CarDescription second = new Car("Nissan", "Leaf", 2015);
        second.display();
        System.out.println();
    }
}
