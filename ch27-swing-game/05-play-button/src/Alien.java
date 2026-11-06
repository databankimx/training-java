import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

// One alien. Its picture is the first frame of its colour's animation, taken from the assets folder.
public class Alien {
    private final ImageIcon image;
    private final int width;
    private final int height;
    private double x;
    private double y;

    public Alien(String colour, double x, double y) {
        image = new ImageIcon("assets/images/" + colour + "-anim/frame_0_delay-0.08s.gif");
        width = image.getIconWidth();
        height = image.getIconHeight();
        this.x = x;
        this.y = y;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public double x() {
        return x;
    }

    // Moves the alien sideways. direction is 1 for right, and -1 for left.
    public void moveSideways(int direction) {
        x += direction * Settings.ALIEN_SPEED;
    }

    // Moves the alien down one row.
    public void drop() {
        y += Settings.FLEET_DROP;
    }

    // True when the alien has reached the edge the fleet is moving towards. direction is 1 for right, and -1 for left.
    // Checking only the edge in the direction of travel means the fleet turns once, and then moves away from that edge.
    public boolean reachedEdge(int direction) {
        if (direction > 0) {
            return x + width >= Settings.SCREEN_WIDTH;
        }
        return x <= 0;
    }

    // The area the alien covers, used to check for hits.
    public Rectangle bounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }

    public void draw(Component panel, Graphics g) {
        image.paintIcon(panel, g, (int) x, (int) y);
    }
}
