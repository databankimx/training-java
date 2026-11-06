import java.awt.Color;
import java.awt.Graphics;

// A bullet fired by the ship. It travels straight up, and is removed once it leaves the top of the screen.
public class Bullet {
    private double x;
    private double y;

    public Bullet(double centerX, double topY) {
        x = centerX - Settings.BULLET_WIDTH / 2.0;
        y = topY;
    }

    // Moves the bullet up one step.
    public void update() {
        y -= Settings.BULLET_SPEED;
    }

    public boolean isOffScreen() {
        return y + Settings.BULLET_HEIGHT < 0;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int) x, (int) y, Settings.BULLET_WIDTH, Settings.BULLET_HEIGHT);
    }
}
