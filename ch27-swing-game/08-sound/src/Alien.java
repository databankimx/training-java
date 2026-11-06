import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

// One alien. Its picture is an animation of its colour that loops for as long as the alien is alive.
public class Alien {
    private final String colour;
    private final Animation animation;
    private final int width;
    private final int height;
    private double x;
    private double y;

    public Alien(String colour, double x, double y) {
        this.colour = colour;
        animation = new Animation(colour + "-anim", true);
        animation.start();
        width = animation.currentFrame().getIconWidth();
        height = animation.currentFrame().getIconHeight();
        this.x = x;
        this.y = y;
    }

    // Moves the alien's picture on by one frame.
    public void updatePicture() {
        animation.update();
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public String colour() {
        return colour;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    // Moves the alien sideways at the given speed. direction is 1 for right, and -1 for left.
    public void moveSideways(int direction, double speed) {
        x += direction * speed;
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
        animation.currentFrame().paintIcon(panel, g, (int) x, (int) y);
    }
}
