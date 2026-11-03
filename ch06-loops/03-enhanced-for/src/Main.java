public class Main {
    public static void main(String[] args) {
        int[] scores = {90, 85, 77};
        for (int score : scores) {
            System.out.println("score: " + score);
        }

        String[] names = {"Ada", "Grace"};
        int count = 0;
        for (String name : names) {
            count++;
            System.out.println(count + ": " + name);
        }

        for (int score : scores) {
            score = 0;
        }
        System.out.println("first score is still: " + scores[0]);
    }
}
