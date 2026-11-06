import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        System.out.println("Opening the window");

        // Windows are built and shown on the event dispatch thread, which handles all drawing and input.
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("First window");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new JLabel("Hello from Swing", JLabel.CENTER));
            frame.setSize(300, 150);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
