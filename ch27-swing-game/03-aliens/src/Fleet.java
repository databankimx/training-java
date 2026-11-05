import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

// The fleet of aliens. The whole fleet moves together, and turns and drops when any alien reaches an edge.
public class Fleet {
    private final List<Alien> aliens = new ArrayList<>();
    private int direction = 1;

    public Fleet() {
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
            if (alien.reachedEdge(direction)) {
                changeDirection();
                break;
            }
        }
        for (Alien alien : aliens) {
            alien.moveSideways(direction);
        }
    }

    private void changeDirection() {
        direction = -direction;
        for (Alien alien : aliens) {
            alien.drop();
        }
    }

    public void draw(Component panel, Graphics g) {
        for (Alien alien : aliens) {
            alien.draw(panel, g);
        }
    }
}
