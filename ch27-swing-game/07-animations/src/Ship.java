import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;

// The player's ship. It moves left and right along the bottom of the screen. Its picture is the first frame of its
// animation, which plays once each time the ship fires.
public class Ship {
    private final Animation firing = new Animation("ship-anim", false);
    private final int width;
    private final int height;

    private double x;
    private final double y;
    private boolean movingLeft = false;
    private boolean movingRight = false;

    public Ship() {
        width = firing.currentFrame().getIconWidth();
        height = firing.currentFrame().getIconHeight();
        x = (Settings.SCREEN_WIDTH - width) / 2.0;
        y = Settings.SCREEN_HEIGHT - height - 20;
    }

    // Plays the firing animation from its first picture.
    public void playFiring() {
        firing.start();
    }

    // True while the firing animation is showing the given picture.
    public boolean isShowingFrame(int frame) {
        return firing.isPlaying() && firing.currentIndex() == frame;
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

    // The area the ship covers, used to check for hits.
    public Rectangle bounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }

    // Moves the ship one step, but never past either edge of the screen. The firing animation also moves on.
    public void update() {
        if (movingRight && x + width < Settings.SCREEN_WIDTH) {
            x += Settings.SHIP_SPEED;
        }
        if (movingLeft && x > 0) {
            x -= Settings.SHIP_SPEED;
        }
        firing.update();
    }

    public void draw(Component panel, Graphics g) {
        firing.currentFrame().paintIcon(panel, g, (int) x, (int) y);
    }
}
