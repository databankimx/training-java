import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// The fleet of aliens. The whole fleet moves together, and turns and drops when any alien reaches the edge it is
// moving towards.
public class Fleet {
    private final List<Alien> aliens = new ArrayList<>();
    private final double speed;
    private int direction = 1;

    // The fleet moves at the given speed, in pixels per frame.
    public Fleet(double speed) {
        this.speed = speed;
        // The first alien tells us the size of every alien, since they all use pictures of the same size.
        Alien sample = new Alien("blue", 0, 0);
        int width = sample.width();
        int height = sample.height();

        // Each column is a fixed distance from the last, so the fleet is only as wide as its columns need.
        double spacing = width * Settings.COLUMN_SPACING;

        for (int row = 0; row < Settings.ALIEN_COLOURS.length; row++) {
            for (int column = 0; column < Settings.FLEET_COLUMNS; column++) {
                double x = Settings.FLEET_MARGIN + column * spacing;
                double y = height + 2.0 * height * row;
                aliens.add(new Alien(Settings.ALIEN_COLOURS[row], x, y));
            }
        }
    }

    // Turns and drops the fleet if any alien has reached the edge it is moving towards, then moves every alien one step.
    public void update() {
        for (Alien alien : aliens) {
            alien.updatePicture();
        }
        for (Alien alien : aliens) {
            if (alien.reachedEdge(direction)) {
                changeDirection();
                break;
            }
        }
        for (Alien alien : aliens) {
            alien.moveSideways(direction, speed);
        }
    }

    private void changeDirection() {
        direction = -direction;
        for (Alien alien : aliens) {
            alien.drop();
        }
    }

    // Removes each alien that a bullet hits, together with the bullet that hit it. Returns the aliens that were hit,
    // so the panel can show an explosion where each one was.
    public List<Alien> removeHitBy(List<Bullet> bullets) {
        List<Alien> hit = new ArrayList<>();
        Iterator<Alien> alienIterator = aliens.iterator();
        while (alienIterator.hasNext()) {
            Alien alien = alienIterator.next();
            Iterator<Bullet> bulletIterator = bullets.iterator();
            while (bulletIterator.hasNext()) {
                if (alien.bounds().intersects(bulletIterator.next().bounds())) {
                    alienIterator.remove();
                    bulletIterator.remove();
                    hit.add(alien);
                    break;
                }
            }
        }
        return hit;
    }

    public boolean isEmpty() {
        return aliens.isEmpty();
    }

    // True when any alien overlaps the given area, such as the ship.
    public boolean touches(Rectangle area) {
        for (Alien alien : aliens) {
            if (alien.bounds().intersects(area)) {
                return true;
            }
        }
        return false;
    }

    // True when any alien has reached the bottom of the screen.
    public boolean reachedBottom(int screenHeight) {
        for (Alien alien : aliens) {
            if (alien.bounds().y + alien.bounds().height >= screenHeight) {
                return true;
            }
        }
        return false;
    }

    public void draw(Component panel, Graphics g) {
        for (Alien alien : aliens) {
            alien.draw(panel, g);
        }
    }
}
