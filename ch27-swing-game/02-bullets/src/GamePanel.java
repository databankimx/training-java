import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

// The game's drawing area. A timer runs the frame loop: update the game, then redraw it.
public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private final Ship ship = new Ship();
    private final List<Bullet> bullets = new ArrayList<>();

    public GamePanel() {
        setPreferredSize(new Dimension(Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT));
        setBackground(Settings.BACKGROUND);
        setFocusable(true);
        addKeyListener(this);
        new Timer(Settings.FRAME_MILLISECONDS, this).start();
    }

    // Called by the timer once per frame.
    @Override
    public void actionPerformed(ActionEvent event) {
        ship.update();
        for (Bullet bullet : bullets) {
            bullet.update();
        }
        // Remove the bullets that have left the top of the screen.
        bullets.removeIf(Bullet::isOffScreen);
        repaint();
    }

    // Fires a bullet from the top of the ship, unless the ship already has the most bullets allowed.
    private void fire() {
        if (bullets.size() < Settings.BULLETS_ALLOWED) {
            bullets.add(new Bullet(ship.centerX(), ship.topY()));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ship.draw(g);
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            ship.setMovingRight(true);
        } else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            ship.setMovingLeft(true);
        } else if (event.getKeyCode() == KeyEvent.VK_SPACE) {
            fire();
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            ship.setMovingRight(false);
        } else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            ship.setMovingLeft(false);
        }
    }

    @Override
    public void keyTyped(KeyEvent event) {
        // Typed characters aren't used in this game.
    }
}
