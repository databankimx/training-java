import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        System.out.println("Use the left and right arrow keys to move the ship");

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Alien Invasion");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            GamePanel panel = new GamePanel();
            frame.add(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            // Keyboard input goes to the panel only when it has focus.
            panel.requestFocusInWindow();
        });
    }
}
