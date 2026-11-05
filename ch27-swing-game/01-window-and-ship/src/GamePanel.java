import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

// The game's drawing area. A timer runs the frame loop: update the game, then redraw it.
public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private final Ship ship = new Ship();

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
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ship.draw(g);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            ship.setMovingRight(true);
        } else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            ship.setMovingLeft(true);
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
