package util;

public class Counter {
    private int secret = 100;
    int step = 1;

    public int next() {
        secret += step;
        return secret;
    }

    int packageStep() {
        return step;
    }
}
