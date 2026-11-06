import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    // The status line is a field, so each button's listener can change it.
    static JLabel status;

    // Adds a button that shows its own text on the status line when it's clicked.
    static JButton keyButton(String text) {
        JButton button = new JButton(text);
        button.addActionListener(event -> status.setText("Pressed " + text));
        return button;
    }

    public static void main(String[] args) throws Exception {
        // Use the system's own look, so the buttons and labels match the other windows on the computer.
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Layout demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout(10, 10));

            // Padding around the window's edge, so the components don't touch the frame.
            JPanel content = new JPanel(new BorderLayout(10, 10));
            content.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

            JLabel title = new JLabel("Four buttons in a grid", JLabel.CENTER);
            status = new JLabel("Click a button", JLabel.CENTER);
            content.add(title, BorderLayout.NORTH);
            content.add(status, BorderLayout.SOUTH);

            // A grid of four buttons, two rows and two columns, with 10 pixels of gap between the cells.
            JPanel grid = new JPanel(new GridLayout(2, 2, 10, 10));
            grid.add(keyButton("7"));
            grid.add(keyButton("8"));
            grid.add(keyButton("9"));
            grid.add(keyButton("+"));
            content.add(grid, BorderLayout.CENTER);

            frame.add(content, BorderLayout.CENTER);
            frame.setSize(340, 280);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            System.out.println("Layout built");
        });
    }
}
