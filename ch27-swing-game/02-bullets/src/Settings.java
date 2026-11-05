import java.awt.Color;

// The game's fixed settings, in one place so the numbers are easy to find and change.
public class Settings {
    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 600;
    static final Color BACKGROUND = new Color(230, 230, 240);
    static final int SHIP_SPEED = 5;
    static final int FRAME_MILLISECONDS = 1000 / 60;

    static final int BULLET_WIDTH = 3;
    static final int BULLET_HEIGHT = 15;
    static final int BULLET_SPEED = 7;
    static final int BULLETS_ALLOWED = 3;
}
