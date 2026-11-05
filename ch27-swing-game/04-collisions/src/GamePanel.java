import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
    private Fleet fleet = new Fleet();
    private final List<Bullet> bullets = new ArrayList<>();
    private int shipsLeft = Settings.SHIPS_ALLOWED;
    private boolean gameOver = false;

    public GamePanel() {
        setPreferredSize(new Dimension(Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT));
        setBackground(Settings.BACKGROUND);
        setFocusable(true);
        addKeyListener(this);
        new Timer(Settings.FRAME_MILLISECONDS, this).start();
    }

    // Called by the timer once per frame. Once the game is over, the frame still redraws but nothing moves.
    @Override
    public void actionPerformed(ActionEvent event) {
        if (!gameOver) {
            ship.update();
            fleet.update();
            for (Bullet bullet : bullets) {
                bullet.update();
            }
            bullets.removeIf(Bullet::isOffScreen);

            // A bullet that hits an alien removes both.
            fleet.removeHitBy(bullets);
            if (fleet.isEmpty()) {
                fleet = new Fleet();
                bullets.clear();
            }

            // An alien that reaches the ship or the bottom of the screen costs a life.
            if (fleet.touches(ship.bounds()) || fleet.reachedBottom(Settings.SCREEN_HEIGHT)) {
                loseShip();
            }
        }
        repaint();
    }

    // Takes away a life, and starts a new fleet. The game is over when no lives are left.
    private void loseShip() {
        shipsLeft--;
        fleet = new Fleet();
        bullets.clear();
        if (shipsLeft == 0) {
            gameOver = true;
        }
    }

    // Fires a bullet from the top of the ship, unless the ship already has the most bullets allowed.
    private void fire() {
        if (!gameOver && bullets.size() < Settings.BULLETS_ALLOWED) {
            bullets.add(new Bullet(ship.centerX(), ship.topY()));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        fleet.draw(this, g);
        ship.draw(this, g);
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }

        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("SansSerif", Font.BOLD, 16));
        g.drawString("Ships left: " + shipsLeft, 10, 20);
        if (gameOver) {
            g.setFont(new Font("SansSerif", Font.BOLD, 48));
            g.drawString("GAME OVER", Settings.SCREEN_WIDTH / 2 - 150, Settings.SCREEN_HEIGHT / 2);
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
