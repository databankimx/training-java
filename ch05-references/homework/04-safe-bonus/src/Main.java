public class Main {
    static void addBonus(int[] score, int bonus) {
        if (score == null) {
            System.out.println("No score to update.");
            return;
        }
        score[0] += bonus;
    }

    public static void main(String[] args) {
        int[] points = {50};
        addBonus(points, 10);
        System.out.println("Points: " + points[0]);

        addBonus(null, 5);
        System.out.println("Points: " + points[0]);
    }
}
