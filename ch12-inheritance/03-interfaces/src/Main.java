interface Drawable {
    void draw();

    default void describe() {
        System.out.println("A drawable thing.");
    }
}

class Circle implements Drawable {
    @Override
    public void draw() {
        System.out.println("Drawing a circle");
    }
}

class Square implements Drawable {
    @Override
    public void draw() {
        System.out.println("Drawing a square");
    }

    @Override
    public void describe() {
        System.out.println("A square.");
    }
}

public class Main {
    static void show(Drawable item) {
        item.draw();
        item.describe();
    }

    public static void main(String[] args) {
        show(new Circle());
        show(new Square());
    }
}
