import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Main {
    // Fields rather than local variables, so the click handler can change them.
    static int clicks = 0;
    static JLabel countLabel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Clicks");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            countLabel = new JLabel("Clicks: 0", JLabel.CENTER);
            JButton button = new JButton("Click me");

            // The lambda runs each time the button is clicked.
            button.addActionListener(event -> {
                clicks++;
                countLabel.setText("Clicks: " + clicks);
            });

            frame.add(countLabel, BorderLayout.CENTER);
            frame.add(button, BorderLayout.SOUTH);
            frame.setSize(300, 150);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
