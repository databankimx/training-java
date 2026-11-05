import java.awt.Color;
import java.nio.file.Path;

// The game's fixed settings, in one place so the numbers are easy to find and change.
public class Settings {
    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 600;
    static final Color BACKGROUND = new Color(25, 25, 45);
    static final Color TEXT = new Color(235, 235, 245);
    static final int SHIP_SPEED = 5;
    static final int FRAME_MILLISECONDS = 1000 / 60;
    static final int SHIPS_ALLOWED = 3;

    static final int BULLET_WIDTH = 3;
    static final int BULLET_HEIGHT = 15;
    static final int BULLET_SPEED = 7;
    static final int BULLETS_ALLOWED = 3;
    // The picture in the ship's firing animation on which the bullet appears.
    static final int BULLET_FRAME = 2;

    // The fleet: one row for each colour, and this many columns.
    static final String[] ALIEN_COLOURS = {"blue", "green", "pink", "yellow"};
    static final int FLEET_COLUMNS = 6;
    // The distance from one column to the next, as a multiple of the width of an alien.
    static final double COLUMN_SPACING = 1.5;
    static final int FLEET_MARGIN = 20;
    static final double ALIEN_SPEED = 1.0;
    // Added to the alien speed each time a new fleet starts after a fleet is cleared.
    static final double SPEED_INCREASE = 0.25;
    static final int FLEET_DROP = 20;

    // The play button.
    static final int BUTTON_WIDTH = 200;
    static final int BUTTON_HEIGHT = 60;

    // Points for each alien hit, and the file where the high score is kept.
    static final int ALIEN_POINTS = 10;
    // The best score is kept in the user's home folder, so it survives between runs of the game.
    static final Path HIGH_SCORE_FILE = Path.of(System.getProperty("user.home"), ".alien-invasion-high-score.txt");

    // Each picture in an animation stays on screen for this many frames. At 60 frames a second, 5 frames is about
    // 80 milliseconds, which matches the delay in the picture files.
    static final int ANIMATION_TICKS_PER_FRAME = 5;
}
