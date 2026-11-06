import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Main {
    static boolean on = false;
    static JLabel stateLabel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Switch");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            stateLabel = new JLabel("Off", JLabel.CENTER);
            JButton toggle = new JButton("Toggle");
            toggle.addActionListener(event -> {
                on = !on;
                stateLabel.setText(on ? "On" : "Off");
            });

            frame.add(stateLabel, BorderLayout.CENTER);
            frame.add(toggle, BorderLayout.SOUTH);
            frame.setSize(240, 140);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
