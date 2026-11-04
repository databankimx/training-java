public class Main {
    // A pair of values, which may be of different types. First and Second are the two type parameters.
    static class Pair<First, Second> {
        private final First first;
        private final Second second;

        Pair(First first, Second second) {
            this.first = first;
            this.second = second;
        }

        First getFirst() {
            return first;
        }

        Second getSecond() {
            return second;
        }
    }

    public static void main(String[] args) {
        Pair<Integer, String> numberAndWord = new Pair<>(3, "three");
        System.out.println("Pair: " + numberAndWord.getFirst() + " " + numberAndWord.getSecond());

        Pair<String, Double> nameAndValue = new Pair<>("pi", 3.14);
        System.out.println("Pair: " + nameAndValue.getFirst() + " " + nameAndValue.getSecond());
    }
}
