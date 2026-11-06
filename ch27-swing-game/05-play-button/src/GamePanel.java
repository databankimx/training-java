import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

// The game's drawing area. A timer runs the frame loop: update the game, then redraw it.
public class GamePanel extends JPanel implements ActionListener, KeyListener {
    // The screens the game can be showing. Only PLAYING updates the game.
    private enum State {
        WAITING, PLAYING, GAME_OVER
    }

    private Ship ship = new Ship();
    private Fleet fleet = new Fleet();
    private final List<Bullet> bullets = new ArrayList<>();
    private int shipsLeft = Settings.SHIPS_ALLOWED;
    private State state = State.WAITING;
    private Button playButton = new Button("PLAY", Settings.SCREEN_WIDTH / 2, Settings.SCREEN_HEIGHT / 2);

    public GamePanel() {
        setPreferredSize(new Dimension(Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT));
        setBackground(Settings.BACKGROUND);
        setFocusable(true);
        addKeyListener(this);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                if (state != State.PLAYING && playButton.contains(event.getPoint())) {
                    startGame();
                }
            }
        });
        new Timer(Settings.FRAME_MILLISECONDS, this).start();
    }

    // Starts a new game, with every life and a fresh fleet.
    private void startGame() {
        shipsLeft = Settings.SHIPS_ALLOWED;
        ship = new Ship();
        fleet = new Fleet();
        bullets.clear();
        state = State.PLAYING;
        requestFocusInWindow();
    }

    // Called by the timer once per frame. Only a game in progress changes; the other screens are only drawn.
    @Override
    public void actionPerformed(ActionEvent event) {
        if (state == State.PLAYING) {
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

    // Takes away a life, and starts a new fleet. When no lives are left, the game is over.
    private void loseShip() {
        shipsLeft--;
        fleet = new Fleet();
        bullets.clear();
        if (shipsLeft == 0) {
            state = State.GAME_OVER;
            playButton = new Button("PLAY AGAIN", Settings.SCREEN_WIDTH / 2, Settings.SCREEN_HEIGHT / 2);
        }
    }

    // Fires a bullet from the top of the ship, unless the ship already has the most bullets allowed.
    private void fire() {
        if (state == State.PLAYING && bullets.size() < Settings.BULLETS_ALLOWED) {
            bullets.add(new Bullet(ship.centerX(), ship.topY()));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        fleet.draw(this, g);

        if (state == State.PLAYING) {
            ship.draw(this, g);
            for (Bullet bullet : bullets) {
                bullet.draw(g);
            }
            g.setColor(Color.DARK_GRAY);
            g.setFont(new Font("SansSerif", Font.BOLD, 16));
            g.drawString("Ships left: " + shipsLeft, 10, 20);
        } else {
            playButton.draw(g);
        }

        if (state == State.GAME_OVER) {
            g.setColor(Color.DARK_GRAY);
            g.setFont(new Font("SansSerif", Font.BOLD, 48));
            g.drawString("GAME OVER", Settings.SCREEN_WIDTH / 2 - 150, Settings.SCREEN_HEIGHT / 2 - 80);
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
        } else if (event.getKeyCode() == KeyEvent.VK_ENTER && state != State.PLAYING) {
            startGame();
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
