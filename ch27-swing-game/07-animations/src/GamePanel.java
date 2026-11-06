import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
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
    // The screens the game can be showing. Only PLAYING and SHIP_DOWN change the fleet and the bullets.
    private enum State {
        WAITING, PLAYING, SHIP_DOWN, GAME_OVER
    }

    private Ship ship = new Ship();
    private Fleet fleet = new Fleet();
    private final List<Bullet> bullets = new ArrayList<>();
    private final List<Explosion> explosions = new ArrayList<>();
    private Explosion shipExplosion;
    // True from the moment the ship starts firing until its bullet appears on the firing animation.
    private boolean shotPending = false;
    private final Scoreboard scoreboard = new Scoreboard();
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
                if (canStartGame() && playButton.contains(event.getPoint())) {
                    startGame();
                }
            }
        });
        new Timer(Settings.FRAME_MILLISECONDS, this).start();
    }

    // A new game can start only from the starting screen or the game over screen.
    private boolean canStartGame() {
        return state == State.WAITING || state == State.GAME_OVER;
    }

    // Starts a new game, with every life, a fresh fleet, and a score of zero.
    private void startGame() {
        shipsLeft = Settings.SHIPS_ALLOWED;
        ship = new Ship();
        fleet = new Fleet();
        bullets.clear();
        explosions.clear();
        shotPending = false;
        scoreboard.resetScore();
        state = State.PLAYING;
        requestFocusInWindow();
    }

    // Saves the high score when the window is closed, so a record set in a game that hasn't ended is kept.
    public void quit() {
        scoreboard.finishGame();
    }

    // Called by the timer once per frame.
    @Override
    public void actionPerformed(ActionEvent event) {
        if (state == State.PLAYING) {
            ship.update();
            fleet.update();

            // The bullet appears on the firing animation's chosen frame, not on the key press itself.
            if (shotPending && ship.isShowingFrame(Settings.BULLET_FRAME)) {
                bullets.add(new Bullet(ship.centerX(), ship.topY()));
                shotPending = false;
            }

            for (Bullet bullet : bullets) {
                bullet.update();
            }
            bullets.removeIf(Bullet::isOffScreen);

            // Each alien hit is worth points, and shows an explosion where it was. A bullet that hits removes both.
            List<Alien> hit = fleet.removeHitBy(bullets);
            for (Alien alien : hit) {
                explosions.add(new Explosion(alien.colour() + "-boom", alien.x(), alien.y()));
            }
            scoreboard.addPoints(hit.size() * Settings.ALIEN_POINTS);
            if (fleet.isEmpty()) {
                fleet = new Fleet();
                bullets.clear();
            }

            // An alien that reaches the ship or the bottom of the screen costs a life.
            if (fleet.touches(ship.bounds()) || fleet.reachedBottom(Settings.SCREEN_HEIGHT)) {
                loseShip();
            }
        } else if (state == State.SHIP_DOWN) {
            // The ship explodes, and nothing else moves until the explosion has played through.
            shipExplosion.update();
            if (shipExplosion.isFinished()) {
                fleet = new Fleet();
                if (shipsLeft == 0) {
                    endGame();
                } else {
                    state = State.PLAYING;
                }
            }
        }

        // The alien explosions keep playing on every screen, and finished ones are removed.
        for (Explosion explosion : explosions) {
            explosion.update();
        }
        explosions.removeIf(Explosion::isFinished);
        repaint();
    }

    // Takes away a life, and starts the ship's explosion. The new fleet comes after the explosion has played.
    private void loseShip() {
        shipsLeft--;
        bullets.clear();
        shotPending = false;
        Rectangle area = ship.bounds();
        shipExplosion = new Explosion("kaboom", area.x, area.y);
        state = State.SHIP_DOWN;
    }

    // Ends the game when the last life has gone, saves the score, and offers another game.
    private void endGame() {
        state = State.GAME_OVER;
        scoreboard.finishGame();
        playButton = new Button("PLAY AGAIN", Settings.SCREEN_WIDTH / 2, Settings.SCREEN_HEIGHT / 2);
    }

    // Starts the ship's firing animation. The bullet is added when the animation reaches its chosen frame. A shot
    // can't start while another is waiting, and the ship has no more than the allowed number of bullets in the air.
    private void fire() {
        if (state == State.PLAYING && !shotPending && bullets.size() < Settings.BULLETS_ALLOWED) {
            shotPending = true;
            ship.playFiring();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        fleet.draw(this, g);
        for (Explosion explosion : explosions) {
            explosion.draw(this, g);
        }

        if (state == State.PLAYING) {
            ship.draw(this, g);
            for (Bullet bullet : bullets) {
                bullet.draw(g);
            }
        }
        if (state == State.SHIP_DOWN) {
            shipExplosion.draw(this, g);
        }
        if (state == State.WAITING || state == State.GAME_OVER) {
            playButton.draw(g);
        }

        g.setColor(Settings.TEXT);
        g.setFont(new Font("SansSerif", Font.BOLD, 16));
        if (state == State.PLAYING || state == State.SHIP_DOWN) {
            g.drawString("Ships left: " + shipsLeft, 10, 20);
        }

        // The score is shown on every screen, and the high score in the middle at the top.
        g.drawString("High score: " + scoreboard.highScore(), Settings.SCREEN_WIDTH / 2 - 60, 20);
        g.drawString("Score: " + scoreboard.score(), Settings.SCREEN_WIDTH - 130, 20);

        if (state == State.GAME_OVER) {
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
        } else if (event.getKeyCode() == KeyEvent.VK_ENTER && canStartGame()) {
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
