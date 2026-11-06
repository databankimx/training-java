import tally.Tally;

public class Main {
    public static void main(String[] args) {
        Tally tally = new Tally();
        tally.addOne();
        tally.addOne();
        System.out.println("Total: " + tally.total());
    }
}
