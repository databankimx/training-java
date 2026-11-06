import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

// The player's ship. It moves left and right along the bottom of the screen.
public class Ship {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 30;

    private double x;
    private final double y;
    private boolean movingLeft = false;
    private boolean movingRight = false;

    public Ship() {
        x = (Settings.SCREEN_WIDTH - WIDTH) / 2.0;
        y = Settings.SCREEN_HEIGHT - HEIGHT - 20;
    }

    public void setMovingLeft(boolean moving) {
        movingLeft = moving;
    }

    public void setMovingRight(boolean moving) {
        movingRight = moving;
    }

    // Moves the ship one step, but never past either edge of the screen.
    public void update() {
        if (movingRight && x + WIDTH < Settings.SCREEN_WIDTH) {
            x += Settings.SHIP_SPEED;
        }
        if (movingLeft && x > 0) {
            x -= Settings.SHIP_SPEED;
        }
    }

    // Draws the ship as a triangle pointing up.
    public void draw(Graphics g) {
        int left = (int) x;
        int top = (int) y;
        Polygon shape = new Polygon(
                new int[] {left, left + WIDTH / 2, left + WIDTH},
                new int[] {top + HEIGHT, top, top + HEIGHT},
                3);
        g.setColor(Color.DARK_GRAY);
        g.fillPolygon(shape);
    }
}
