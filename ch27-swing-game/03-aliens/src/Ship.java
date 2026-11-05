import java.awt.Component;
import java.awt.Graphics;

import javax.swing.ImageIcon;

// The player's ship. It moves left and right along the bottom of the screen. Its picture is the first frame of its
// animation, which plays when the ship fires in a later step.
public class Ship {
    private final ImageIcon image;
    private final int width;
    private final int height;

    private double x;
    private final double y;
    private boolean movingLeft = false;
    private boolean movingRight = false;

    public Ship() {
        image = new ImageIcon("assets/images/ship-anim/frame_0_delay-0.08s.gif");
        width = image.getIconWidth();
        height = image.getIconHeight();
        x = (Settings.SCREEN_WIDTH - width) / 2.0;
        y = Settings.SCREEN_HEIGHT - height - 20;
    }

    public void setMovingLeft(boolean moving) {
        movingLeft = moving;
    }

    public void setMovingRight(boolean moving) {
        movingRight = moving;
    }

    // The point at the top of the ship, where a bullet starts.
    public double centerX() {
        return x + width / 2.0;
    }

    public double topY() {
        return y;
    }

    // Moves the ship one step, but never past either edge of the screen.
    public void update() {
        if (movingRight && x + width < Settings.SCREEN_WIDTH) {
            x += Settings.SHIP_SPEED;
        }
        if (movingLeft && x > 0) {
            x -= Settings.SHIP_SPEED;
        }
    }

    public void draw(Component panel, Graphics g) {
        image.paintIcon(panel, g, (int) x, (int) y);
    }
}
