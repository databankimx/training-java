record Point(int x, int y) {
}

public class Main {
    public static void main(String[] args) {
        Point home = new Point(0, 0);
        Point cafe = new Point(0, 0);
        Point park = new Point(5, 2);

        System.out.println("home equals cafe: " + home.equals(cafe));
        System.out.println("home equals park: " + home.equals(park));
        System.out.println("home == cafe: " + (home == cafe));
        System.out.println("home == home: " + (home == home));
    }
}
