import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

// Keeps the score for the current game, and the best score ever reached. The best score is saved to a text file,
// so it survives when the program is closed.
public class Scoreboard {
    private final Path file = Settings.HIGH_SCORE_FILE;
    private int score = 0;
    private int highScore;
    private int savedHighScore;

    public Scoreboard() {
        highScore = loadHighScore();
        savedHighScore = highScore;
    }

    // Adds points to the score. The high score rises with it as soon as the score passes it.
    public void addPoints(int points) {
        score += points;
        if (score > highScore) {
            highScore = score;
        }
    }

    public void resetScore() {
        score = 0;
    }

    public int score() {
        return score;
    }

    public int highScore() {
        return highScore;
    }

    // Saves the high score if this game set a new record. Called when a game ends.
    public void finishGame() {
        if (highScore > savedHighScore) {
            saveHighScore();
            savedHighScore = highScore;
        }
    }

    // Reads the saved high score. A missing or unreadable file means no score has been saved yet, so the high score is 0.
    private int loadHighScore() {
        try {
            return Integer.parseInt(Files.readString(file).trim());
        } catch (IOException | NumberFormatException error) {
            return 0;
        }
    }

    // Writes the high score to the file. A failure to save only costs the record, so it is reported and the game goes on.
    private void saveHighScore() {
        try {
            Files.writeString(file, Integer.toString(highScore));
        } catch (IOException error) {
            System.err.println("Could not save the high score: " + error.getMessage());
        }
    }
}
