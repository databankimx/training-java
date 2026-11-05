import java.util.Iterator;

public class Main {
    // A countdown that can be used in an enhanced for loop, because it implements Iterable.
    static class Countdown implements Iterable<Integer> {
        private final int start;

        Countdown(int start) {
            this.start = start;
        }

        @Override
        public Iterator<Integer> iterator() {
            return new Iterator<Integer>() {
                private int next = start;

                @Override
                public boolean hasNext() {
                    return next > 0;
                }

                @Override
                public Integer next() {
                    return next--;
                }
            };
        }
    }

    public static void main(String[] args) {
        for (int value : new Countdown(3)) {
            System.out.println(value);
        }
        System.out.println("Liftoff");

        for (int value : new Countdown(0)) {
            System.out.println(value);
        }
        System.out.println("The empty countdown printed nothing");
    }
}
