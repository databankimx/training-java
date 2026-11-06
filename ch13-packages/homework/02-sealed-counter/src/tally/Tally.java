package tally;

public class Tally {
    private int total;
    private static final int STEP = 5;

    public void addOne() {
        total += STEP;
    }

    public int total() {
        return total;
    }

    int resetInternal() {
        total = 0;
        return total;
    }
}
