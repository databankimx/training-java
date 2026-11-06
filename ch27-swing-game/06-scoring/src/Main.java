import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        System.out.println("Click PLAY, then use the arrow keys and space");

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Alien Invasion");
            // The window doesn't close by itself, so the high score can be saved first.
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            GamePanel panel = new GamePanel();
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent event) {
                    panel.quit();
                    System.exit(0);
                }
            });
            frame.add(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            // Keyboard input goes to the panel only when it has focus.
            panel.requestFocusInWindow();
        });
    }
}
