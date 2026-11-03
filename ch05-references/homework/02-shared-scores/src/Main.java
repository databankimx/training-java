public class Main {
    public static void main(String[] args) {
        int[] teamScores = {10, 20, 30};
        int[] gameScores = teamScores;

        gameScores[1] = 25;
        System.out.println("teamScores[1]: " + teamScores[1]);
        System.out.println("gameScores[1]: " + gameScores[1]);

        gameScores = new int[]{0, 0, 0};
        gameScores[1] = 40;
        System.out.println("teamScores[1] after reassigning: " + teamScores[1]);
        System.out.println("gameScores[1] now: " + gameScores[1]);
    }
}
