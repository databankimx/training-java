class Box<T> {
    private T item;

    Box(T value) {
        item = value;
    }

    T get() {
        return item;
    }

    void set(T value) {
        item = value;
    }
}

public class Main {
    public static void main(String[] args) {
        Box<Integer> number = new Box<>(42);
        Box<String> word = new Box<>("hello");

        System.out.println(number.get());
        number.set(7);
        System.out.println(number.get());
        System.out.println(word.get());
    }
}
