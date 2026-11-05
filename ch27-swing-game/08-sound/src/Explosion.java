import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;

// An explosion that plays once at a fixed place. The folder names the pictures to use, such as "blue-boom" for an
// alien or "kaboom" for the ship.
public class Explosion {
    private final Animation animation;
    private final int x;
    private final int y;

    public Explosion(String folder, double x, double y) {
        animation = new Animation(folder, false);
        animation.start();
        this.x = (int) x;
        this.y = (int) y;
    }

    public void update() {
        animation.update();
    }

    public boolean isFinished() {
        return !animation.isPlaying();
    }

    public void draw(Component panel, Graphics g) {
        animation.currentFrame().paintIcon(panel, g, x, y);
    }
}
